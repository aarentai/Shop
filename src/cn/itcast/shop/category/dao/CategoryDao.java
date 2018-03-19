package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

//一级分类的持久层
public class CategoryDao extends HibernateDaoSupport{
//dao层的查询所有一级分类的方法
	public List<Category> findAll() {
		// TODO 自动生成的方法存根
		String hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		return null;
	}

}
