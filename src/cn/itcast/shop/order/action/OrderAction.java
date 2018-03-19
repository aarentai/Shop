package cn.itcast.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.cart.vo.Cart;
import cn.itcast.shop.cart.vo.CartItem;
import cn.itcast.shop.order.service.OrderService;
import cn.itcast.shop.order.vo.Order;
import cn.itcast.shop.order.vo.OrderItem;
import cn.itcast.shop.user.vo.User;
import cn.itcast.shop.utils.PageBean;

//订单管理action
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	//模型驱动需要使用的对象
	private Order order=new Order();
	//注入OrderService
	private OrderService orderService;
	//接受page参数
	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public Order getModel() {
		return order;
	}

	//生成订单的方法
	public String save(){
		//保存数据到数据库
		//订单数据补全
		order.setOrdertime(new Date());
		order.setState(1);//1未付款	 2已付款，未发货	 3已发货，没收货 	4交易完成
		//总计的数据是购物车中的数据
		Cart cart=(Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			this.addActionError("您的购物车为空");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//设置订单中的订单项
		for(CartItem cartItem:cart.getCartItems()){
			OrderItem orderItem=new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		
		//设置订单所属的用户
		User existUser=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser==null){

			this.addActionError("您还未登陆");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		//将订单的对象显示到页面
		//通过值栈的形式显示
		//清空购物车
		cart.clearCart();
		return "saveSuccess";
	}
	
	//我的订单的查询
	public String findByUid(){
		//根据用户的id查询
		User user =(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		//调用service
		PageBean<Order> pageBean=orderService.findByPageUid(user.getUid(),page);
		//将分页数据显示到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
}
