package GridCP.core.service.coprocessorService;

import java.io.InputStream;
import java.util.List;

import GridCP.core.domain.coprocessor.CoprocesorPKg;
import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.ModelVarDto;
import GridCP.core.dto.coprocessor.FlowModelDto;
import GridCP.core.dto.coprocessor.ParentDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.dto.pageModel.EasyuiTreeNode;
import GridCP.core.modelMeta.commonMeta.ModelMeta;
import GridCP.core.modelMeta.flowModelMeta.FlowModelMeta;

public interface CoprocessorService {

	public CoprocesorPKg getCoprocesorPkg(int parentId); 
	/**
	 * 根据模型包Id解析XML保存模型信息
	 * @param modelPackageId 模型包Id
	 * @param InputStream 
	 * @return true==保存成功
	 * */
	public boolean saveCoprocesorByParseXML(int modelPackageId,InputStream in);
	/**
	 * @param InputStream
	 * @return true==保存成功
	 * */
	public boolean saveCoprocesorByParseXML(InputStream in);
	
	public boolean saveCoprocesor(FlowModelMeta flowModelMeta);
	/**
	 * @param 无
	 * @return List<EasyuiTreeNode> 流程模型的树形列表
	 * */
	public List<EasyuiTreeNode> getModelTree();
	
	/**
	 * @param 无
	 * @return List<TreeGridDto> 流程组件及变量的树形列表
	 * */
	public TreeGridDto getFlowModelByFlowModelId(int flowModelId);
	
	public List<TreeGridDto> getFlowComponentListByFlowModelId(int flowModelId);
	
	public List<FlowModelDto> getFlowComponentListByModelPackageId(int modelPackageId);
	
	/**
	 * @param 无
	 * @return FlowModelDto 流程模型列表
	 * */
	public FlowModelDto getFlowModelListByFlowModelId(int modelId);
	
	/**
	 * 根据id 获取所有的父类
	 * @param modelId
	 */
	public List<ParentDto>  getAllParentsByFlowModelId(Integer modelId);
		
	public List<ModelDto> getFlowModelListByModelPackageId(int modelPackageId);
	
	public List<ModelDto> getChildrenModelListByModelId(int modelId);
	
	public List<ModelVarDto> getModelVarListByModelPackageId(int modelId);
	
	public long getModelVarCount(int modelId);
	
	public void deleteFlowModelByModelPackageId(int modelPackageId);
	
	public List<ModelDto> getModelDtoListByModelName(String name);
	
	public List<ModelMeta> getModelListByPackageId(int packageId);
	
}
