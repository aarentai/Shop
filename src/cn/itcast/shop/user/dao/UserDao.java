package cn.itcast.shop.user.dao;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.itcast.shop.user.vo.User;
/*�û�ģ��־ò����*/

public class UserDao extends HibernateDaoSupport {
//�����Ʋ�ѯ�Ƿ��и��û�
	public User findByUsername(String username){
		String hql="from User where username = ? ";
		List<User> list= this.getHibernateTemplate().find(hql,username);
		if(list != null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}


	public void save(User user) {
		// TODO �Զ����ɵķ������
		this.getHibernateTemplate().save(user);
	}
//�û���½�ķ���
	public User login(User user) {
		// TODO �Զ����ɵķ������
		String hql="from User where username = ? and password";
		List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
