package vote509.dao;

import vote509.entity.User;

public interface IUserDAO {

	/**
	 * 插入用户
	 */
	public int insert(User record);

	/**
	 * 更新用户全部数据
	 */
	public int update(User record);
	
	/**
	 * 更新用户名
	 * @return
	 */
	public int update(int id, String username);
	
	/**
	 * 更新头像
	 * @return
	 */
	public int updateHeader(int id, String header);
	
	/**
	 * 更新用户的邮箱或密码
	 * @return
	 */
	public int update(int id, String email, String password);
	
	

	public int deleteById(Integer id);
	
	/**
	 * 按邮箱和密码进行查询
	 * 
	 * @param email 邮箱
	 * @param password 密码
	 * @param viewName 视图名
	 * @return
	 */
	public User selectByEmailAndPassword(String email, String password);
	
	/**
	 * 获取用户数
	 * @return 用户数量
	 */
	public int countUser();

	/**
	 * 通过id获取用户信息
	 */
	public User selectById(int id);
}
