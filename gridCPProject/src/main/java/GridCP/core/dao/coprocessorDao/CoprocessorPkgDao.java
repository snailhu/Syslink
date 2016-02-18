package GridCP.core.dao.coprocessorDao;

import GridCP.core.dao.baseDao.IBaseDao;
import GridCP.core.domain.coprocessor.CoprocesorPKg;

public interface CoprocessorPkgDao extends IBaseDao<CoprocesorPKg> {

	public CoprocesorPKg getCoprocesorPkg(int parentId);
}
