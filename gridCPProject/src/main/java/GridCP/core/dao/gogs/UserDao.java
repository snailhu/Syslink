package GridCP.core.dao.gogs;

import GridCP.core.dao.baseDao.IBaseDao;
import GridCP.core.gogsDomain.User;

public interface UserDao extends IBaseDao<User> {
	
	public User getUserByName(String username);
}