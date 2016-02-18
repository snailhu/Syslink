package GridCP.core.dao.coprocessorDao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import GridCP.core.dao.baseDao.IBaseDaoImpl;
import GridCP.core.dao.coprocessorDao.CoprocessorModelDao;
import GridCP.core.domain.coprocessor.CoprocessorModel;

@Repository
public class CoprocessorModelDaoImpl extends IBaseDaoImpl<CoprocessorModel>
implements CoprocessorModelDao{

	@Override
	public CoprocessorModel getParentById(int id) {
		String sql = "from CoprocessorModel sm where sm.parentId=?";
		return (CoprocessorModel) this.getSession().createQuery(sql).setParameter(0, id).uniqueResult();
	}

	@Override
	public void deleteByPackageId(int modelPackageId) {
		String hql = "delete CoprocessorModel as cm where cm.modelPackageId = ?";
		this.getSession().createQuery(hql).setParameter(0, modelPackageId);
	}

	@Override
	public void deleteByParentIds(int[] ids) {
		String hql = "delete CoprocessorModel as cm where cm.parentId int (?)";
		this.getSession().createQuery(hql).setParameter(0, ids);
	}

	@Override
	public void deleteByModelParentId(int parentId) {
		String hql = "delete CoprocessorModel as cm where cm.parentId = ?";
		this.getSession().createQuery(hql).setParameter(0, parentId);
	}

	@Override
	public List<CoprocessorModel> getByparentIdAndLikeName(int parentId, String name) {
		name = "%"+name+"%";
		String hql = "from CoprocessorModel cm where cm.parentId=? and cm.name like ?";
		return this.list(hql, new Object[]{parentId,name});
	}
	
	
}
