package cn.tf.dao;

import org.apache.struts2.ServletActionContext;

import cn.tf.domain.User;
import cn.tf.service.BusinessService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport  implements ModelDriven<User>{

	private User user=new User();
	private BusinessService businessService;
	
	public String login(){
		User dbUser=businessService.login(user.getLogonName(),user.getLogonPwd());
		if(dbUser==null){
			addActionError("错误的用户名或密码");
			
			return LOGIN;
		}else{
			//向SESSION中添加登录信息
			ServletActionContext.getRequest().getSession().setAttribute("user", dbUser);
			return SUCCESS;
		
		}
	}
	
	
	
	
	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}




	public User getUser() {
		return user;
	}





	public void setUser(User user) {
		this.user = user;
	}





	@Override
	public User getModel() {
		
		return user;
	}
	
	

}
