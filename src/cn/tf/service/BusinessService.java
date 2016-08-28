package cn.tf.service;

import java.util.List;

import cn.tf.domain.User;

public interface  BusinessService {

	/**
	 * 用户登陆
	 * @param logonName 登陆名
	 * @param logonPwd 登陆密码
	 * @return 如果错误返回null
	 */
	User login(String logonName, String logonPwd);
	/**
	 * 保存新用户
	 * @param user
	 */
	void save(User user);
	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> findAll();
	/**
	 * 根据id删除一个用户
	 * @param userID
	 */
	void delete(Integer userID);
	/**
	 * 根据id查询一个用户
	 * @param userID
	 * @return
	 */
	User findOne(Integer userID);
	/**
	 * 更新
	 * @param user
	 */
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
