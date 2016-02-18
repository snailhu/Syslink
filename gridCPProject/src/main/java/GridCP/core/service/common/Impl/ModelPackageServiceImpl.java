package GridCP.core.service.common.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import GridCP.core.dao.common.ModelPackageDao;
import GridCP.core.dao.common.PackageTypeDao;
import GridCP.core.dao.gogs.UserDao;
import GridCP.core.domain.common.ModelPackage;
import GridCP.core.domain.common.ModelPackageType;
import GridCP.core.domain.coprocessor.CoprocessorModel;
import GridCP.core.domain.modelica.ModelicaModelAndComponent;
import GridCP.core.domain.modelica.ModelicaPkg;
import GridCP.core.modelMeta.commonMeta.ModelMeta;
import GridCP.core.modelMeta.commonMeta.PackageMeta;
import GridCP.core.modelMeta.commonMeta.PackageTreeMeta;
import GridCP.core.dto.coprocessor.ParentDto;
import GridCP.core.dto.pageModel.EasyuiTreeNode;
import GridCP.core.service.common.ModelPackageService;
import GridCP.core.service.coprocessorService.CoprocessorService;
import GridCP.core.service.modelicaService.ModelicaModelAndComponentService;

@Service
public class ModelPackageServiceImpl implements ModelPackageService{

	@Resource
	private ModelPackageDao modelPackageDao;
	@Resource
	private PackageTypeDao packageTypeDao;
	@Resource
	private CoprocessorService coprocessorService;
	@Resource
	private ModelicaModelAndComponentService modelicaService;
	@Resource
	private UserDao  userDao;


	@Override
	@Transactional
	public int savePackage(PackageMeta pkg) {
		ModelPackage mpkg = new ModelPackage();
		mpkg.setName(pkg.getName());;
		mpkg.setParentId(pkg.getParentId());
		mpkg.setDescription(pkg.getDescription());
		ModelPackageType packageType = new ModelPackageType();
		packageType.setId(pkg.getPackageTypeId());
		mpkg.setPackageType(packageType);
		mpkg.setUserId(pkg.getUserId());
		mpkg.setCreateDate(new Date());
		mpkg = modelPackageDao.add(mpkg);
		return mpkg.getId();
	}
	
	@Override
	@Transactional
	public void deletePackage(int pkgId) {
		//删除包下的流程模型信息
		coprocessorService.deleteFlowModelByModelPackageId(pkgId);
		//删除包下的modelica模型信息
		modelicaService.deleteModelicaModelByModelPackageId(pkgId);
		//删除包信息
		modelPackageDao.delete(pkgId);
		
	}
	
	@Override
	public void updatePackage(PackageMeta pkg) {
		ModelPackage mpkg = new ModelPackage();
		mpkg.setId(pkg.getId());
		mpkg.setName(pkg.getName());;
		mpkg.setParentId(pkg.getParentId());
		mpkg.setDescription(pkg.getDescription());
		modelPackageDao.update(mpkg);
	}
	
	@Override
	public boolean checkIsExist(PackageMeta mpkg) {
		ModelPackage pkg = modelPackageDao.selectByNameAndParentId(mpkg.getName(), mpkg.getParentId());
		if(pkg != null){
			return true;
		}
		return false;
	}

