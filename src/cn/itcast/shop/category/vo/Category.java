package cn.itcast.shop.category.vo;

import java.util.HashSet;
import java.util.Set;

import cn.itcast.shop.categorysecond.vo.CategorySecond;

public class Category {
private Integer cid;
private String cname;
//һ�������д�Ŷ�������ļ���
private Set<CategorySecond> categorySeconds=new HashSet<CategorySecond>();
public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
	this.categorySeconds = categorySeconds;
}
public Set<CategorySecond> getCategorySeconds() {
	return categorySeconds;
}
public Integer getCid() {
	return cid;
}
public void setCid(Integer cid) {
	this.cid = cid;
}
public String getCname() {
	return cname;
}
public void setCname(String cname) {
	this.cname = cname;
}
}
