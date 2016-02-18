package GridCP.core.dao.coprocessorDao.Impl;


import org.springframework.stereotype.Repository;

import GridCP.core.dao.baseDao.IBaseDaoImpl;
import GridCP.core.dao.coprocessorDao.CoprocessorPkgDao;
import GridCP.core.domain.coprocessor.CoprocesorPKg;

@Repository
public class CoprocessorPkgDaoImpl extends IBaseDaoImpl<CoprocesorPKg> implements CoprocessorPkgDao  {

	@Override
	public CoprocesorPKg getCoprocesorPkg(int parentId) {
		String sql = "from ModelicaPkg mp where mp.parentId=? ";
		return (CoprocesorPKg) this.getSession().createQuery(sql).setParameter(0, parentId).uniqueResult();
	
	}

	
}
