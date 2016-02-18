package GridCP.core.service.modelicaService;

import java.io.InputStream;
import java.util.List;

import GridCP.core.domain.modelica.ModelicaModelAndComponent;
import GridCP.core.domain.modelica.ModelicaPkg;
import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.ModelVarDto;
import GridCP.core.dto.coprocessor.ParentDto;
import GridCP.core.dto.modelicaDto.ModelicaComponentDto;
import GridCP.core.dto.modelicaDto.ModelicaComponentVariableDto;
import GridCP.core.dto.modelicaDto.ModelicaModelDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.dto.pageModel.EasyuiTreeNode;
import GridCP.core.modelMeta.commonMeta.ModelMeta;
import GridCP.core.modelMeta.modellicaModelMeta.ModelicaPackageMeta;

public interface ModelicaModelAndComponentService {

	public List<ModelicaModelAndComponent> getModelsByParentId(int parent_id)throws Exception;
	
	public List<ModelicaModelAndComponent> getModelsByPkgId(int modelicaPkgId);
	
	public boolean saveModelicaModelByParseXML(int modelPackageId,InputStream stream);
	/**
	 * 通过解析xml保存modelica模型信息
	 * */
	public boolean saveModelicaModelByParseXML(InputStream stream);
	
	public boolean saveModelicaModel(ModelicaPackageMeta pkgMeta);

	public List<EasyuiTreeNode> getModelTree();

	public List<ModelicaComponentVariableDto> getComponentVars(int componentId);
	
	public ModelicaPkg getModelicaPkg(int parentId);
	
	public List<TreeGridDto> getModelsByParentIdAndType(int parent_id,String type);
	
	/**
	 * 根据包获取modelica模型列表
	 * */
	public List<ModelicaModelDto> getModelicaModelPkgListByModelPackageId(int modelPackageId);
	
	/**
	 * 根据modelica-package获取次级模型列表
	 * */
	public List<ModelicaModelDto> getModelicaModelListByModelicaPkgId(int pkgId);
	
	/**
	 * 根据modelica-model获取次级组件和变量列表
	 * */
	public List<ModelicaComponentDto> getModelicaModelDto(int modelId,String type);
	
	/**
	 * 根据包获取modelica模型列表
	 * */
	public List<ModelDto> getModelicaPkgListByModelPackageId(int modelPackageId);
	
	/**
	 * 根据modelica-package获取次级模型列表
	 * */
	public List<ModelDto> getChildrenModelListByModelicaPkgId(int pkgId);
	
	/**
	 * 根据modelica-model获取次级组件和变量列表
	 * */
	public List<ModelDto> getChildrenModelListByModelicaId(int modelId);
	
	/**
	 * 根据modelica-model获取变量列表
	 * */
	public List<ModelVarDto> getModelVarListByModelPackageId(int modelId);
	
	public long getModelVarCount(int modelId);
	
	public void deleteModelicaModelByModelPackageId(int modelPackageId);
	
	public List<ParentDto>  getAllParentPkgByModelicaModelPkgId(Integer modelPkgId);
	
	public List<ParentDto>  getAllParentModelByModelicaModelId(Integer modelId);
	
	public List<ModelDto> getModelDtoListByModelName(String name);

	public List<ModelMeta> getModelListByPackageId(int packageId);
}
