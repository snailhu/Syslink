package GridCP.core.dao.common;

import java.util.List;

import GridCP.core.dao.baseDao.IBaseDao;
import GridCP.core.domain.common.ModelPackage;

public interface ModelPackageDao extends IBaseDao<ModelPackage>{

	public ModelPackage selectByNameAndParentId(String name,int parentId);
	
	public List<ModelPackage> selectByParentIdAndPackageTypeId(int parentId,int packageTypeId);
	
	public List<ModelPackage> selectByParentIdAndUserId(int parentId,int userId);
	
	public List<ModelPackage> selectByParentIdOrderByCreateDate(int parentId);
}
