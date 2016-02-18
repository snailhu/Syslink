package GridCP.core.dao.coprocessorDao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import GridCP.core.dao.baseDao.IBaseDaoImpl;
import GridCP.core.dao.coprocessorDao.CoprocesorRunDao;
import GridCP.core.domain.coprocessor.CoprocesorRun;

@Repository
public class CoprocesorRunDaoImpl extends IBaseDaoImpl<CoprocesorRun>
implements CoprocesorRunDao{

	@Override
	public CoprocesorRun getLastRunCoprocesorRun(int modelId) {
		long count = this.getTotalCount("coprocessorModel.coprocessorId", modelId);
		if(count > 0){
			List<CoprocesorRun> list = this.findByParam("runNum", Integer.parseInt(String.valueOf(count)));
			if(list != null && list.size() > 0){
				return list.get(0);				
			}
		}
		return null;
	}

}
