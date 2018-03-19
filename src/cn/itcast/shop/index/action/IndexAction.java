package cn.itcast.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

/*首页访问的Action*/
public class IndexAction extends ActionSupport {
	//执行的访问首页的方法
	//注入一级分类的Service
	private CategoryService categoryService;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

public String execute(){
	//查询所有一级分类的集合
	List<Category> cList=categoryService.findAll();
	//将一级分类存入到session的范围
	ActionContext.getContext().getSession().put("cList", cList);
	//查询热门商品
	List<Product> hList=productService.findHot();
	//保存到值栈中
	ActionContext.getContext().getValueStack().set("hList",hList);
	//查询最新商品
	List<Product> nList=productService.findNew();
	//保存到值栈中
	ActionContext.getContext().getValueStack().set("nList",nList);
	return "index";
}
}
