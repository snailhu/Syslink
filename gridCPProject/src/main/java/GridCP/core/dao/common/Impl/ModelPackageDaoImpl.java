package GridCP.core.dao.common.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import GridCP.core.dao.baseDao.IBaseDaoImpl;
import GridCP.core.dao.common.ModelPackageDao;
import GridCP.core.domain.common.ModelPackage;

@Repository
public class ModelPackageDaoImpl extends IBaseDaoImpl<ModelPackage>
implements ModelPackageDao{

	@Override
	public ModelPackage selectByNameAndParentId(String name, int parentId) {
		String hql = "from ModelPackage pkg where pkg.name = ? and pkg.parentId = ?";
		List<ModelPackage> list = this.list(hql, new Object[]{name,parentId});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ModelPackage> selectByParentIdAndPackageTypeId(int parentId,
			int packageTypeId) {
		String hql = "from ModelPackage pkg where pkg.parentId = ? and pkg.packageTypeId = ?";
		return this.list(hql, new Object[]{parentId,packageTypeId});
	}

	@Override
	public List<ModelPackage> selectByParentIdAndUserId(int parentId,int userId) {
		String hql = "from ModelPackage pkg where pkg.parentId=? and pkg.userId=? order by createDate";
		return this.list(hql, new Object[]{parentId,userId});
	}

	@Override
	public List<ModelPackage> selectByParentIdOrderByCreateDate(int parentId) {
		String hql = "from ModelPackage pkg where pkg.parentId=? order by createDate";
		return this.list(hql, new Object[]{parentId});
	}
	
}
