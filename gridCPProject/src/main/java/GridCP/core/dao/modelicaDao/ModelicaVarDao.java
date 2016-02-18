package GridCP.core.dao.modelicaDao;

import java.util.List;

import GridCP.core.dao.baseDao.IBaseDao;
import GridCP.core.domain.modelica.ModelicaVar;

public interface ModelicaVarDao extends IBaseDao<ModelicaVar>{

	public List<ModelicaVar> getModelicaVarByModelicaId(int model_id);
	
	public List<ModelicaVar> selectByIsDefaultAndModelicaId(int model_id);
	
}
