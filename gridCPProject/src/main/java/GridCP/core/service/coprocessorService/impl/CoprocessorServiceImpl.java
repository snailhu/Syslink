package GridCP.core.service.coprocessorService.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GridCP.core.dao.common.ModelPackageDao;
import GridCP.core.dao.coprocessorDao.CoprocessorModelDao;
import GridCP.core.dao.coprocessorDao.CoprocessorPkgDao;
import GridCP.core.dao.coprocessorDao.CoprocessorVarDao;
import GridCP.core.domain.common.ModelPackage;
import GridCP.core.domain.coprocessor.CoprocesorPKg;
import GridCP.core.domain.coprocessor.CoprocessorModel;
import GridCP.core.domain.coprocessor.CoprocessorVar;
import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.ModelVarDto;
import GridCP.core.dto.coprocessor.FlowComponentDto;
import GridCP.core.dto.coprocessor.FlowModelDto;
import GridCP.core.dto.coprocessor.FlowModelVarDto;
import GridCP.core.dto.coprocessor.ParentDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.dto.pageModel.EasyuiTreeNode;
import GridCP.core.modelMeta.commonMeta.ModelMeta;
import GridCP.core.modelMeta.flowModelMeta.FlowComponentMeta;
import GridCP.core.modelMeta.flowModelMeta.FlowComponentVariableMeta;
import GridCP.core.modelMeta.flowModelMeta.FlowModelMeta;
import GridCP.core.service.coprocessorService.CoprocessorService;
import GridCP.core.util.flowUtil.ParseFlowXMLUtil;

@Service
public class CoprocessorServiceImpl implements CoprocessorService {

