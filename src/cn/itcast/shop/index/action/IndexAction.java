package cn.itcast.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.category.vo.Category;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

/*��ҳ���ʵ�Action*/
public class IndexAction extends ActionSupport {
	//ִ�еķ�����ҳ�ķ���
	//ע��һ�������Service
	private CategoryService categoryService;
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

public String execute(){
	//��ѯ����һ������ļ���
	List<Category> cList=categoryService.findAll();
	//��һ��������뵽session�ķ�Χ
	ActionContext.getContext().getSession().put("cList", cList);
	//��ѯ������Ʒ
	List<Product> hList=productService.findHot();
	//���浽ֵջ��
	ActionContext.getContext().getValueStack().set("hList",hList);
	//��ѯ������Ʒ
	List<Product> nList=productService.findNew();
	//���浽ֵջ��
	ActionContext.getContext().getValueStack().set("nList",nList);
	return "index";
}
}
