package cn.itcast.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.itcast.shop.category.dao.CategoryDao;
import cn.itcast.shop.category.vo.Category;

//一级分类的业务层
@Transactional
public class CategoryService {
	//注入CategoryDao
	private CategoryDao categoryDao;
	
	public void setCategoryDao(CategoryDao categoryDao){
		this.categoryDao=categoryDao;
	}
//业务层查询所有一级分类的方法
	public List<Category> findAll() {
		// TODO 自动生成的方法存根
		return categoryDao.findAll();
	}
}
