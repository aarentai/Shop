package cn.itcast.shop.user.action;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.shop.user.service.UserService;
import cn.itcast.shop.user.vo.User;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
/*�û�ģ��action����*/
public class UserAction  extends  ActionSupport implements ModelDriven<User>{
	private User user = new User();
	private UserService userService;
	
	public void setUserservice(UserService userService) {
		this.userService = userService;
	}

/*��ת��ע��ҳ���ִ�з���*/
	public String  registPage(){
		return "registPage";
	}
	/*ajax����*/
	public String findByName() throws IOException{
		
		/*����service��ѯ*/
		User existUser = userService.findByUsername(user.getUsername());
		HttpServletResponse response=  ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		
		if(existUser != null){
			response.getWriter().println("<font color='red'>�û����Ѿ�����</font>");
		}
		else{
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}

	public String regist(){
		userService.save(user);
		return NONE;
	}
	@Override
	public User getModel() {
		// TODO �Զ����ɵķ������
		return user;
	}
//ת������½ҳ��
	public String loginPage(){
		return "loginPage";
	}
	//��½�ķ���
	public String login(){
		User existUser=userService.login(user);
		if(existUser==null){
			//��½ʧ��
			this.addActionError("��½ʧ�ܣ���¼�������������û�δ����");
			return LOGIN;
		}
		else{
			//��½ �ɹ�
			//���û���Ϣ����session
			ServletActionContext.getRequest().getSession().setAttribute("existUser",existUser);
		//ҳ����ת
			return "loginSuccess";
		}
	}
	
	//�û��˳��ķ���
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
