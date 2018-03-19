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

//��������action
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	//ģ��������Ҫʹ�õĶ���
	private Order order=new Order();
	//ע��OrderService
	private OrderService orderService;
	//����page����
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

	//���ɶ����ķ���
	public String save(){
		//�������ݵ����ݿ�
		//�������ݲ�ȫ
		order.setOrdertime(new Date());
		order.setState(1);//1δ����	 2�Ѹ��δ����	 3�ѷ�����û�ջ� 	4�������
		//�ܼƵ������ǹ��ﳵ�е�����
		Cart cart=(Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			this.addActionError("���Ĺ��ﳵΪ��");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//���ö����еĶ�����
		for(CartItem cartItem:cart.getCartItems()){
			OrderItem orderItem=new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		
		//���ö����������û�
		User existUser=(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser==null){

			this.addActionError("����δ��½");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		//�������Ķ�����ʾ��ҳ��
		//ͨ��ֵջ����ʽ��ʾ
		//��չ��ﳵ
		cart.clearCart();
		return "saveSuccess";
	}
	
	//�ҵĶ����Ĳ�ѯ
	public String findByUid(){
		//�����û���id��ѯ
		User user =(User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		
		//����service
		PageBean<Order> pageBean=orderService.findByPageUid(user.getUid(),page);
		//����ҳ������ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
}
