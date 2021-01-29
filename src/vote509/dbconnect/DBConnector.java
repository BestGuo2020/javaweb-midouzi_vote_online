package vote509.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import vote509.util.FileUtils;

/**
 * 连接到数据库的类
 * @author He Guo
 *
 */

public class DBConnector {

	/**
	 * 获得数据库的连接
	 * 
	 * @return 返回一个连接对象
	 */
	public static Connection getConn() {
		Connection conn = null;

		Properties properties = FileUtils.readProperties("db.properties");
		String jdbc = properties.getProperty("jdbc.driver");
		String url = properties.getProperty("jdbc.url");
		String username = properties.getProperty("jdbc.username");
		String password = properties.getProperty("jdbc.password");
		try {
			Class.forName(jdbc);
			conn = DriverManager.getConnection(url, username, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭连接
	 * 
	 * @param conn 连接类
	 * @param ps 执行sql部分参数
	 * @param rs 返回的结果集
	 */
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {}
		}

	}
	
	/**
	 * 连接测试主方法
	 * @param args
	 */
	public static void main(String[] args) {
		// 连接器
		DBConnector c1 = new DBConnector();
		Connection conn = c1.getConn();
		try {
			// 前期准备工作
			PreparedStatement stat = conn.prepareStatement("select * from user");
			// 执行查询时结果集返回，需要使用到循环
			ResultSet res = stat.executeQuery();
			while(res != null && res.next()) {
				System.out.println(res.getString("id"));
				System.out.println(res.getString("username"));
				System.out.println(res.getString("password"));
				System.out.println(res.getString("header"));
				System.out.println(res.getString("email"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
