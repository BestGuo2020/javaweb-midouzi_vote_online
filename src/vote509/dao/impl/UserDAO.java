package vote509.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vote509.dao.IUserDAO;
import vote509.dbconnect.DBConnector;
import vote509.entity.User;

public class UserDAO implements IUserDAO {

	/**
	 * 插入用户
	 */
	public int insert(User record) {

		Connection connection = DBConnector.getConn();
		PreparedStatement ps = null;
		boolean b = false;
		try {
			
			StringBuilder sb = new StringBuilder();
			sb.append("insert into user (username,password");
			
			if(record.getHeader() != null) {
				sb.append(",header");
			}
			sb.append(",email)");
			sb.append("values (?,?");
			
			if(record.getHeader() != null) {
				sb.append(",?");
			}
			sb.append(",?)");
			
			ps = connection.prepareStatement(sb.toString());
			ps.setString(1, record.getUsername());
		
			ps.setString(2, record.getPassword());
			
			if(record.getHeader() != null) {
				ps.setString(3, record.getHeader());
				ps.setString(4, record.getEmail());
			} else {
				ps.setString(3, record.getEmail());
			}
		
			
			
			b = ps.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, ps, null);
		}
		return b ? 1 : 0;
	}

	/**
	 * 更新用户全部数据
	 */
	public int update(User record) {
		// update admin set username=?, password=?, email=? where id=?
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int status = 0;
		try {
			pre = connection.prepareStatement("update user set username=?, password=?, email=? where id=?");
			pre.setString(1, record.getUsername());
			pre.setString(2, record.getPassword());
			pre.setString(3, record.getEmail());
			pre.setInt(4, record.getId());
			status = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return status;
	}
	
	/**
	 * 更新用户名
	 * @return
	 */
	public int update(int id, String username) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int stat = 0;
		try {
			pre = connection.prepareStatement("update user set username=? where id=?");
			pre.setString(1, username);
			pre.setInt(2, id);
			stat = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return stat;
	}
	
	/**
	 * 更新头像
	 * @return
	 */
	public int updateHeader(int id, String header) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int stat = 0;
		try {
			// 判断头像是否上传
			if(header != null) {
				pre = connection.prepareStatement("update user set header=? where id=?");
				pre.setString(1, header);
				pre.setInt(2, id);
				stat = pre.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return stat;
	}
	
	/**
	 * 更新用户的邮箱或密码
	 * @return
	 */
	public int update(int id, String email, String password) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int stat = 0;
		try {
			if(password == null || "".equals(password)) {
				pre = connection.prepareStatement("update user set email=? where id=?");
				pre.setString(1, email);
				pre.setInt(2, id);
			} else {
				pre = connection.prepareStatement("update user set email=?, password=? where id=?");
				pre.setString(1, email);
				pre.setString(2, password);
				pre.setInt(3, id);
			}
			stat = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return stat;
	}
	
	
	/**
	 * 删除账户
	 */
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		Connection connection = DBConnector.getConn();
		PreparedStatement ps = null;
		int res = 0;
		
		try {
			String sql = "delete from user where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			res = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			DBConnector.close(connection, ps, null);
		}
		return res;
	}
	
	/**
	 * 按邮箱和密码进行查询
	 * 
	 * @param email 邮箱
	 * @param password 密码
	 * @param viewName 视图名
	 * @return
	 */
	public User selectByEmailAndPassword(String email, String password) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement ps = null;
		
		User user = null;
		
		try {
			String sql = "select * from user where email = ? and password = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				user = new User();
				user.setId(res.getInt("id"));
				user.setUsername(res.getString("username"));
				user.setHeader(res.getString("header"));
				user.setEmail(res.getString("email"));
				user.setRole(res.getString("role"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			DBConnector.close(connection, ps, null);
		}
		return user;
	}
	
	/**
	 * 获取用户数
	 * @return 用户数量
	 */
	public int countUser() {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = -1;
		try {
			pre = connection.prepareStatement("select count(*) count from user");
			res = pre.executeQuery();
			if(res.next())
				count = res.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}
	
	@Override
	public User selectById(int id) {
		Connection connection = DBConnector.getConn();
		PreparedStatement ps = null;
		
		User user = null;
		
		try {
			String sql = "select * from user where id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				user = new User();
				user.setId(res.getInt("id"));
				user.setUsername(res.getString("username"));
				user.setHeader(res.getString("header"));
				user.setEmail(res.getString("email"));
				user.setRole(res.getString("role"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} finally {
			DBConnector.close(connection, ps, null);
		}
		return user;
		
	}
	
	public static void main(String[] args) {
		
		User record = new User();
		record.setUsername("Hehe");
		record.setPassword("123456");
		record.setHeader("123.jpg");
		record.setEmail("12346@163.com");
		UserDAO userDao = new UserDAO();
		// userDao.insert(record);
		System.out.println(userDao.update(1, "admin@qq.com", "1q2w3f"));
	}
	
}
