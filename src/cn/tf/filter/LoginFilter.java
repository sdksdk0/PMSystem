package cn.tf.filter;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginFilter extends  MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		Object obj=session.getAttribute("user");
		if(obj==null){
			return "login";
		}	
		String rtValue=invocation.invoke();
		return rtValue;
	}

}