	@Resource
	private CoprocessorPkgDao coprocessorPkgDao;
	@Resource
	private CoprocessorModelDao coprocessorModelDao;
	@Resource
	private CoprocessorVarDao coprocessorVarDao;
	@Resource
	private ModelPackageDao modelPackageDao;
	
	
	@Override
	public CoprocesorPKg getCoprocesorPkg(int parentId) {		
		return coprocessorPkgDao.getCoprocesorPkg(parentId);
	}
	@Override
	@Transactional
	public boolean saveCoprocesorByParseXML(int modelPackageId, InputStream in) {
		FlowModelMeta flowModelMeta = ParseFlowXMLUtil.getFlowModel(in);
		flowModelMeta.setModelPackageId(modelPackageId);
		return this.saveCoprocesor(flowModelMeta);
	}
	@Override
	@Transactional
	public boolean saveCoprocesorByParseXML(InputStream in) {
		FlowModelMeta flowModelMeta = ParseFlowXMLUtil.getFlowModel(in);
		return this.saveCoprocesor(flowModelMeta);
	}
	@Override
	@Transactional
	public boolean saveCoprocesor(FlowModelMeta flowModelMeta) {
		CoprocessorModel flowModel = new CoprocessorModel();
//		flowModel.setName(flowModelMeta.getFlowName());
		flowModel.setDataType("flowmodel");
		flowModel.setType(flowModelMeta.getFlowType());
		flowModel.setName(flowModelMeta.getFlowName());
		//流程模型包Id
		flowModel.setModelPackageId(flowModelMeta.getModelPackageId());
		//获取流程模型全局变量
		List<FlowComponentVariableMeta> globalVars = flowModelMeta.getGlobalVars();
		if(globalVars != null && globalVars.size() > 0){
			Set<CoprocessorVar> varList = new HashSet<CoprocessorVar>();
			CoprocessorVar var = null;
			for (FlowComponentVariableMeta varMeta : globalVars) {
				var = new CoprocessorVar();
				var.setCoprocessorModel(flowModel);
				var.setDescription(varMeta.getDescription());
				var.setEnumValues(varMeta.getEnumValues());
				var.setInOrOut(varMeta.getType());
				var.setLowerBound(varMeta.getMinValue());
				var.setName(varMeta.getVarName());
				var.setUnits(varMeta.getUnits());
				var.setUpperBound(varMeta.getMaxValue());
//				var.setVarType(VarType.valueOf(varMeta.getVarType().toUpperCase()));
				varList.add(var);
			}
			flowModel.setCoprocessorVars(varList);
		}
		flowModel = coprocessorModelDao.add(flowModel);
		//获取模型组件
		List<FlowComponentMeta> componentMetaList = flowModelMeta.getComponents();
		if(componentMetaList != null && componentMetaList.size() > 0){
			CoprocessorModel component = null;
			Set<CoprocessorVar> varList = null;
			CoprocessorVar var = null;
			for (FlowComponentMeta componentMeta : componentMetaList) {
				component = new CoprocessorModel();
				component.setDataType("component");
				component.setType(componentMeta.getType());
				component.setName(componentMeta.getName());
				component.setDescription(componentMeta.getDescription());
				component.setParentId(flowModel.getCoprocessorId());
				//获取组件变量
				List<FlowComponentVariableMeta> componentVars = componentMeta.getComponentVars();
				if(componentVars != null && componentVars.size() > 0){
					varList = new HashSet<CoprocessorVar>();
					for (FlowComponentVariableMeta varMeta : componentVars) {
						var = new CoprocessorVar();
						var.setCoprocessorModel(component);
						var.setDescription(varMeta.getDescription());
						var.setEnumValues(varMeta.getEnumValues());
						var.setInOrOut(varMeta.getType());
						var.setLowerBound(varMeta.getMinValue());
						var.setName(varMeta.getVarName());
						var.setUnits(varMeta.getUnits());
						var.setUpperBound(varMeta.getMaxValue());
						var.setValue(varMeta.getValue());
						//?
//						var.setVarType(VarType.valueOf(varMeta.getVarType().toUpperCase()));
						varList.add(var);
					}
					component.setCoprocessorVars(varList);
				}
				coprocessorModelDao.add(component);
			}			
		}
		return true;
	}
	@Override
	public List<EasyuiTreeNode> getModelTree() {
		List<CoprocessorModel> flowList = coprocessorModelDao.findByParam("dataType", "flowmodel");
		if(flowList != null && flowList.size() > 0){
			List<EasyuiTreeNode> treeList = new ArrayList<EasyuiTreeNode>();
			EasyuiTreeNode tree = null;
//			List<EasyuiTreeNode> treeChildren =  null;
//			EasyuiTreeNode treeChild = null;
			for (CoprocessorModel flowModel : flowList) {
				tree = new EasyuiTreeNode();
				tree.setId(String.valueOf(flowModel.getCoprocessorId()));
				tree.setText(flowModel.getName());
				tree.setNodeType("flowmodel");
//				List<CoprocessorModel> componentList = coprocessorModelDao.findByParam("parentId", flowModel.getCoprocessorId());
//				if(componentList != null && componentList.size() > 0){
//					treeChildren =  new ArrayList<EasyuiTreeNode>();
//					for (CoprocessorModel component : componentList) {
//						treeChild = new EasyuiTreeNode();
//						treeChild.setId(String.valueOf(component.getCoprocessorId()));
//						treeChild.setText(component.getName());
//						treeChild.setNodeType("component");
//						treeChildren.add(treeChild);
//					}
//					tree.setChildren(treeChildren);
//				}
				treeList.add(tree);
			}
			return treeList;
		}
		return null;
	}
	@Override
	public TreeGridDto getFlowModelByFlowModelId(int flowModelId){
		TreeGridDto tree = new TreeGridDto();
		CoprocessorModel flowModel = coprocessorModelDao.get(flowModelId);
		tree.setId(flowModel.getCoprocessorId());
		tree.setText(flowModel.getName());
//		tree.setChildren(this.getFlowComponentListByFlowModelId(flowModelId));
		return tree;
	}
	@Override
	public List<TreeGridDto> getFlowComponentListByFlowModelId(int flowModelId) {
		List<TreeGridDto> trees = new ArrayList<TreeGridDto>();
		TreeGridDto tree = null;
		CoprocessorModel flowModel = coprocessorModelDao.get(flowModelId);
		Set<CoprocessorVar> vars = flowModel.getCoprocessorVars();
//		List<CoprocessorVar> vars = coprocessorVarDao.findByParam("coprocessorModel.coprocessor_id", flowModelId);
		if(vars != null && vars.size() > 0){
			for (CoprocessorVar var : vars) {
				tree = new TreeGridDto();
				tree.setId(var.getId());
				tree.setParentId(flowModel.getCoprocessorId());
//				tree.setText("GlobalVar");
//				tree.setVarName(var.getName());
				tree.setText(var.getName());
				tree.setValue(var.getValue());
				tree.setUnits(var.getUnits());
				tree.setDescription(var.getDescription());
				tree.setType("var");
				trees.add(tree);
			}
		}
		List<CoprocessorModel> componentList = coprocessorModelDao.findByParam("parentId", flowModelId);
		if(componentList != null && componentList.size() > 0){
			List<TreeGridDto> treeChlidren = null;
			for (CoprocessorModel component : componentList) {
				tree = new TreeGridDto();
				tree.setId(component.getCoprocessorId());
				tree.setState("closed");
				tree.setText(component.getName());
				tree.setDescription(component.getDescription());
				tree.setType("model");
				treeChlidren = this.getFlowComponentListByFlowModelId(component.getCoprocessorId());
				tree.setChildren(treeChlidren);
				trees.add(tree);
			}
		}
		return trees;
	}
	@Override
	public List<FlowModelDto> getFlowComponentListByModelPackageId(int modelPackageId) {
		List<FlowModelDto> flowModelDtoList = new ArrayList<FlowModelDto>();
		List<CoprocessorModel> flowModelList = coprocessorModelDao.findByParam("modelPackageId", modelPackageId);
		if(flowModelList != null && flowModelList.size() > 0){
			FlowModelDto flowModelDto = null;
			for (CoprocessorModel flowModel : flowModelList) {
				flowModelDto = new FlowModelDto();
				flowModelDto.setId(flowModel.getCoprocessorId());
				flowModelDto.setName(flowModel.getName());
				flowModelDto.setDescription(flowModel.getDescription());
				flowModelDto.setType(flowModel.getDataType());
				flowModelDto.setClassName(CoprocessorModel.class.getSimpleName());
				flowModelDtoList.add(flowModelDto);
			}
		}
		return flowModelDtoList;
	}
	@Override
	public FlowModelDto getFlowModelListByFlowModelId(int modelId) {
		FlowModelDto flowModelDto = new FlowModelDto();
		CoprocessorModel flowModel = coprocessorModelDao.get(modelId);
		flowModelDto.setId(flowModel.getCoprocessorId());
		flowModelDto.setName(flowModel.getName());
		flowModelDto.setDescription(flowModel.getDescription());
		Set<CoprocessorVar> vars = flowModel.getCoprocessorVars();
//		List<CoprocessorVar> vars = coprocessorVarDao.findByParam("coprocessorModel.coprocessor_id", flowModelId);
		if(vars != null && vars.size() > 0){
			List<FlowModelVarDto> globalVars = new ArrayList<FlowModelVarDto>();
			FlowModelVarDto globalVar = null;
			for (CoprocessorVar varMeta : vars) {
				globalVar = new FlowModelVarDto();
				globalVar.setDescription(varMeta.getDescription());
				globalVar.setValue(varMeta.getValue());
				globalVar.setMinValue(varMeta.getLowerBound());
				globalVar.setVarName(varMeta.getName());
				globalVar.setUnits(varMeta.getUnits());
				globalVar.setMaxValue(varMeta.getUpperBound());
				globalVars.add(globalVar);
			}
			flowModelDto.setGlobalVars(globalVars);
		}
		List<CoprocessorModel> componentList = coprocessorModelDao.findByParam("parentId", modelId);
		if(componentList != null && componentList.size() > 0){
			List<FlowComponentDto> components = new ArrayList<FlowComponentDto>();
			FlowComponentDto componentDto = null;
			for (CoprocessorModel component : componentList) {
				componentDto = new FlowComponentDto();
				componentDto.setId(component.getCoprocessorId());
				componentDto.setName(component.getName());
				componentDto.setDescription(component.getDescription());
				componentDto.setClassName(CoprocessorModel.class.getSimpleName());
				components.add(componentDto);
			}
			flowModelDto.setComponents(components);
		}
		return flowModelDto;
	}
	@Override
	public List<ParentDto> getAllParentsByFlowModelId(Integer modelId) {
		List<ParentDto> parentDaots = new ArrayList<ParentDto>() ;
		if(modelId !=null){
			parentDaots = new LinkedList<ParentDto>();	
			int parentId = modelId;
			while(parentId!=0){				
				ParentDto pd = new ParentDto();	
				CoprocessorModel cm = coprocessorModelDao.get(parentId);
				pd.setId(cm.getCoprocessorId());
				pd.setName(cm.getName());
				pd.setType("model");
				pd.setClassName(CoprocessorModel.class.getSimpleName());
				parentDaots.add(pd);				
				parentId = cm.getParentId();
				if(parentId==0){					
					int pkgParentId = cm.getModelPackageId(); 
					while(pkgParentId!=0){
						ParentDto pdo = new ParentDto();
						ModelPackage mpe = modelPackageDao.get(pkgParentId);
						pdo.setId(mpe.getId());
						pdo.setName(mpe.getName());	
						pdo.setType("pkg");
						pdo.setClassName(ModelPackage.class.getSimpleName());
						pkgParentId = mpe.getParentId();
						parentDaots.add(pdo);
					}					
				}					
			}
	  }
		return parentDaots;
	}
	@Override
	public List<ModelDto> getFlowModelListByModelPackageId(int modelPackageId) {
		List<ModelDto> flowModelDtoList = new ArrayList<ModelDto>();
		List<CoprocessorModel> flowModelList = coprocessorModelDao.findByParam("modelPackageId", modelPackageId);
		if(flowModelList != null && flowModelList.size() > 0){
			ModelDto flowModelDto = null;
			for (CoprocessorModel flowModel : flowModelList) {
				flowModelDto = new ModelDto();
				flowModelDto.setId(flowModel.getCoprocessorId());
				flowModelDto.setName(flowModel.getName());
				flowModelDto.setDescription(flowModel.getDescription());
				flowModelDto.setType(flowModel.getDataType());
				flowModelDto.setClassName(CoprocessorModel.class.getSimpleName());
				flowModelDtoList.add(flowModelDto);
			}
		}
		return flowModelDtoList;
	}
	@Override
	public List<ModelDto> getChildrenModelListByModelId(int modelId) {
		List<ModelDto> components = new ArrayList<ModelDto>();
		List<CoprocessorModel> componentList = coprocessorModelDao.findByParam("parentId", modelId);
		if(componentList != null && componentList.size() > 0){
			ModelDto componentDto = null;
			for (CoprocessorModel component : componentList) {
				componentDto = new ModelDto();
				componentDto.setId(component.getCoprocessorId());
				componentDto.setName(component.getName());
				componentDto.setDescription(component.getDescription());
				componentDto.setType(component.getDataType());
				componentDto.setClassName(CoprocessorModel.class.getSimpleName());
				components.add(componentDto);
			}
		}
		return components;
	}
	@Override
	public List<ModelVarDto> getModelVarListByModelPackageId(int modelId) {
		List<ModelVarDto> globalVars = new ArrayList<ModelVarDto>();
		CoprocessorModel flowModel = coprocessorModelDao.get(modelId);
//		List<CoprocessorVar> vars = coprocessorVarDao.findByParam("coprocessorModel.coprocessor_id", flowModelId);
		Set<CoprocessorVar> vars = flowModel.getCoprocessorVars();
		if(vars != null && vars.size() > 0){
			ModelVarDto globalVar = null;
			for (CoprocessorVar varMeta : vars) {
				globalVar = new ModelVarDto();
				globalVar.setId(varMeta.getId());
				globalVar.setDescription(varMeta.getDescription());
				globalVar.setValue(varMeta.getValue());
				globalVar.setMinValue(varMeta.getLowerBound());
				globalVar.setVarName(varMeta.getName());
				globalVar.setUnits(varMeta.getUnits());
				globalVar.setMaxValue(varMeta.getUpperBound());
				globalVars.add(globalVar);
			}
		}
		return globalVars;
	}
	@Override
	public long getModelVarCount(int modelId) {
//		CoprocessorModel flowModel = coprocessorModelDao.get(modelId);
//		Set<CoprocessorVar> vars = flowModel.getCoprocessorVars();
//		if(vars != null && vars.size() > 0){
//			return true;
//		}
		long count = coprocessorVarDao.getTotalCount("coprocessorModel.coprocessorId", modelId);
		if(count > 0){
			return count;
		}
		return 0;
	}
	@Override
	public void deleteFlowModelByModelPackageId(int modelPackageId) {
		List<CoprocessorModel> flowModelList = coprocessorModelDao.findByParam("modelPackageId", modelPackageId);
		if(flowModelList != null && flowModelList.size() > 0){
			for (CoprocessorModel flowModel : flowModelList) {
				this.deleteFlowModel(flowModel.getCoprocessorId());
			}
		}
		coprocessorModelDao.deleteByPackageId(modelPackageId);
	}
	
