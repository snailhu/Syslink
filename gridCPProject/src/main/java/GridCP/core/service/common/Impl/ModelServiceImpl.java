package GridCP.core.service.common.Impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.activation.DataHandler;
import javax.annotation.Resource;
import org.dom4j.Element;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import GridCP.core.common.Option;
import GridCP.core.dao.modelicaDao.ModelicaModelAndComponentDao;
import GridCP.core.dao.modelicaDao.ModelicaPkgDao;
import GridCP.core.domain.coprocessor.CoprocessorModel;
import GridCP.core.domain.modelica.ModelicaModelAndComponent;
import GridCP.core.domain.modelica.ModelicaPkg;
import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.ModelVarDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.modelMeta.flowModelMeta.FlowModelMeta;
import GridCP.core.modelMeta.modellicaModelMeta.ModelicaModelMeta;
import GridCP.core.modelMeta.modellicaModelMeta.ModelicaPackageMeta;
import GridCP.core.service.common.ModelService;
import GridCP.core.service.coprocessorService.CoprocessorService;
import GridCP.core.service.modelicaService.ModelicaModelAndComponentService;
import GridCP.core.util.FileHelper;
import GridCP.core.util.ParseXMLUtil;
import GridCP.core.util.UUIDGenerator;
import GridCP.core.util.ZipUtil;
import GridCP.core.util.flowUtil.ParseFlowXMLUtil;
import GridCP.core.util.modelicaUtil.ParseModelicaXMLUtil;

@Service
public class ModelServiceImpl implements ModelService {

	@Resource
	private CoprocessorService coprocessorService;
	@Resource
	private ModelicaModelAndComponentService modelicaService;
	@Resource
	private ModelicaModelAndComponentDao  modelicaModelAndComponentDao;
	@Resource
	private ModelicaPkgDao modelicaPkgDao;
	
	@Override
	@Transactional
	public boolean saveModelInfoByParseZip(int modelPackageId, String type, DataHandler handler) throws Exception {
		if (handler != null) {
			return this.saveModelInfoByParseXML(modelPackageId, type, handler.getInputStream());
		}
		return false;
	}
	
	@Override
	@Transactional
	public boolean saveModelInfoByParseZip(int modelPackageId, String type, InputStream inputStream) throws Exception {
		String relativePath = UUIDGenerator.getUUID();
		String absPath = Option.getCache() + "static/svg/" + relativePath;
		File file = new File(absPath);
		if(!file.exists()){
			FileHelper.createFile(absPath, true, true);	
		}
		String zipFileName = "result";
		String absFileZipPath = absPath + "/" + zipFileName+".zip";
		File resultFile = new File(absFileZipPath);
		FileOutputStream fos = null;
		fos = new FileOutputStream(resultFile, true);
		byte[] buff = new byte[1024 * 1024 * 3];
		int len = 0;
		while ((len = inputStream.read(buff)) > 0) {
			fos.write(buff, 0, len);
		}
		if (fos != null) {
            fos.flush();
            fos.close();
        }
        if (inputStream != null) {
        	inputStream.close();
        }
		List<String> fileNames = ZipUtil.unZipFiles(absFileZipPath, absPath);
		String fileXMLPath = "";
		String fileSVGName = "";
		Map<String,String> map = new HashMap<String,String>();
		for (String fileName : fileNames) {
			fileSVGName = fileName.substring(fileName.lastIndexOf('/') + 1);
			//获取svg文件名称与文件路径
			map.put(fileSVGName, relativePath + "/" + fileName);
			if(fileName.endsWith(".xml")){
				fileXMLPath = absPath + "/" + fileName;
			}
		}
		
		// 获取流程组件xml根目录
		Element rootElem = ParseXMLUtil.getRootElement(fileXMLPath);
		// 流程模型
		if (rootElem.getName().equals("Task")) {
			
		}else {
			ModelicaPackageMeta pkgMeta = ParseModelicaXMLUtil.getPackageModel(rootElem);
			pkgMeta.setPkgSVGUrl(map.get(pkgMeta.getName() + "_Icon.svg"));
			pkgMeta.setModelPackageId(modelPackageId);
			List<ModelicaModelMeta> models = pkgMeta.getModels();
			for (ModelicaModelMeta model : models) {
				model.setModelSVGUrl(map.get(model.getName() + "_Diagram.svg"));
			}
			modelicaService.saveModelicaModel(pkgMeta);	
		}
		
		FileHelper.deleteFile(absFileZipPath);
		return true;
	}
	@Override
	@Transactional
	public boolean saveModelInfoByParseZip(int modelPackageId, String type,
			long begin, long length, byte[] data) throws Exception {
		String relativePath = UUIDGenerator.getUUID();
		String absPath = Option.getCache() + "static/svg/" + relativePath;
		File file = new File(absPath);
		if(!file.exists()){
			FileHelper.createFile(absPath, true, true);	
		}
		String zipFileName = "result";
		String absFileZipPath = absPath + "/" + zipFileName+".zip";
		RandomAccessFile ra = new RandomAccessFile(absFileZipPath, "rw");
		try {
			// 移动指针
			ra.seek(begin);
			ra.write(data, 0, (int) length);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		} finally {
			ra.close();
		}
		List<String> fileNames = ZipUtil.unZipFiles(absFileZipPath, absPath);
		String fileXMLPath = "";
		String fileSVGName = "";
		Map<String,String> map = new HashMap<String,String>();
		for (String fileName : fileNames) {
			fileSVGName = fileName.substring(fileName.lastIndexOf('/') + 1);
			//获取svg文件名称与文件路径
			map.put(fileSVGName, relativePath + "/" + fileName);
			if(fileName.endsWith(".xml")){
				fileXMLPath = absPath + "/" + fileName;
			}
		}
		// 获取流程组件xml根目录
		Element rootElem = ParseXMLUtil.getRootElement(fileXMLPath);
		// 流程模型
		if (rootElem.getName().equals("Task")) {

		} else {
			ModelicaPackageMeta pkgMeta = ParseModelicaXMLUtil.getPackageModel(rootElem);
			pkgMeta.setPkgSVGUrl(map.get(pkgMeta.getName() + "_Icon.svg"));
			pkgMeta.setModelPackageId(modelPackageId);
			List<ModelicaModelMeta> models = pkgMeta.getModels();
			for (ModelicaModelMeta model : models) {
				model.setModelSVGUrl(map.get(model.getName() + "_Diagram.svg"));
			}
			modelicaService.saveModelicaModel(pkgMeta);
		}	
		FileHelper.deleteFile(absFileZipPath);		
		return true;
	}
	
