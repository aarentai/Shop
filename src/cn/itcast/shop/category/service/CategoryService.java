package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;

//һ�������ҵ���
@Transactional
public class CategoryService {
	//ע��CategoryDao
	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao){
		this.categoryDao=categoryDao;
	}
//ҵ����ѯ����һ������ķ���
	public List<Category> findAll() {
		// TODO �Զ����ɵķ������
		return categoryDao.findAll();
	}
}
