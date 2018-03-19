package cn.itcast.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

//���ﳵ����
public class Cart {
	//������� keyΪpid valueΪ������
	private Map<Integer,CartItem> map=new LinkedHashMap<Integer,CartItem>();

	//�����ܼ�
	private double total;
	public double getTotal() {
		return total;
	}

	//Cart��������һ����cartItems����
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	

	//���ﳵ�Ĺ���
	//����������ӵ����ﳵ
	public void addCart(CartItem cartItem){
		//�жϹ��ﳵ���Ƿ��Ѿ����ڹ�����
		//���ڣ��������ӣ��ܼ�����
		//�����ڣ���ӹ�����
		//�����Ʒid
		Integer pid=cartItem.getProduct().getPid();
		//�жϹ��ﳵ���Ƿ��Ѿ����ڸù�����
		if(map.containsKey(pid)){
			//����
			CartItem _cartItem = map.get(pid);//��ù��ﳵ��ԭ���Ĺ�����
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}
		else{
			//������
			map.put(pid, cartItem);
		}
		//�����ܼƵ�ֵ
		total+=cartItem.getSubtotal();
	}
	
	//�ӹ��ﳵ���Ƴ�������
	public void removeCart(Integer pid){
		//���������Ƴ����ﳵ
		CartItem cartItem=map.remove(pid);
		//�ܼ�-С��
		total-=cartItem.getSubtotal();
	}
	
	//��չ��ﳵ
	public void clearCart(){
		//�����еĹ�������գ��ܼ�����Ϊ0
		total=0;
		map.clear();
	}
}

