package cn.itcast.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.order.vo.Order;

//订单模块的dao层
public class OrderDao extends HibernateDaoSupport{
	//dao层的保存订单的方法
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}
	//dao层的我的订单的个数统计
	public Integer findByCountUid(Integer uid) {
		String hql="select count(*) from Order o where o.user.uid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,uid);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}
	//dao层我的订单的查询
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql="from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql,new Object[]{uid},begin,limit));
		return list;
	}

}
