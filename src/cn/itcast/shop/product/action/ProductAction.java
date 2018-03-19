package cn.itcast.shop.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.category.service.CategoryService;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageBean;

//��Ʒ��action
public class ProductAction  extends ActionSupport implements ModelDriven<Product>{
//���ڽ������ݵ�ģ������
	private Product product=new Product();
	//ע����Ʒ��service
	private ProductService productService;
	//���ܵ�ǰ��ҳ��
	private int page;
	public void setPage(int page) {
		this.page = page;
	}

	//���ܷ���cid
	private Integer cid;
	private Integer csid;
	

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	//ע��һ�������find
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

	//������Ʒ��id���в�ѯ��Ʒ��ִ�з���
	public String findByPid(){
		//����service�ķ�����ɲ�ѯ
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//���ݷ����id��ѯ��Ʒ
	public String findByCid(){
		//List<Category> cList=categoryService.findAll();
		PageBean<Product> pageBean=productService.findByPageCid(cid,page);
		//��PageBean���뵽ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//���ݶ��������id��ѯ��Ʒ
	public String findByCsid(){
		//���ݶ��������id��ѯ��Ʒ
		PageBean<Product> pageBean = productService.findByPageCsid(csid, page);
		//��PageBean���뵽ֵջ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}