	@Override
	public PackageMeta getPaskageDtoById(int pkgId) {
		PackageMeta pkgDto = new PackageMeta();
		 ModelPackage mpkg = modelPackageDao.get(pkgId);
		 pkgDto.setId(mpkg.getId());
		 pkgDto.setName(mpkg.getName());
		 pkgDto.setDescription(mpkg.getDescription());
		 pkgDto.setParentId(pkgDto.getParentId());
		 return pkgDto;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<EasyuiTreeNode> getModelTreeList(int userId) {
		List<EasyuiTreeNode> treeList = new ArrayList<EasyuiTreeNode>();
//		List<ModelPackageType> pkgTypeSet = packageTypeDao.findByParam("userId", userId);
//		for (ModelPackageType pkgType : pkgTypeSet) {
////			List<ModelPackage> pkgList = modelPackageDao.selectByParentIdAndpackageTypeId(0, pkgType.getId());
//			Set<ModelPackage> pkgList = pkgType.getModelPackage();
//			if(pkgList != null && pkgList.size() > 0){
//				for (ModelPackage pkg : pkgList) {
//					treeList.add(this.getTree(pkg));
//				}			
//			}
//		}
		List<ModelPackage> pkgList = modelPackageDao.selectByParentIdAndUserId(0, userId);
		if(pkgList != null && pkgList.size() > 0){
			for (ModelPackage pkg : pkgList) {
				treeList.add(this.getTree(pkg));
			}			
		}
		return treeList;
	}

	@Override
	public List<ParentDto> getAllParentsByPkgId(Integer pkgId) {
		List<ParentDto> parentDaots = new ArrayList<ParentDto>() ;
		int pkgParentId = pkgId; 
		while(pkgParentId!=0){
			ParentDto pdo = new ParentDto();
			ModelPackage mpe = modelPackageDao.get(pkgParentId);
			pdo.setId(mpe.getId());
			pdo.setName(mpe.getName());	
			pdo.setType("pkg");
			pkgParentId = mpe.getParentId();
			parentDaots.add(pdo);
		}
		return parentDaots;
	}
	
	@Override
	public List<ParentDto>  getAllParentsByModelId(Integer modelId, String className){
		List<ParentDto> parentDaots = new ArrayList<ParentDto>();
		//流程模型
		if(className.equals(CoprocessorModel.class.getSimpleName())){
			List<ParentDto> pds = coprocessorService.getAllParentsByFlowModelId(modelId);
			if(pds != null && pds.size() > 0){
				parentDaots.addAll(pds);
			}
		}
		//modelica
		if(className.equals(ModelicaPkg.class.getSimpleName())){
			List<ParentDto> pds = modelicaService.getAllParentPkgByModelicaModelPkgId(modelId);
			if(pds != null && pds.size() > 0){
				parentDaots.addAll(pds);
			}
		}
		if(className.equals(ModelicaModelAndComponent.class.getSimpleName())){
			List<ParentDto> pds = modelicaService.getAllParentModelByModelicaModelId(modelId);
			if(pds != null && pds.size() > 0){
				parentDaots.addAll(pds);
			}
		}
		return parentDaots;
	}

	
	@Override
	public List<PackageTreeMeta> getRootPackage() {
		List<PackageTreeMeta> treeList = new ArrayList<PackageTreeMeta>();
		List<ModelPackage> pkgList = modelPackageDao.findByParam("parentId", 0);
		if(pkgList != null && pkgList.size() > 0){
			PackageTreeMeta tree = null;
			for (ModelPackage pkg : pkgList) {
				tree = new PackageTreeMeta();
				tree.setId(pkg.getId());
				tree.setName(pkg.getName());
				treeList.add(tree);
			}			
		}
		return treeList;
	}

	@Override
	public List<PackageTreeMeta> getPackageByParentId(int parentId) {
		List<PackageTreeMeta> treeList = new ArrayList<PackageTreeMeta>();
		List<ModelPackage> pkgList = modelPackageDao.findByParam("parentId", parentId);
		if(pkgList != null && pkgList.size() > 0){
			PackageTreeMeta tree = null;
			for (ModelPackage pkg : pkgList) {
				tree = new PackageTreeMeta();
				tree.setId(pkg.getId());
				tree.setName(pkg.getName());
				treeList.add(tree);
			}			
		}
		return treeList;
	}

	@Override
	public List<PackageTreeMeta> getPackageTree() {
		List<PackageTreeMeta> treeList = new ArrayList<PackageTreeMeta>();
		List<ModelPackage> pkgList = modelPackageDao.findByParam("parentId", 0);
		if(pkgList != null && pkgList.size() > 0){
			for (ModelPackage pkg : pkgList) {
				treeList.add(this.getTree2(pkg));
			}			
		}
		return treeList;
	}
	
	@Override
	public List<ModelMeta> getModelListByPackageId(int packageId, String type) {
		List<ModelMeta> modelMetaList = new ArrayList<ModelMeta>();
		List<ModelMeta> flowModelList = coprocessorService.getModelListByPackageId(packageId);
		if(flowModelList != null && flowModelList.size() > 0){
			modelMetaList.addAll(flowModelList);
		}
		List<ModelMeta> modelicaModelList = modelicaService.getModelListByPackageId(packageId);
		if(modelicaModelList != null && modelicaModelList.size() > 0){
			modelMetaList.addAll(modelicaModelList);
		}
		return modelMetaList;
	}
	
	private EasyuiTreeNode getTree(ModelPackage pkg){
		EasyuiTreeNode tree = new EasyuiTreeNode();
		tree.setId(String.valueOf(pkg.getId()));
		tree.setText(pkg.getName());
		tree.setNodeType(pkg.getPackageType().getName());
		tree.setIconCls("icon-folder");
		List<ModelPackage> pkgList = modelPackageDao.selectByParentIdOrderByCreateDate(pkg.getId());
		if(pkgList != null && pkgList.size() > 0){
			List<EasyuiTreeNode> treeChildren = new ArrayList<EasyuiTreeNode>();
			for (ModelPackage pkgChildren : pkgList) {
				treeChildren.add(this.getTree(pkgChildren));
			}
			tree.setChildren(treeChildren);
		}
		return tree;
	}
	
	private PackageTreeMeta getTree2(ModelPackage pkg){
		PackageTreeMeta tree = new PackageTreeMeta();
		tree.setId(pkg.getId());
		tree.setName(pkg.getName());
		List<ModelPackage> pkgList = modelPackageDao.findByParam("parentId", pkg.getId());
		if(pkgList != null && pkgList.size() > 0){
			List<PackageTreeMeta> treeChildren = new ArrayList<PackageTreeMeta>();
			for (ModelPackage pkgChildren : pkgList) {
				treeChildren.add(this.getTree2(pkgChildren));
			}
			tree.setChildren(treeChildren);
		}
		return tree;
	}

	


}
