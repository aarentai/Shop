package cn.itcast.shop.user.service;
import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import org.springframework.transaction.annotation.Transactional;

/*用户模块业务层代码*/
@Transactional
public class UserService {
	//UserDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
//用户名查询的方法
	public User findByUsername (String username){
		return userDao.findByUsername(username);
	}
	public void save(User user) {
		// TODO 自动生成的方法存根
		user.setState(0);//0代表未激活
		userDao.save(user);
	}
	public User login(User user) {
		// TODO 自动生成的方法存根
		return userDao.login(user);
	}
}
