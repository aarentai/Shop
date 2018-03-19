package cn.itcast.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.order.dao.OrderDao;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.utils.PageBean;

//订单模块的业务层
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//保存订单的业务层代码
	public void save(Order order) {
		orderDao.save(order);
	}
	//我的订单业务层代码
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean=new PageBean<Order>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		Integer limit=5;
		pageBean.setLimit(limit);
		//设置总记录数
		Integer totalCount=null;
		totalCount=orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		Integer totalPage=null;
		if(totalCount %limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//设置每页现实的数据集合
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
}
