package GridCP.core.service.modelicaService.Impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GridCP.core.common.Config;
import GridCP.core.dao.common.ModelPackageDao;
import GridCP.core.dao.modelicaDao.ModelicaModelAndComponentDao;
import GridCP.core.dao.modelicaDao.ModelicaPkgDao;
import GridCP.core.dao.modelicaDao.ModelicaVarDao;
import GridCP.core.domain.common.ModelPackage;
import GridCP.core.domain.modelica.ClassRestricitonType;
import GridCP.core.domain.modelica.ModelicaModelAndComponent;
import GridCP.core.domain.modelica.ModelicaPkg;
import GridCP.core.domain.modelica.ModelicaVar;
import GridCP.core.dto.commonDto.ModelDto;
import GridCP.core.dto.commonDto.ModelVarDto;
import GridCP.core.dto.coprocessor.ParentDto;
import GridCP.core.dto.modelicaDto.ModelicaComponentDto;
import GridCP.core.dto.modelicaDto.ModelicaComponentVariableDto;
import GridCP.core.dto.modelicaDto.ModelicaModelDto;
import GridCP.core.dto.modelicaDto.TreeGridDto;
import GridCP.core.dto.pageModel.EasyuiTreeNode;
import GridCP.core.modelMeta.commonMeta.ModelMeta;
import GridCP.core.modelMeta.modellicaModelMeta.ModelicaComponentMeta;
import GridCP.core.modelMeta.modellicaModelMeta.ModelicaComponentVariableMeta;
import GridCP.core.modelMeta.modellicaModelMeta.ModelicaModelMeta;
import GridCP.core.modelMeta.modellicaModelMeta.ModelicaPackageMeta;
import GridCP.core.service.modelicaService.ModelicaModelAndComponentService;
import GridCP.core.util.modelicaUtil.ParseModelicaXMLUtil;
import GridCP.util.ResultUtil;

