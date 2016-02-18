package GridCP.core.dao.modelicaDao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import GridCP.core.dao.baseDao.IBaseDaoImpl;
import GridCP.core.dao.modelicaDao.ModelicaVarDao;
import GridCP.core.domain.modelica.ModelicaVar;
@Repository
public class ModelicaVarDaoImpl extends IBaseDaoImpl<ModelicaVar>
implements ModelicaVarDao{

	@Override
	public List<ModelicaVar> getModelicaVarByModelicaId(int model_id) {
		String hql = "from ModelicaVar mv where mv.modelicaModelandComponent.id=?";
		return this.list(hql,model_id);
	}

	@Override
	public List<ModelicaVar> selectByIsDefaultAndModelicaId(int model_id) {
		return null;
	}

}
