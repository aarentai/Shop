package cn.itcast.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.product.service.ProductService;
import cn.itcast.shop.product.vo.Product;

public class CartAction extends ActionSupport{
	//接受pid
	private Integer pid;
	//接受count
	private Integer count;
	//注入商品service
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
	//将购物项添加到购物车
	public String addCart(){
		//封装一个CartItem对象
		CartItem cartItem=new CartItem();
		//设置数量
		cartItem.setCount(count);
		//根据pid查询商品
		Product product=productService.findByPid(pid);
		//设置商品
		cartItem.setProduct(product);
		//将购物项添加到购物车
		//购物车存在session中
		Cart cart=getCart();
		
		return "addCart";
	}
	//清空购物车的执行方法
	public String clearCart(){
		//获得购物车的对象
		Cart cart=getCart();
		//调用购物车的清空方法
		cart.clearCart();
		return "clearCart";
	}
	
	//从购物车中移除购物项
	public String removeCart(){
		//获得购物车对象
		Cart cart=getCart();
		//调用购物车中移除的方法
		cart.removeCart(pid);
		//返回页面
		return "removeCart";
	}
	//我的购物车
	public String myCart(){
		return "myCart";
	}
//从session中获得购物车
	private Cart getCart() {
		Cart cart= (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart",cart);
		}
		return cart;
	}
}
