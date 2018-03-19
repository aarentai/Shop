package cn.itcast.shop.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

//商品的action
public class ProductAction  extends ActionSupport implements ModelDriven<Product>{
//用于接收数据的模型驱动
	private Product product=new Product();
	//注入商品的service
	private ProductService productService;
	//接受当前的页数
	private int page;
	public void setPage(int page) {
		this.page = page;
	}

	//接受分类cid
	private Integer cid;
	private Integer csid;
	

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	//注入一级分类的find
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}
	
	public Product getModel() {
		return product;
	}
	
public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	//根据商品的id进行查询商品：执行方法
	public String findByPid(){
		//调用service的方法完成查询
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//根据分类的id查询商品
	public String findByCid(){
		//List<Category> cList=categoryService.findAll();
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		//将PageBean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//根据二级分类的id查询商品
	public String findByCsid(){
		//根据二级分类的id查询商品
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		//将PageBean存入到值栈中
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
