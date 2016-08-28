package cn.tf.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;

import cn.tf.dao.UserDao;
import cn.tf.domain.User;
import cn.tf.utils.C3P0Util;

public class UserDaoImpl implements UserDao{

	private QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());

	public User find(String logonName, String logonPwd) {
		try {
			return qr.query(
					"select * from S_User where logonName=? and logonPwd=?",
					new BeanHandler<User>(User.class), logonName, logonPwd);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void save(User user) {
		try {
			qr.update("insert into S_User (userName,logonName,logonPwd,sex,birthday,education,telephone,interest,path,filename,remark) values (?,?,?,?,?,?,?,?,?,?,?)", 
					user.getUserName(),user.getLogonName(),user.getLogonPwd(),user.getSex(),
					user.getBirthday(),user.getEducation(),user.getTelephone(),user.getInterest(),user.getPath(),
					user.getFilename(),user.getRemark());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<User> findAll() {
		try {
			return qr.query(
					"select * from S_User",
					new BeanListHandler<User>(User.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Integer userID) {
		try {
			qr.update("delete from S_User where userID=?",
					userID);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public User findOne(Integer userID) {
		try {
			return qr.query(
					"select * from S_User where userID=?",
					new BeanHandler<User>(User.class), userID);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(User user) {
		try {
			qr.update("update S_User set userName=?,logonName=?,logonPwd=?,sex=?,birthday=?,education=?,telephone=?,interest=?,path=?,filename=?,remark=? where userID=?", 
					user.getUserName(),user.getLogonName(),user.getLogonPwd(),user.getSex(),
					user.getBirthday(),user.getEducation(),user.getTelephone(),user.getInterest(),user.getPath(),
					user.getFilename(),user.getRemark(),user.getUserID());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<User> findByCondition(String userName, String sex,
			String education, String isUpload) {
		
		//判断4个参数是否都是null或“”
		if(StringUtils.isBlank(userName)&&StringUtils.isBlank(sex)&&StringUtils.isBlank(education)&&StringUtils.isBlank(isUpload)){
			//忽略条件
			return findAll();
		}
		List<String> params = new ArrayList<String>();//放参数
		StringBuffer sb = new StringBuffer("select * from S_User where 1=1 ");
		if(StringUtils.isNotBlank(userName)){
			//用户名不为空
			sb.append(" and userName like ? ");
			params.add("%"+userName+"%");
		}
		if(StringUtils.isNotBlank(sex)){
			sb.append(" and sex=? ");
			params.add(sex);
		}
		if(StringUtils.isNotBlank(education)){
			sb.append(" and education=? ");
			params.add(education);
		}
		if(StringUtils.isNotBlank(isUpload)){
			if("1".equals(isUpload)){
				//有简历的
				sb.append(" and filename is not null");
			}
			if("2".equals(isUpload)){
				sb.append(" and filename is null");
			}
		}
		try {
			return qr.query(sb.toString(), new BeanListHandler<User>(User.class),params.toArray());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