@Service
public class ModelicaModelAndComponentServiceImpl implements
ModelicaModelAndComponentService {
	@Resource
	private ModelicaModelAndComponentDao  modelicaModelAndComponentDao;
	@Resource
	private ModelicaPkgDao modelicaPkgDao;
	@Resource
	private ModelicaVarDao modelicaVarDao;
	@Resource
	private ModelPackageDao modelPackageDao;
	
	@Override
	public List<ModelicaModelAndComponent> getModelsByParentId(int parent_id) throws Exception {
		List<ModelicaModelAndComponent> models =modelicaModelAndComponentDao.getModelsByParentId(parent_id);
		if (models==null){
			ResultUtil.throwExcepion(ResultUtil.createFail(Config.MESSAGE, 101,null));
		}
		return models;
	}
	
	@Override
	public List<ModelicaModelAndComponent> getModelsByPkgId(int modelicaPkgId) {
		return modelicaModelAndComponentDao.getModelsByPkgId(modelicaPkgId);
	}
	
	@Override
	@Transactional
	public boolean saveModelicaModelByParseXML(int modelPackageId,InputStream stream) {
		ModelicaPackageMeta pkgMeta = ParseModelicaXMLUtil.getPackageModel(stream);
		pkgMeta.setModelPackageId(modelPackageId);
		return this.saveModelicaModel(pkgMeta);
	}
	
	@Override
	@Transactional
	public boolean saveModelicaModelByParseXML(InputStream stream) {
		ModelicaPackageMeta pkgMeta = ParseModelicaXMLUtil.getPackageModel(stream);
		return this.saveModelicaModel(pkgMeta);
	}
	
	@Override
	@Transactional
	public boolean saveModelicaModel(ModelicaPackageMeta pkgMeta) {
		ModelicaPkg pkg = new ModelicaPkg();
		pkg.setClassRestriction(ClassRestricitonType.valueOf(pkgMeta.getClassRestriction().toUpperCase()));
		pkg.setURL(pkgMeta.getURL());
		pkg.setName(pkgMeta.getName());
		pkg.setDescription(pkgMeta.getDescription());
		//保存模型包Id
		pkg.setModelPackageId(pkgMeta.getModelPackageId());
		//svg
		pkg.setSvgPath(pkgMeta.getPkgSVGUrl());
		pkg = modelicaPkgDao.add(pkg);
		//保存包下的模型
		List<ModelicaModelMeta> models = pkgMeta.getModels();
		if(models != null && models.size() > 0){
			ModelicaModelAndComponent model = null;
			for (ModelicaModelMeta modelMeta : models) {
				model = new ModelicaModelAndComponent();
				model.setParentPkgId(pkg.getId());
				model.setModelName(modelMeta.getName());
				model.setInfo(modelMeta.getDescription());
				model.setType("model");
				model.setSvgPath(modelMeta.getModelSVGUrl());
				model = modelicaModelAndComponentDao.add(model);
				//保存模型下的组件
				List<ModelicaComponentMeta> components = modelMeta.getComponents();
				for (ModelicaComponentMeta componentMeta : components) {
					this.saveComponent(model,componentMeta);					
				}
			}			
		}
		return true;
	}
	
	@Override
	public List<EasyuiTreeNode> getModelTree() {
		List<ModelicaPkg> pkgList = modelicaPkgDao.list("from ModelicaPkg");
		if(pkgList != null && pkgList.size() > 0){
			List<EasyuiTreeNode> treeList = new ArrayList<EasyuiTreeNode>();
			EasyuiTreeNode tree = null;
			List<EasyuiTreeNode> treeChildren = null;
			for (ModelicaPkg pkg : pkgList) {
				System.out.println("pkg: " + pkg.getId() + " : " + pkg.getName());
				tree = new EasyuiTreeNode();
				tree.setId(String.valueOf(pkg.getId()));
				tree.setText(pkg.getName());
				tree.setNodeType("package");
//				List<ModelicaModelAndComponent> models = modelicaModelAndComponentDao.list("from ModelicaModelAndComponent as mac where mac.parentPkgId = ?",pkg.getId());
				List<ModelicaModelAndComponent> models = modelicaModelAndComponentDao.findByParam("parentPkgId", pkg.getId());
				if(models != null && models.size() > 0){
					treeChildren = new ArrayList<EasyuiTreeNode>();
					for (ModelicaModelAndComponent model : models) {
						System.out.println("model: " + model.getId() + " : " + model.getModelName());
						treeChildren.add(this.getTreeByModel(model));
					}
					tree.setChildren(treeChildren);
				}
				treeList.add(tree);
			}
			return treeList;
		}
		return null;
	}


	@Override
	public ModelicaPkg getModelicaPkg(int parentId) {
		return modelicaPkgDao.getModelicaPkg(parentId);
	}

	@Override
	public List<ModelicaComponentVariableDto> getComponentVars(int componentId) {
		List<ModelicaVar> varList = modelicaVarDao.list("from ModelicaVar where isDefault = 1 and modelica_id = ?",componentId);
		if(varList != null && varList.size() > 0){
			List<ModelicaComponentVariableDto> varDtoList = new ArrayList<ModelicaComponentVariableDto>();
			ModelicaComponentVariableDto varDto = null;
			for (ModelicaVar var : varList) {
				varDto = new ModelicaComponentVariableDto();
				varDto.setId(var.getId());
				varDto.setVarName(var.getName());
				varDto.setValue(var.getValue());
				varDto.setUnits(var.getUnit());
				varDto.setDescription(var.getDescription());
				System.out.println(varDto);
				varDtoList.add(varDto);
			}
			return varDtoList;
		}
		return null;
	}
	@Override
	public List<TreeGridDto> getModelsByParentIdAndType(int parent_id, String type) {
		List<ModelicaModelAndComponent> components = modelicaModelAndComponentDao.getModelsByParentIdAndType(parent_id, "component");
		List<TreeGridDto> treeDtos = null;
		if (components != null && components.size() > 0) {
			treeDtos = new ArrayList<TreeGridDto>();
			for (ModelicaModelAndComponent mmac : components) {
				treeDtos.add(this.getComponent(mmac));
			}
		}
		return treeDtos;
	}
	@Override
	public List<ModelicaModelDto> getModelicaModelPkgListByModelPackageId(int modelPackageId) {
		List<ModelicaModelDto> modelDtoList = new ArrayList<ModelicaModelDto>();
		List<ModelicaPkg> pkgList = modelicaPkgDao.findByParam("modelPackageId", modelPackageId);
		if(pkgList != null && pkgList.size() > 0){
			ModelicaModelDto modelDto = null;
			for (ModelicaPkg pkg : pkgList) {
				modelDto = new ModelicaModelDto();
				modelDto.setId(pkg.getId());
				modelDto.setName(pkg.getName());
				modelDto.setDescription(pkg.getDescription());
				modelDto.setType(pkg.getClassRestriction().getName());
				modelDto.setModelPackageId(modelPackageId);
				modelDtoList.add(modelDto);
			}
		}
		return modelDtoList;
	}
	@Override
	public List<ModelicaModelDto> getModelicaModelListByModelicaPkgId(int pkgId) {
		List<ModelicaModelDto> modelDtoList = new ArrayList<ModelicaModelDto>();
		List<ModelicaModelAndComponent> pkgList = modelicaModelAndComponentDao.findByParam("parentPkgId", pkgId);
		if(pkgList != null && pkgList.size() > 0){
			ModelicaModelDto modelDto = null;
			for (ModelicaModelAndComponent model : pkgList) {
				modelDto = new ModelicaModelDto();
				modelDto.setId(model.getId());
				modelDto.setName(model.getModelName());
				modelDto.setDescription(model.getInfo());
				modelDto.setType(model.getType());
				modelDtoList.add(modelDto);
			}
		}
		return modelDtoList;
	}
	@Override
	public List<ModelicaComponentDto> getModelicaModelDto(int modelId,String type) {
		List<ModelicaComponentDto> componentDtoList = new ArrayList<ModelicaComponentDto>();
		ModelicaModelAndComponent model = modelicaModelAndComponentDao.get(modelId);
//		List<ModelicaModelAndComponent> components = modelicaModelAndComponentDao.getModelsByParentIdAndType(modelId,"component");
		Set<ModelicaModelAndComponent> components = model.getIsQuotedModelicaModel();
		if(components != null && components.size() > 0){
			ModelicaComponentDto componentDto = null;
			List<ModelicaComponentVariableDto> vars = null;
			ModelicaComponentVariableDto var = null;
			for (ModelicaModelAndComponent component : components) {
				componentDto = new ModelicaComponentDto();
				//获取组件变量
				Set<ModelicaVar> varSet = component.getModelicaVars();
				vars = new ArrayList<ModelicaComponentVariableDto>();
				if(varSet != null && varSet.size() > 0){
					for (ModelicaVar varMeta : varSet) {
						var = new ModelicaComponentVariableDto();
						var.setVarName(varMeta.getName());
						var.setValue(varMeta.getValue());
						var.setDescription(varMeta.getDescription());
						var.setUnits(varMeta.getUnit());					
						vars.add(var);
					}
				}
				componentDto.setVars(vars);
				//获取组件信息
				componentDto.setId(component.getId());
				componentDto.setName(componentDto.getName());
				componentDto.setDescription(component.getInfo());
				
				componentDtoList.add(componentDto);
			}
			
		}
		return componentDtoList;
	}
	
	@Override
	public List<ModelDto> getModelicaPkgListByModelPackageId(int modelPackageId) {
		List<ModelDto> modelDtoList = new ArrayList<ModelDto>();
		List<ModelicaPkg> pkgList = modelicaPkgDao.findByParam("modelPackageId", modelPackageId);
		if(pkgList != null && pkgList.size() > 0){
			ModelDto modelDto = null;
			for (ModelicaPkg pkg : pkgList) {
				modelDto = new ModelDto();
				modelDto.setId(pkg.getId());
				modelDto.setName(pkg.getName());
				modelDto.setDescription(pkg.getDescription());
				modelDto.setType(pkg.getClassRestriction().getName());
				modelDto.setModelPackageId(modelPackageId);
				modelDto.setClassName(ModelicaPkg.class.getSimpleName());
				modelDto.setSvgPath(pkg.getSvgPath());
				modelDtoList.add(modelDto);
			}
		}
		return modelDtoList;
	}
	@Override
	public List<ModelDto> getChildrenModelListByModelicaPkgId(int pkgId) {
		List<ModelDto> modelDtoList = new ArrayList<ModelDto>();
		List<ModelicaModelAndComponent> pkgList = modelicaModelAndComponentDao.findByParam("parentPkgId", pkgId);
		if(pkgList != null && pkgList.size() > 0){
			ModelDto modelDto = null;
			for (ModelicaModelAndComponent model : pkgList) {
				modelDto = new ModelDto();
				modelDto.setId(model.getId());
				modelDto.setName(model.getModelName());
				modelDto.setDescription(model.getInfo());
				modelDto.setType(model.getType());
				modelDto.setSvgPath(model.getSvgPath());
				modelDto.setClassName(ModelicaModelAndComponent.class.getSimpleName());
				modelDtoList.add(modelDto);
			}
		}
		return modelDtoList;
	}

	@Override
	public List<ModelDto> getChildrenModelListByModelicaId(int modelId) {
		List<ModelDto> modelDtoList = new ArrayList<ModelDto>();
//		ModelicaModelAndComponent modelChildren = modelicaModelAndComponentDao.get(modelId);
		List<ModelicaModelAndComponent> components = modelicaModelAndComponentDao.getModelsByParentIdAndType(modelId,"component");
//		Set<ModelicaModelAndComponent> components = modelChildren.getIsQuotedModelicaModel();
		if(components != null && components.size() > 0){
			ModelDto modelDto = null;
			for (ModelicaModelAndComponent model : components) {
				modelDto = new ModelDto();
				modelDto.setId(model.getId());
				modelDto.setName(model.getModelName());
				modelDto.setDescription(model.getInfo());
				modelDto.setType(model.getType());
				modelDto.setSvgPath(model.getSvgPath());
				modelDto.setClassName(ModelicaModelAndComponent.class.getSimpleName());
				modelDtoList.add(modelDto);
			}
		}
		return modelDtoList;
	}

	@Override
	public List<ModelVarDto> getModelVarListByModelPackageId(int modelId) {
		List<ModelVarDto> varDtos = new ArrayList<ModelVarDto>();
		List<ModelicaVar> vars = modelicaVarDao.findByParam("modelicaModelandComponent.id", modelId);
		if(vars != null && vars.size() > 0){
			ModelVarDto varDto = null;
			for (ModelicaVar var : vars) {
				varDto = new ModelVarDto();
				varDto.setId(var.getId());
				varDto.setVarName(var.getName());
				varDto.setValue(var.getValue());
				varDto.setDescription(var.getDescription());
				varDto.setUnits(var.getUnit());					
				varDtos.add(varDto);
			}
		}
		return varDtos;
	}
	@Override
	public long getModelVarCount(int modelId){
//		List<ModelicaVar> vars = modelicaVarDao.findByParam("modelica_id", modelId);
//		if(vars != null && vars.size() > 0){
//			return true;
//		}
		long count = modelicaVarDao.getTotalCount("modelicaModelandComponent.id", modelId);
		if(count > 0){
			return count;
		}
		return 0;
	}
	
	@Override
	public void deleteModelicaModelByModelPackageId(int modelPackageId) {
		List<ModelicaPkg> pkgList = modelicaPkgDao.findByParam("modelPackageId", modelPackageId);
		if(pkgList != null && pkgList.size() > 0){
			for (ModelicaPkg pkg : pkgList) {
				List<ModelicaModelAndComponent> modelList = modelicaModelAndComponentDao.findByParam("parentPkgId", pkg.getId());
				if(modelList != null && modelList.size() > 0){
					for (ModelicaModelAndComponent model : modelList) {
						this.deleteModelicaModelByParentId(model.getId());
//						modelicaModelAndComponentDao.delete(model);
					}
				}
				modelicaPkgDao.delete(pkg);
			}
		}
	}
	
	@Override
	public List<ParentDto> getAllParentPkgByModelicaModelPkgId(Integer modelPkgId) {
		List<ParentDto> parentDaots = new ArrayList<ParentDto>();
		ParentDto parentDaot = new ParentDto();
		ModelicaPkg pkg = modelicaPkgDao.get(modelPkgId);
		parentDaot.setId(pkg.getId());
		parentDaot.setClassName(ModelicaPkg.class.getSimpleName());
		parentDaot.setName(pkg.getName());
		parentDaot.setType("model");
		parentDaots.add(parentDaot);
		int pkgParentId = pkg.getModelPackageId(); 
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
		return parentDaots;
	}

	@Override
	public List<ParentDto> getAllParentModelByModelicaModelId(Integer modelId) {
		List<ParentDto> parentDaots = new ArrayList<ParentDto>();
		ModelicaModelAndComponent model = modelicaModelAndComponentDao.get(modelId);
		ParentDto parentDaot = new ParentDto();
		parentDaot.setId(model.getId());
		parentDaot.setClassName(ModelicaModelAndComponent.class.getSimpleName());
		parentDaot.setName(model.getModelName());
		parentDaot.setType("model");
		parentDaots.add(parentDaot);
		if(model.getParentPkgId() > 0){
			parentDaots.addAll(this.getAllParentPkgByModelicaModelPkgId(model.getParentPkgId()));
			return parentDaots;
		}else{
			int parentId = model.getParentId();
			int modelPkgId = 0;
			while(parentId!=0){	
				ParentDto pd = new ParentDto();	
				ModelicaModelAndComponent component = modelicaModelAndComponentDao.get(parentId);
				pd.setId(component.getId());
				pd.setName(component.getModelName());
				pd.setType("model");
				pd.setClassName(ModelicaModelAndComponent.class.getSimpleName());
				parentId = component.getParentId();
				modelPkgId = component.getParentPkgId();
				parentDaots.add(pd);
			}
			parentDaots.addAll(this.getAllParentPkgByModelicaModelPkgId(modelPkgId));
			return parentDaots;
		}
	}
	
	@Override
	public List<ModelDto> getModelDtoListByModelName(String name){
		List<ModelDto> modelDtoList = new ArrayList<ModelDto>();
//		List<ModelicaPkg> pkgList = modelicaPkgDao.findByParam("Name", name);
//		if(pkgList != null && pkgList.size() > 0){
//			ModelDto modelDto = null;
//			for (ModelicaPkg pkg : pkgList) {
//				modelDto = new ModelDto();
//				modelDto.setId(pkg.getId());
//				modelDto.setName(pkg.getName());
//				modelDto.setDescription(pkg.getDescription());
//				modelDto.setType(pkg.getClassRestriction().getName());
//				modelDto.setClassName(ModelicaPkg.class.getSimpleName());
//				modelDtoList.add(modelDto);
//			}
//		}
//		List<ModelicaModelAndComponent> modelList = modelicaModelAndComponentDao.findByParam("modelName", name);
		List<ModelicaModelAndComponent> modelList = modelicaModelAndComponentDao.getModelsByLikeModelNamedAndType(name, "model");
		if(modelList != null && modelList.size() > 0){
			ModelDto modelDto = null;
			for (ModelicaModelAndComponent model : modelList) {
				modelDto = new ModelDto();
				modelDto.setId(model.getId());
				modelDto.setName(model.getModelName());
				modelDto.setDescription(model.getInfo());
				modelDto.setType(model.getType());
				modelDto.setClassName(ModelicaModelAndComponent.class.getSimpleName());
				modelDtoList.add(modelDto);
			}
		}
		return modelDtoList;
	}
	
	@Override
	public List<ModelMeta> getModelListByPackageId(int packageId) {
		List<ModelMeta> modelMetaList = new ArrayList<ModelMeta>();
		List<ModelicaPkg> pkgList = modelicaPkgDao.findByParam("modelPackageId", packageId);
		if(pkgList != null && pkgList.size() > 0){
			ModelMeta modelMeta = null;
			for (ModelicaPkg pkg : pkgList) {
				modelMeta = new ModelMeta();
				modelMeta.setId(pkg.getId());
				modelMeta.setName(pkg.getName());
				modelMeta.setDescription(pkg.getDescription());
				modelMeta.setType(pkg.getClassRestriction().getName());
				modelMeta.setClassName(ModelicaPkg.class.getSimpleName());
				modelMeta.setUrl(pkg.getURL());
				modelMetaList.add(modelMeta);
			}
		}
		return modelMetaList;
	}
	
	private void deleteModelicaModelByParentId(int modelId){
		 List<ModelicaModelAndComponent> models = modelicaModelAndComponentDao.findByParam("parentId", modelId);
		 if(models != null && models.size() > 0){
			 for (ModelicaModelAndComponent model : models) {
				this.deleteModelicaModelByParentId(model.getId());
			}
		 }
		 modelicaModelAndComponentDao.delete(modelId);
	}
	
	private EasyuiTreeNode getTreeByModel(ModelicaModelAndComponent model){
		EasyuiTreeNode tree = new EasyuiTreeNode();
		tree.setId(String.valueOf(model.getId()));
		tree.setText(model.getModelName());
		tree.setNodeType(model.getType());
//		List<ModelicaModelAndComponent> components = modelicaModelAndComponentDao.
//												 list("from ModelicaModelAndComponent as mac where mac.type= 'model' and mac.parentId = ? ",
//												 model.getId());
		List<ModelicaModelAndComponent> components = modelicaModelAndComponentDao.getModelsByParentIdAndType(model.getId(), "model");
		if(components != null && components.size() > 0){
			List<EasyuiTreeNode> treeChildren =  new ArrayList<EasyuiTreeNode>();
			for (ModelicaModelAndComponent component : components) {
				treeChildren.add(this.getTreeByModel(component));
			}
			tree.setChildren(treeChildren);
		}
		return tree;
	}
	
	private void saveComponent(ModelicaModelAndComponent parent, ModelicaComponentMeta componentMeta) {		
		ModelicaModelAndComponent component = new ModelicaModelAndComponent();
//		if(parent.getType().equals("model")){
//			component.setParentPkgId(parent.getId());			
//		}else{
//			component.setParentId(parent.getId());
//		}
		component.setParentId(parent.getId());
		component.setModelName(componentMeta.getName());
		component.setInfo(componentMeta.getDescription());
		component.setType("component");
		List<ModelicaComponentVariableMeta> varMetas = componentMeta.getVars();
		component = modelicaModelAndComponentDao.add(component);
		if(varMetas != null && varMetas.size() > 0){
			this.componentVars(component,varMetas);
//			Set<ModelicaVar> varSet = this.componentVars(component,varMetas);
//			component.setModelicaVars(varSet);
		}
		//保存组件下的组件
		List<ModelicaComponentMeta> componentChildren = componentMeta.getComponents();
		if(componentChildren != null && componentChildren.size() > 0){
			for (ModelicaComponentMeta omponentChild : componentChildren) {
				this.saveComponent(component,omponentChild);
			}
		}
	}

	private Set<ModelicaVar> componentVars(ModelicaModelAndComponent component,List<ModelicaComponentVariableMeta> varMetas) {
		Set<ModelicaVar> varSet = new HashSet<ModelicaVar>();
		ModelicaVar var = null;
		for (ModelicaComponentVariableMeta varMeta : varMetas) {
//			System.out.println(varMeta.toString());
			try {
				var = new ModelicaVar();
				var.setModelicaModelandComponent(component);
				var.setName(varMeta.getVarName());
				var.setValue(varMeta.getPrefixes());
				var.setDefault(true);
				var.setDescription(varMeta.getDescription());
//				var.setUnit(varMeta.getUnits());					
				if(varMeta.getUnits() != null){
					var.setUnit(varMeta.getUnits());					
				}else{
					var.setUnit("");
				}
//				System.out.println(var);
				modelicaVarDao.add(var);
				varSet.add(var);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return varSet;
	}
	
	private TreeGridDto getComponent(ModelicaModelAndComponent comoponent){
		TreeGridDto tgd = new TreeGridDto();
		tgd.setText(comoponent.getModelName());
		tgd.setId(comoponent.getId());
		tgd.setState("closed");
		tgd.setIconCls("icon-component");
		//获取组件变量树
		List<TreeGridDto> trees = getModelicaVarByparent(comoponent.getId());
		if(trees == null ){
			trees = new ArrayList<TreeGridDto>();
		}
		List<ModelicaModelAndComponent> components = modelicaModelAndComponentDao.getModelsByParentIdAndType(comoponent.getId(), "component");
		if(components != null && components.size() > 0){
			for(ModelicaModelAndComponent mmac : components){
				trees.add(this.getComponent(mmac));
			}
		}
		tgd.setChildren(trees);
		return tgd;
	}	
	private List<TreeGridDto> getModelicaVarByparent(int parent_id){		
		List<ModelicaVar> vars = modelicaVarDao.getModelicaVarByModelicaId(parent_id);
		if(vars!=null && vars.size()>0){
			List<TreeGridDto> treeDtos = new ArrayList<TreeGridDto>();
			for(ModelicaVar var:vars){
				TreeGridDto td = new TreeGridDto();
				td.setText(var.getName());
				td.setIconCls("icon-component-var");
//				td.setText("variable");
//				td.setVarName(var.getName());
				td.setValue(var.getValue());
				td.setId(var.getId());
				td.setUnits(var.getUnit());
				td.setDescription(var.getDescription());	
				treeDtos.add(td);
			}
			return treeDtos;
		}
		return null;
	}



}
