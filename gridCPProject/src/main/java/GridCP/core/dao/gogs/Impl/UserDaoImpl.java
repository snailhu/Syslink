package GridCP.core.dao.gogs.Impl;


import org.springframework.stereotype.Repository;

import GridCP.core.dao.baseDao.IBaseDaoImpl;
import GridCP.core.dao.gogs.UserDao;
import GridCP.core.gogsDomain.User;

@Repository
public class UserDaoImpl  extends IBaseDaoImpl<User> implements UserDao {

	@Override
	public User getUserByName(String username) {
		String sql = "from User us where us.name=:user_name";
		return (User) this.getSession().createQuery(sql).setParameter("user_name", username).uniqueResult();
	}
		
}
