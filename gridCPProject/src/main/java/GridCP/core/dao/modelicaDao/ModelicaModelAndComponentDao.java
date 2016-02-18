package GridCP.core.dao.modelicaDao;



import java.util.List;

import GridCP.core.dao.baseDao.IBaseDao;
import GridCP.core.domain.modelica.ModelicaModelAndComponent;


public interface ModelicaModelAndComponentDao extends IBaseDao<ModelicaModelAndComponent>{

	/**
	 *根据父类id获取该节点下的model
	 * @param parent_id
	 * @return
	 */
	public List<ModelicaModelAndComponent> getModelsByParentId(int parent_id);
	
	/**
	 *根据包节点获取该节点下的model
	 * @param modelicaPkgId
	 * @return
	 */
	public List<ModelicaModelAndComponent> getModelsByPkgId(int modelicaPkgId);
	
	/**
	 *根据父节点Id和类型获取model
	 * @param parent_id
	 * @param type
	 * @return
	 */
	public List<ModelicaModelAndComponent> getModelsByParentIdAndType(int parent_id,String type);
	
	/**
	 *根据父节点Id和类型获取model
	 * @param parent_id
	 * @param type
	 * @return
	 */
	public List<ModelicaModelAndComponent> getModelsByLikeModelNamedAndType(String modelName,String type);
}
