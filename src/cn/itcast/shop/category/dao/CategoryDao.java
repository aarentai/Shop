package cn.itcast.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.category.vo.Category;

//һ������ĳ־ò�
public class CategoryDao extends HibernateDaoSupport{
//dao��Ĳ�ѯ����һ������ķ���
	public List<Category> findAll() {
		// TODO �Զ����ɵķ������
		String hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		return null;
	}

}