	@Override
	public List<ModelDto> getModelDtoListByModelName(String name) {
		List<ModelDto> flowModelDtoList = new ArrayList<ModelDto>();
//		List<CoprocessorModel> flowModelList = coprocessorModelDao.findByParam("name", name);
		List<CoprocessorModel> flowModelList = coprocessorModelDao.getByparentIdAndLikeName(0, name);
		if(flowModelList != null && flowModelList.size() > 0){
			ModelDto flowModelDto = null;
			for (CoprocessorModel flowModel : flowModelList) {
				flowModelDto = new ModelDto();
				flowModelDto.setId(flowModel.getCoprocessorId());
				flowModelDto.setName(flowModel.getName());
				flowModelDto.setDescription(flowModel.getDescription());
				flowModelDto.setType(flowModel.getDataType());
				flowModelDto.setClassName(CoprocessorModel.class.getSimpleName());
				flowModelDtoList.add(flowModelDto);
			}
		}
		return flowModelDtoList;
	}
	@Override
	public List<ModelMeta> getModelListByPackageId(int packageId) {
		List<ModelMeta> flowModelMetaList = new ArrayList<ModelMeta>();
		List<CoprocessorModel> flowModelList = coprocessorModelDao.findByParam("modelPackageId", packageId);
		if(flowModelList != null && flowModelList.size() > 0){
			ModelMeta flowModelMeta = null;
			for (CoprocessorModel flowModel : flowModelList) {
				flowModelMeta = new ModelMeta();
				flowModelMeta.setId(flowModel.getCoprocessorId());
				flowModelMeta.setName(flowModel.getName());
				flowModelMeta.setDescription(flowModel.getDescription());
				flowModelMeta.setType(flowModel.getDataType());
				flowModelMeta.setClassName(CoprocessorModel.class.getSimpleName());
				flowModelMeta.setUrl(flowModel.getURL());
				flowModelMetaList.add(flowModelMeta);
			}
		}
		return flowModelMetaList;
	}
	private void deleteFlowModel(int flowModelId){
		List<CoprocessorModel> models = coprocessorModelDao.findByParam("parentId", flowModelId);
		if(models != null && models.size() > 0){
			for (CoprocessorModel m : models) {
				this.deleteFlowModel(m.getCoprocessorId());
			}
		}
		coprocessorModelDao.delete(flowModelId);
	}


}
