package GridCP.core.dao.modelicaDao;

import GridCP.core.dao.baseDao.IBaseDao;
import GridCP.core.domain.modelica.ModelicaPkg;

public interface ModelicaPkgDao extends IBaseDao<ModelicaPkg>{

	public ModelicaPkg getModelicaPkg(int parentId);
	
	
}
