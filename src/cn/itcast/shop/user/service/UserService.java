package cn.itcast.shop.user.service;
import cn.itcast.shop.user.dao.UserDao;
import cn.itcast.shop.user.vo.User;
import org.springframework.transaction.annotation.Transactional;

/*�û�ģ��ҵ������*/
@Transactional
public class UserService {
	//UserDao
	private UserDao userDao;
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
//�û�����ѯ�ķ���
	public User findByUsername (String username){
		return userDao.findByUsername(username);
	}
	public void save(User user) {
		// TODO �Զ����ɵķ������
		user.setState(0);//0����δ����
		userDao.save(user);
	}
	public User login(User user) {
		// TODO �Զ����ɵķ������
		return userDao.login(user);
	}
}
