package cn.itcast.shop.product.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.shop.product.vo.Product;
import cn.itcast.shop.utils.PageHibernateCallback;

//持久层
public class ProductDao extends HibernateDaoSupport{
//首页上热门商品查询
	public List<Product> findHot() {
		// 使用离线条件查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		//is_hot==1
		criteria.add(Restriction.eq("is_hot",1));
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return null;
	}
	//首页上最新商品查询
	public List<Product> findNew() {
		// 使用离线条件查询
		DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
		criteria.addOrder(Order.desc("pdate"));
		List<Product> list=this.getHibernateTemplate().findByCriteria(criteria,0,10);
		return null;
	}
	public Product findByPid(Integer pid) {
		this.getHibernateTemplate().get(Product.class,pid);
		return this.getHibernateTemplate().get(Product.class,pid);
	}
	//根据分类的id查询商品的个数
	public int findCountCid(Integer cid) {
		String hql="select count(*) from Product p where p.categorySecond.category.cid = ?";
		List<Long> list=this.getHibernateTemplate().find(hql,cid);
		if(list!=null&&list.size()>0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	//根据分类id查询商品的集合
	public List<Product> findByPageCid(Integer cid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[]{cid},begin,limit));
	
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}
	
	//根据二级分类查询商品个数
	public int findCountCsid(Integer csid) {
		String hql="select count(*) from Product p where p.categorySecond.csid=?";
		List<Long> list=this.getHibernateTemplate().find(hql,csid);
		if(list!=null&&list.size()>0)
		{
			return list.get(0).intValue();
		}
		return 0;
	}
	
	//根据二级分类查询商品信息
	public List<Product> findByPageCsid(Integer csid, int begin, int limit) {
		String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
		List<Product> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Product>(hql,new Object[][csid],begin,limit));
		if(list != null && list.size()>0){
			return list;
		}
		return null;
	}


}
