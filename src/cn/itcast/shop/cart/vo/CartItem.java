package cn.itcast.shop.cart.vo;

import cn.itcast.shop.product.vo.Product;

//购物项对象
public class CartItem {
	private Product product;
	private int count;
	private double subtotal;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	//小计自动计算
	public double getSubtotal() {
		return count*product.getShop_price();
	}
	
	
}
