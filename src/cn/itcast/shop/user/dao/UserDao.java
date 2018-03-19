package cn.itcast.shop.user.dao;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cn.itcast.shop.user.vo.User;
/*用户模块持久层代码*/

public class UserDao extends HibernateDaoSupport {
//按名称查询是否有该用户
	public User findByUsername(String username){
		String hql="from User where username = ? ";
		List<User> list= this.getHibernateTemplate().find(hql,username);
		if(list != null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}


	public void save(User user) {
		// TODO 自动生成的方法存根
		this.getHibernateTemplate().save(user);
	}
//用户登陆的方法
	public User login(User user) {
		// TODO 自动生成的方法存根
		String hql="from User where username = ? and password";
		List<User> list=this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}


}
