package GridCP.core.dao.coprocessorDao;

import java.util.List;

import GridCP.core.dao.baseDao.IBaseDao;
import GridCP.core.domain.coprocessor.CoprocessorModel;

public interface CoprocessorModelDao extends IBaseDao<CoprocessorModel>{

	/**
	 * 根据id 获取父类
	 * @param id
	 * @return
	 */
	public CoprocessorModel getParentById(int id);
	
	public void deleteByPackageId(int modelPackageId);
	
	public void deleteByParentIds(int[] ids);
	
	public void deleteByModelParentId(int parentId);
	
	public List<CoprocessorModel> getByparentIdAndLikeName(int parentId,String name);
}