	@Override
	@Transactional
	public boolean saveModelInfoByParseXML(int modelPackageId, String type,InputStream inputStream) {
		//获取流程组件xml根目录
		Element rootElem = ParseXMLUtil.getRootElement(inputStream);
		//流程模型
		if(rootElem.getName().equals("Task")){
			FlowModelMeta flowModelMeta = ParseFlowXMLUtil.getFlowModel(rootElem);
			flowModelMeta.setModelPackageId(modelPackageId);
			coprocessorService.saveCoprocesor(flowModelMeta);						
		}else{
			ModelicaPackageMeta pkgMeta = ParseModelicaXMLUtil.getPackageModel(rootElem);
			pkgMeta.setModelPackageId(modelPackageId);
			modelicaService.saveModelicaModel(pkgMeta);						
		}
		return true;
	}
	@Override
	public List<ModelDto> getModelListByModelPackageId(int modelPackageId) {
		List<ModelDto> modelDtoList = new ArrayList<ModelDto>();
		List<ModelDto> flowModelDtoList = coprocessorService.getFlowModelListByModelPackageId(modelPackageId);
		if(flowModelDtoList != null && flowModelDtoList.size() > 0){
			modelDtoList.addAll(flowModelDtoList);
		}
		List<ModelDto> modelicaModelDtoList = modelicaService.getModelicaPkgListByModelPackageId(modelPackageId);
		if(modelicaModelDtoList != null && modelicaModelDtoList.size() > 0){
			modelDtoList.addAll(modelicaModelDtoList);
		}
		return modelDtoList;
	}

	@Override
	public List<ModelDto> getChildrenModelListByModelId(int modelId, String className, String type) {
		List<ModelDto> modelDtoList = new ArrayList<ModelDto>();
		//流程模型
		if(className.equals(CoprocessorModel.class.getSimpleName())){
			List<ModelDto> flowModelDtoList = coprocessorService.getChildrenModelListByModelId(modelId);
			if(flowModelDtoList != null && flowModelDtoList.size() > 0){
				modelDtoList.addAll(flowModelDtoList);
			}
		}
		//modelica
		if(className.equals(ModelicaPkg.class.getSimpleName())){
			List<ModelDto> modelicaModelPkgDtoList = modelicaService.getChildrenModelListByModelicaPkgId(modelId);
			if(modelicaModelPkgDtoList != null && modelicaModelPkgDtoList.size() > 0){
				modelDtoList.addAll(modelicaModelPkgDtoList);
			}
		}
		if(className.equals(ModelicaModelAndComponent.class.getSimpleName())){
			List<ModelDto> modelicaModelDtoList = modelicaService.getChildrenModelListByModelicaId(modelId);
			if(modelicaModelDtoList != null && modelicaModelDtoList.size() > 0){
				modelDtoList.addAll(modelicaModelDtoList);
			}
		}
		return modelDtoList;
	}
	
