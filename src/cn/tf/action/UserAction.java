package cn.tf.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.TokenHelper;

import cn.tf.domain.User;
import cn.tf.service.BusinessService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;

public class UserAction extends ActionSupport  implements ModelDriven<User>{

	private User user=new User();
	private BusinessService businessService;
	
	
	
	public String editUI(){
		user=businessService.findOne(user.getUserID());
		ValueStack  vs=ActionContext.getContext().getValueStack();
		vs.push(user);

		return SUCCESS;
	}
	
	public String editUser(){
		
		try {
			
			if(StringUtils.isNotBlank(uploadFileName)){
				//添加用户:文件上传
				//用户输入的信息都在user中，但没有path和filename
				String storeDirectory = ServletActionContext.getServletContext().getRealPath("/files");
				String childDirectory = makeChildDirectory(storeDirectory);
				user.setPath(childDirectory);//设置存放文件的子目录
				//搞文件名 uploadFileName：a.doc---->GUID_a.doc
				String newfilename = TokenHelper.generateGUID()+"_"+uploadFileName;
				user.setFilename(newfilename);
				//文件上传
				FileUtils.moveFile(upload, new File(storeDirectory+File.separator+childDirectory+File.separator+newfilename));
				
			}else{
				User dbUser=businessService.findOne(user.getUserID());
				user.setPath(dbUser.getPath());
				user.setFilename(dbUser.getFilename());
	
			}
			businessService.update(user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private InputStream inStream;
	private String downloadFilename;
	
	public String download(){
		
		user=businessService.findOne(user.getUserID());
		ServletContext sc=ServletActionContext.getServletContext();
		String rootDirectory=sc.getRealPath("/files");
		
		try {
			inStream=new FileInputStream(rootDirectory+File.separator+user.getPath()+File.separator+user.getFilename());
			
			//截取文件名
			String guidFilename = user.getFilename();
			downloadFilename = URLEncoder.encode(guidFilename.substring(guidFilename.indexOf("_")+1), "UTF-8");
			return SUCCESS;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		
		
	}
	
	
	
	
	
	
	
	public String view(){
		user=businessService.findOne(user.getUserID());
		return SUCCESS;
	}
	
	
	
	
	
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
	
	private File upload;
	private String uploadFileName;
	public String addUser(){
		try {
			//添加用户:文件上传
			//用户输入的信息都在user中，但没有path和filename
			String storeDirectory = ServletActionContext.getServletContext().getRealPath("/files");
			String childDirectory = makeChildDirectory(storeDirectory);
			user.setPath(childDirectory);//设置存放文件的子目录
			//搞文件名 uploadFileName：a.doc---->GUID_a.doc
			String newfilename = TokenHelper.generateGUID()+"_"+uploadFileName;
			user.setFilename(newfilename);
			//文件上传
			FileUtils.moveFile(upload, new File(storeDirectory+File.separator+childDirectory+File.separator+newfilename));
			businessService.save(user);
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ERROR;
		}
	}
	//创建存放上传文件的子目录
	private String makeChildDirectory(String storeDirectory) {
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		String path = df.format(now);
		
		File file = new File(storeDirectory,path);
		if(!file.exists())
			file.mkdirs();
		return path;
	}

	
	
	public String delUser(){
		businessService.delete(user.getUserID());
		return SUCCESS;
	}
	
	private List<User> users;
	public String listUser(){
		users = businessService.findAll();
		return SUCCESS;
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

	public User getModel() {
		return user;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}







	public InputStream getInStream() {
		return inStream;
	}







	public void setInStream(InputStream inStream) {
		this.inStream = inStream;
	}







	public String getDownloadFilename() {
		return downloadFilename;
	}







	public void setDownloadFilename(String downloadFilename) {
		this.downloadFilename = downloadFilename;
	}
	



	

}
