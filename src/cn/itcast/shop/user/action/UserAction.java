package cn.itcast.shop.user.action;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
/*用户模块action的类*/
public class UserAction  extends  ActionSupport implements ModelDriven<User>{
	private User user = new User();
	private UserService userService;
	
	public void setUserservice(UserService userService) {
		this.userService = userService;
	}

/*跳转到注册页面的执行方法*/
	public String  registPage(){
		return "registPage";
	}
	/*ajax进行*/
	public String findByName() throws IOException{
		
		/*调用service查询*/
		User existUser = userService.findByUsername(user.getUsername());
		HttpServletResponse response=  ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		
		if(existUser != null){
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}
		else{
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	public String regist(){
		userService.save(user);
		return NONE;
	}
	@Override
	public User getModel() {
		// TODO 自动生成的方法存根
		return user;
	}
//转跳到登陆页面
	public String loginPage(){
		return "loginPage";
	}
	//登陆的方法
	public String login(){
		User existUser=userService.login(user);
		if(existUser==null){
			//登陆失败
			this.addActionError("登陆失败，登录名或密码错误或用户未激活");
			return LOGIN;
		}
		else{
			//登陆 成功
			//将用户信息存入session
			ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
		//页面跳转
			return "loginSuccess";
		}
	}
	
	//用户退出的方法
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
