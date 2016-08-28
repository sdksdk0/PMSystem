package cn.tf.dao;

import java.util.List;

import cn.tf.domain.User;

public interface UserDao {
	
	User find(String logonName, String logonPwd);

	void save(User user);

	List<User> findAll();

	void delete(Integer userID);

	User findOne(Integer userID);

	void update(User user);
	/**
	 * 根据条件查询记录
	 * @param userName 为null,""代表忽略该条件
	 * @param sex为null,""代表忽略该条件
	 * @param education为null,""代表忽略该条件
	 * @param isUpload 为null,""代表忽略该条件,1 有 2没有
	 * @return
	 */
	List<User> findByCondition(String userName, String sex, String education,
			String isUpload);


}
