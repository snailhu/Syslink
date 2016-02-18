package GridCP.core.service.userOperateService.Impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import GridCP.core.dao.gogs.UserDao;
import GridCP.core.gogsDomain.User;
import GridCP.core.service.userOperateService.UserService;


@Service
@Transactional(value="gogs_transactionManager")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao  userDao;
	
	@Override
	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}

}
