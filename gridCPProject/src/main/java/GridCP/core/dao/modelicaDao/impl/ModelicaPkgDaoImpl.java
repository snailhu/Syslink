package GridCP.core.dao.modelicaDao.impl;

import org.springframework.stereotype.Repository;

import GridCP.core.dao.baseDao.IBaseDaoImpl;
import GridCP.core.dao.modelicaDao.ModelicaPkgDao;
import GridCP.core.domain.modelica.ModelicaPkg;

@Repository
public class ModelicaPkgDaoImpl extends IBaseDaoImpl<ModelicaPkg>
implements ModelicaPkgDao{

	@Override
	public ModelicaPkg getModelicaPkg(int parentId) {
		String sql = "from ModelicaPkg mp where mp.parentId=? ";
		return (ModelicaPkg) this.getSession().createQuery(sql).setParameter(0, parentId).uniqueResult();
	}
	
}
