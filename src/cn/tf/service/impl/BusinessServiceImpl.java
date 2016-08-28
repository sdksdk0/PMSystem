package cn.tf.service.impl;

import java.util.List;

import cn.tf.dao.UserDao;
import cn.tf.domain.User;
import cn.tf.service.BusinessService;

public class BusinessServiceImpl implements BusinessService{
	
	private UserDao userDao;
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User login(String logonName, String logonPwd) {
		return userDao.find(logonName,logonPwd);
	}

	public void save(User user) {
		userDao.save(user);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public void delete(Integer userID) {
		userDao.delete(userID);
	}

	public User findOne(Integer userID) {
		return userDao.findOne(userID);
	}

	public void update(User user) {
		if(user==null||user.getUserID()==null)
			throw new IllegalArgumentException("参数不全");
		userDao.update(user);
	}

	public List<User> findByCondition(String userName, String sex,
			String education, String isUpload) {
		return userDao.findByCondition(userName,sex,education,isUpload);
	}

	
	

}
