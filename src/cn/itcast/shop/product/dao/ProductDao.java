package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

//�־ò�
public class ProductDao extends HibernateDaoSupport{
//��ҳ��������Ʒ��ѯ
	public List<Product> findHot() {
		// ʹ������������ѯ
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//is_hot==1
		criteria.add(Restriction.eq("is_hot",1));
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return null;
	}
	//��ҳ��������Ʒ��ѯ
	public List<Product> findNew() {
		// ʹ������������ѯ
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return null;
	}
	public Product findByPid(Integer pid) {
		this.getHibernateTemplate().get(Product.class,pid);
		return this.getHibernateTemplate().get(Product.class,pid);
	}
	//���ݷ����id��ѯ��Ʒ�ĸ���
	public int findCountCid(Integer cid) {
		String hql="select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,cid);
		if(list!=null&&list.size()>0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	//���ݷ���id��ѯ��Ʒ�ļ���
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[]{cid},begin,limit));
	
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	
	//���ݶ��������ѯ��Ʒ����
	public int findCountCsid(Integer csid) {
		String hql="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,csid);
		if(list!=null&&list.size()>0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//���ݶ��������ѯ��Ʒ��Ϣ
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[][csid],begin,limit));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}


}
