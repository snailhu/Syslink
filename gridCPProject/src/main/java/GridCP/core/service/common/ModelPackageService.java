package GridCP.core.service.common;

import java.util.List;
import GridCP.core.dto.coprocessor.ParentDto;
import GridCP.core.dto.pageModel.EasyuiTreeNode;
import GridCP.core.modelMeta.commonMeta.ModelMeta;
import GridCP.core.modelMeta.commonMeta.PackageMeta;
import GridCP.core.modelMeta.commonMeta.PackageTreeMeta;

public interface ModelPackageService {

	/**
	 * @param PackageMeta
	 * @return id
	 * */
	public int savePackage(PackageMeta pkg);
	
	/**
	 * @param id
	 * */
	public void deletePackage(int pkgId);
	
	/**
	 * @param PackageMeta
	 * @return 
	 * */
	public void updatePackage(PackageMeta pkg);
	
	/**
	 * 判断同一级目录下是否存在此包
	 * @param 存在==true
	 * @return PaskageDto
	 * */
	public boolean checkIsExist(PackageMeta pkg);
	
	public PackageMeta getPaskageDtoById(int pkgId);
	
	public List<EasyuiTreeNode> getModelTreeList(int userId);
	
	/**
	 * 根据包id 获取所有的父类包
	 * @param pkgId
	 */
	public List<ParentDto>  getAllParentsByPkgId(Integer pkgId);
	
	public List<ParentDto>  getAllParentsByModelId(Integer modelId, String className);

	public List<PackageTreeMeta> getRootPackage();

	public List<PackageTreeMeta> getPackageByParentId(int parentId);

	public List<PackageTreeMeta> getPackageTree();

	public List<ModelMeta> getModelListByPackageId(int packageId, String type);
		
	
}
