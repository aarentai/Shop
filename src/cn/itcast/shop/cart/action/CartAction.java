package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

public class CartAction extends ActionSupport{
	//����pid
	private Integer pid;
	//����count
	private Integer count;
	//ע����Ʒservice
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	//����������ӵ����ﳵ
	public String addCart(){
		//��װһ��CartItem����
		CartItem cartItem=new CartItem();
		//��������
		cartItem.setCount(count);
		//����pid��ѯ��Ʒ
		Product product=productService.findByPid(pid);
		//������Ʒ
		cartItem.setProduct(product);
		//����������ӵ����ﳵ
		//���ﳵ����session��
		Cart cart=getCart();
		
		return "addCart";
	}
	//��չ��ﳵ��ִ�з���
	public String clearCart(){
		//��ù��ﳵ�Ķ���
		Cart cart=getCart();
		//���ù��ﳵ����շ���
		cart.clearCart();
		return "clearCart";
	}
	
	//�ӹ��ﳵ���Ƴ�������
	public String removeCart(){
		//��ù��ﳵ����
		Cart cart=getCart();
		//���ù��ﳵ���Ƴ��ķ���
		cart.removeCart(pid);
		//����ҳ��
		return "removeCart";
	}
	//�ҵĹ��ﳵ
	public String myCart(){
		return "myCart";
	}
//��session�л�ù��ﳵ
	private Cart getCart() {
		Cart cart= (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart",cart);
		}
		return cart;
	}
}
