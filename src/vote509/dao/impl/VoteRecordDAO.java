package vote509.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vote509.dao.IVoteRecordDAO;
import vote509.dbconnect.DBConnector;
import vote509.entity.VoteRecord;

/**
 * 投票记录实现类
 * @author He Guo
 */
public class VoteRecordDAO implements IVoteRecordDAO {

	/**
	 * 清空投票记录
	 */
	@Override
	public int delete() {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int res = 0;
		try {
			String str = "delete from voterecord";
			pre = connection.prepareStatement(str);
			res = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return res;
	}
	
	/**
	 * 添加投票记录
	 * 
	 * @return
	 */
	public int addRecord(VoteRecord record) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int res = 0;
		try {
			String str = "insert into voterecord (ip, cid, aid) values (?, ?, ?)";
			pre = connection.prepareStatement(str);
			pre.setString(1, record.getIp());
			pre.setInt(2, record.getCid());
			pre.setInt(3, record.getAid());
			res = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return res;
	}
	
	/**
	 * 查询记录是否存在
	 * 
	 * @return
	 */
	public int recordExist(VoteRecord record) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet result = null;
		int res = 0;
		try {
			String str = "select * from voterecord where ip = ? and cid = ? and aid = ?";
			pre = connection.prepareStatement(str);
			pre.setString(1, record.getIp());
			pre.setInt(2, record.getCid());
			pre.setInt(3, record.getAid());
			result = pre.executeQuery();
			while (result.next()) {
				res++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return res;
	}
	
	/**
	 * 添加投票记录
	 */
	public int recordActivity(int aid, int cid) {
		// update participate set votes = votes + 1 where cid = 2 and aid = 1
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int res = 0;
		try {
			pre = connection.prepareStatement("update participate set votes = votes + 1 where aid = ? and cid = ?");
			pre.setInt(1, aid);
			pre.setInt(2, cid);
			res = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return res;
		
	}
	
	/**
	 * 统计投票次数
	 */
	public int countIpAndAid(String ip, int aid) {
		// update participate set votes = votes + 1 where cid = 2 and aid = 1
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			pre = connection.prepareStatement("select count(ip) from voterecord where aid = ? and ip = ?");
			pre.setString(2, ip);
			pre.setInt(1, aid);
			res = pre.executeQuery();
			if (res.next()) {
				count = res.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return count;
		
	}

}
