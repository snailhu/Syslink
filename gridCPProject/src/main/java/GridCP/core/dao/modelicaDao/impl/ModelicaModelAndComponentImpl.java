package GridCP.core.dao.modelicaDao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import GridCP.core.common.Config;
import GridCP.core.dao.baseDao.IBaseDaoImpl;
import GridCP.core.dao.modelicaDao.ModelicaModelAndComponentDao;
import GridCP.core.domain.modelica.ModelicaModelAndComponent;
import GridCP.core.result.ExceptionResultInfo;
import GridCP.util.ResultUtil;

@Repository
public class ModelicaModelAndComponentImpl extends IBaseDaoImpl<ModelicaModelAndComponent> 
	implements ModelicaModelAndComponentDao {
	
	@Override
	public List<ModelicaModelAndComponent> getModelsByParentId(int parent_id){
		String hql = "from ModelicaModelAndComponent mmc where mmc.parentId=?";
		return this.list(hql, parent_id);		
	}

	@Override
	public List<ModelicaModelAndComponent> getModelsByPkgId(int modelicaPkgId) {
		String hql = "from ModelicaModelAndComponent mmc where mmc.parentPkgId=?";
		return this.list(hql, modelicaPkgId);	
	}

	@Override
	public List<ModelicaModelAndComponent> getModelsByParentIdAndType(
			int parent_id, String type) {
		String hql = "from ModelicaModelAndComponent mmc where mmc.parentId=? and mmc.type=? ";
		return this.list(hql, new Object[]{parent_id,type});	
	}

	@Override
	public List<ModelicaModelAndComponent> getModelsByLikeModelNamedAndType(
			String modelName, String type) {
		modelName = "%"+modelName+"%";
		String hql = "from ModelicaModelAndComponent mmc where mmc.modelName like ? and mmc.type=? ";
		return this.list(hql, new Object[]{modelName,type});	
	}		
}
