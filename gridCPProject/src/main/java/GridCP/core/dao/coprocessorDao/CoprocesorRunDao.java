package GridCP.core.dao.coprocessorDao;

import GridCP.core.dao.baseDao.IBaseDao;
import GridCP.core.domain.coprocessor.CoprocesorRun;

public interface CoprocesorRunDao extends IBaseDao<CoprocesorRun>{

	public CoprocesorRun getLastRunCoprocesorRun(int modelId);
}
