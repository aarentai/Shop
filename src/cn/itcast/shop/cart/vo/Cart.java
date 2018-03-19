package cn.itcast.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

//购物车对象
public class Cart {
	//购物项集合 key为pid value为购物项
	private Map<Integer,CartItem> map=new LinkedHashMap<Integer,CartItem>();

	//购物总计
	private double total;
	public double getTotal() {
		return total;
	}

	//Cart对象中有一个叫cartItems属性
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	

	//购物车的功能
	//将购物项添加到购物车
	public void addCart(CartItem cartItem){
		//判断购物车中是否已经存在购物项
		//存在：数量增加，总计增加
		//不存在：添加购物项
		//获得商品id
		Integer pid=cartItem.getProduct().getPid();
		//判断购物车中是否已经存在该购物项
		if(map.containsKey(pid)){
			//存在
			CartItem _cartItem = map.get(pid);//获得购物车中原来的购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}
		else{
			//不存在
			map.put(pid, cartItem);
		}
		//设置总计的值
		total+=cartItem.getSubtotal();
	}
	
	//从购物车中移除购物项
	public void removeCart(Integer pid){
		//将购物项移除购物车
		CartItem cartItem=map.remove(pid);
		//总计-小计
		total-=cartItem.getSubtotal();
	}
	
	//清空购物车
	public void clearCart(){
		//将所有的购物项清空，总计设置为0
		total=0;
		map.clear();
	}
}