	@Override
	public ModelDto getChildrenModelListByModelId(int modelId, String className) {
		ModelDto modelDto = new ModelDto();
//		long modelVarCount = 0;
		modelDto.setId(modelId);
		modelDto.setClassName(className);
		List<ModelDto> modelDtoList = new ArrayList<ModelDto>();
		//流程模型
		if(className.equals(CoprocessorModel.class.getSimpleName())){
			List<ModelDto> flowModelDtoList = coprocessorService.getChildrenModelListByModelId(modelId);
			if(flowModelDtoList != null && flowModelDtoList.size() > 0){
				modelDtoList.addAll(flowModelDtoList);
			}
//			modelVarCount = coprocessorService.getModelVarCount(modelId);
		}
		//modelica
		if(className.equals(ModelicaPkg.class.getSimpleName())){
			ModelicaPkg  modelicaPkg  = modelicaPkgDao.get(modelId);
			modelDto.setSvgPath(modelicaPkg.getSvgPath());
			List<ModelDto> modelicaModelPkgDtoList = modelicaService.getChildrenModelListByModelicaPkgId(modelId);
			if(modelicaModelPkgDtoList != null && modelicaModelPkgDtoList.size() > 0){
				modelDtoList.addAll(modelicaModelPkgDtoList);
			}
		}
		if(className.equals(ModelicaModelAndComponent.class.getSimpleName())){
			ModelicaModelAndComponent model = modelicaModelAndComponentDao.get(modelId);
			modelDto.setSvgPath(model.getSvgPath());
			List<ModelDto> modelicaModelDtoList = modelicaService.getChildrenModelListByModelicaId(modelId);
			if(modelicaModelDtoList != null && modelicaModelDtoList.size() > 0){
				modelDtoList.addAll(modelicaModelDtoList);
			}
//			modelVarCount = modelicaService.getModelVarCount(modelId);
		}
		modelDto.setModels(modelDtoList);
//		modelDto.setModelVarSize(modelVarCount);;
//		modelDto.setVars(this.getModelVarListByModelPackageId(modelId, className));
		return modelDto;
	}
	
	@Override
	public List<ModelVarDto> getModelVarListByModelPackageId(int modelId,String className) {
		List<ModelVarDto> modelVarDtoList = new ArrayList<ModelVarDto>();
		//流程模型变量
		if(className.equals(CoprocessorModel.class.getSimpleName())){
			List<ModelVarDto> flowModelVarDtoList = coprocessorService.getModelVarListByModelPackageId(modelId);
			if(flowModelVarDtoList != null && flowModelVarDtoList.size() > 0){
				modelVarDtoList.addAll(flowModelVarDtoList);
			}
		}
		//modelica
		if(className.equals(ModelicaModelAndComponent.class.getSimpleName())){
			List<ModelVarDto> modelicaModelVarDtoList = modelicaService.getModelVarListByModelPackageId(modelId);
			if(modelicaModelVarDtoList != null && modelicaModelVarDtoList.size() > 0){
				modelVarDtoList.addAll(modelicaModelVarDtoList);
			}
		}
		return modelVarDtoList;
	}

	@Override
	public List<ModelDto> getModelDtoListByModelName(String name) {
		List<ModelDto> modelDtoList = new ArrayList<ModelDto>();
		List<ModelDto> flowModelDtoList = coprocessorService.getModelDtoListByModelName(name);
		if(flowModelDtoList != null && flowModelDtoList.size() > 0){
			modelDtoList.addAll(flowModelDtoList);
		}
		List<ModelDto> modelicaModelDtoList = modelicaService.getModelDtoListByModelName(name);
		if(modelicaModelDtoList != null && modelicaModelDtoList.size() > 0){
			modelDtoList.addAll(modelicaModelDtoList);
		}
		return modelDtoList;
	}
	@Override
	public List<TreeGridDto> getModelComponentVarsList(int modelId, String className) {
		List<TreeGridDto> treeList = null;
		//流程模型变量
		if (className.equals(CoprocessorModel.class.getSimpleName())) {
			treeList = coprocessorService.getFlowComponentListByFlowModelId(modelId);
		}
		// modelica
		if (className.equals(ModelicaModelAndComponent.class.getSimpleName())) {
			
			treeList = modelicaService.getModelsByParentIdAndType(modelId, "");
		}	
		return treeList;
	}


}
