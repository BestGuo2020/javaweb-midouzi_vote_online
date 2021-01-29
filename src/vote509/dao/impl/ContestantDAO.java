package vote509.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import vote509.dao.IContestantDAO;
import vote509.dbconnect.DBConnector;
import vote509.entity.Contestant;
import vote509.vo.ContestantVo;

/**
 * 操作选手表的数据访问对象类
 * @author He Guo
 *
 */
public class ContestantDAO implements IContestantDAO {
	
	/**
	 * 按活动号和用户名查询
	 * 
	 * @param activityId 活动id
	 * @param userId 用户id
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId) {
		// select id, contestantId, name, votes, looks, giveup
		// from contestant, participate 
		// where contestant.id = participate.cId and aId = (select id from activity where id = 1 and userId = 1);
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet rs = null;
		List<ContestantVo> constants = null;
		try {
			String sql = "select id, contestantId, image, name, votes, giveup "
					+ "from contestant, participate " + 
					"where contestant.id = participate.cId and aId = (select id from activity where id = ? and userId = ?) "
					+ "order by votes desc";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, activityId);
			pre.setInt(2, userId);
			rs = pre.executeQuery();
			constants = new ArrayList<ContestantVo>();
			while(rs.next()) {
				ContestantVo e = new ContestantVo();
				setContestantVo(e, rs);
				constants.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return constants;
	}
	
	/**
	 * 按活动号、用户名、选手号查询
	 * 
	 * @param activityId 活动号
	 * @param userId 用户id
	 * @param giveup 放弃状态
	 * @param contestantId 选手号
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup) {
		// select id, contestantId, name, votes, giveup
		// from contestant, participate 
		// where contestant.id = participate.cId and aId = (select id from activity where id = 1 and userId = 1);
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet rs = null;
		List<ContestantVo> constants = null;
		try {
			String sql = "select id, contestantId, image, name, votes, giveup "
					+ "from contestant, participate "
					+ "where contestant.id = participate.cId and aId = (select id from activity where id = ? and userId = ?) "
					+ "and giveup = ? order by votes desc";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, activityId);
			pre.setInt(2, userId);
			pre.setInt(3, giveup);
			rs = pre.executeQuery();
			constants = new ArrayList<ContestantVo>();
			while(rs.next()) {
				ContestantVo e = new ContestantVo();
				setContestantVo(e, rs);
				constants.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return constants;
	}
	
	/**
	 * 按活动号、用户名、选手号查询
	 * 
	 * @param activityId 活动号
	 * @param userId 用户id
	 * @param giveup 放弃状态
	 * @param contestantId 选手号
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup, int contestantId) {
		// select id, contestantId, name, votes, looks, giveup
		// from contestant, participate 
		// where contestant.id = participate.cId and aId = (select id from activity where id = 1 and userId = 1);
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet rs = null;
		List<ContestantVo> constants = null;
		try {
			String sql = null;
			if(giveup < 0 || giveup > 1) {
				sql = "select id, contestantId, name, image, votes, looks, giveup "
						+ "from contestant, participate "
						+ "where contestant.id = participate.cId and aId = (select id from activity where id = ? and userId = ?)"
						+ " and contestantId = ? order by votes desc";
				pre = connection.prepareStatement(sql);
				pre.setInt(1, activityId);
				pre.setInt(2, userId);
				pre.setInt(3, contestantId);
			} else {
				sql = "select id, contestantId, name, image, votes, giveup "
						+ "from contestant, participate "
						+ "where contestant.id = participate.cId and aId = (select id from activity where id = ? and userId = ?) "
						+ "and giveup = ? and contestantId = ? order by votes desc";
				pre = connection.prepareStatement(sql);
				pre.setInt(1, activityId);
				pre.setInt(2, userId);
				pre.setInt(3, giveup);
				pre.setInt(4, contestantId);
			}
			rs = pre.executeQuery();
			constants = new ArrayList<ContestantVo>();
			while(rs.next()) {
				ContestantVo e = new ContestantVo();
				setContestantVo(e, rs);
				constants.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return constants;
	}
	
	/**
	 * 按活动号、用户名、选手名查询
	 * 
	 * @param activityId 活动号
	 * @param userId 用户id
	 * @param giveup 放弃状态
	 * @param name 选手号
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup, String name) {
		// select id, contestantId, name, votes, looks, giveup
		// from contestant, participate 
		// where contestant.id = participate.cId and aId = (select id from activity where id = 1 and userId = 1);
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet rs = null;
		List<ContestantVo> constants = null;
		try {
			String sql = null;
			if(giveup < 0 || giveup > 1) {
				sql = "select id, contestantId, name, image, votes, giveup "
						+ "from contestant, participate "
						+ "where contestant.id = participate.cId and aId = (select id from activity where id = ? and userId = ?) "
						+ " and name like \"%\"?\"%\" order by votes desc";
				pre = connection.prepareStatement(sql);
				pre.setInt(1, activityId);
				pre.setInt(2, userId);
				pre.setString(3, name);
			} else {
				sql = "select id, contestantId, name, image, votes, giveup "
						+ "from contestant, participate "
						+ "where contestant.id = participate.cId and aId = (select id from activity where id = ? and userId = ?) "
						+ "and giveup = ? and name like \"%\"?\"%\" order by votes desc";
				pre = connection.prepareStatement(sql);
				pre.setInt(1, activityId);
				pre.setInt(2, userId);
				pre.setInt(3, giveup);
				pre.setString(4, name);
			}
			rs = pre.executeQuery();
			constants = new ArrayList<ContestantVo>();
			while(rs.next()) {
				ContestantVo e = new ContestantVo();
				setContestantVo(e, rs);
				constants.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return constants;
	}
	
	/**
	 * 按活动号、用户名、选票区间查询
	 * 
	 * @param activityId 活动号
	 * @param userId 用户id
	 * @param giveup 放弃状态
	 * @param min 最小值
	 * @Param max 最大值
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup, int min, int max) {
		// select id, contestantId, name, votes, looks, giveup
		// from contestant, participate 
		// where contestant.id = participate.cId and aId = (select id from activity where id = 1 and userId = 1);
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet rs = null;
		List<ContestantVo> constants = null;
		try {
			String sql = null;
			if(giveup < 0 || giveup > 1) {
				sql = "select id, contestantId, name, image, votes, looks, giveup "
						+ "from contestant, participate "
						+ "where contestant.id = participate.cId and aId = (select id from activity where id = ? and userId = ?) "
						+ " and votes between ? and ? order by votes desc";
				pre = connection.prepareStatement(sql);
				pre.setInt(1, activityId);
				pre.setInt(2, userId);
				pre.setInt(3, min);
				pre.setInt(4, max);
			} else {
				sql = "select id, contestantId, name, image, votes, giveup "
						+ "from contestant, participate "
						+ "where contestant.id = participate.cId and aId = (select id from activity where id = ? and userId = ?) "
						+ "and giveup = ? and votes between ? and ? order by votes desc";
				pre = connection.prepareStatement(sql);
				pre.setInt(1, activityId);
				pre.setInt(2, userId);
				pre.setInt(3, giveup);
				pre.setInt(4, min);
				pre.setInt(5, max);
			}
			rs = pre.executeQuery();
			constants = new ArrayList<ContestantVo>();
			while(rs.next()) {
				ContestantVo e = new ContestantVo();
				setContestantVo(e, rs);
				constants.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return constants;
	}
	
	private void setContestantVo(ContestantVo e, ResultSet rs) throws SQLException {
		e.setId(rs.getInt("id"));
		e.setContestantid(rs.getInt("contestantId"));
		e.setName(rs.getString("name"));
		e.setVotes(rs.getInt("votes"));
		e.setGiveup(rs.getInt("giveup"));
		e.setImage(rs.getString("image"));
	}

	/**
	 * 添加选手到当前投票活动
	 * 
	 * @param id 活动编号
	 * @param contestant 选手
	 * @return
	 */
	public int addContestant(int id, Contestant contestant) {
		/*
		 * insert into contestant(contestantId, name, introduce) values ((select contestantCount from activity where activity.id = 2) + 1, '杀马特团长', '测试介绍2');
		 * update activity set contestantCount = contestantCount + 1 where id = 2;
		 */
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int res1 = 0, res2 = 0, res3 = 0;
		// 添加选手到选手表
		try {
			String sql = "insert into contestant"
					+ "(contestantId, name, image, introduce) "
					+ "values ((select contestantCount from activity where activity.id = ?) + 1, ?, ?, ?)";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			pre.setString(2, contestant.getName());
			pre.setString(3, contestant.getImage());
			pre.setString(4, contestant.getIntroduce());
			res1 = pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(null, pre, null);
		}
		Contestant contestant2 = getLast();
		// 将选手数和参与的活动关联起来
		try {
			String sql = "insert into participate(cId, aId) values (?,?)";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, contestant2.getId());
			pre.setInt(2, id);
			res2 = pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(null, pre, null);
		}
		// 更新选手数
		try {
			String sql = "update activity set contestantCount = contestantCount + 1 where id = ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			res3 = pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		
		return res1 == 1 && res2 == 1 && res3 == 1 ? 1 : 0;
		
	}
	
	/**
	 * 修改选手到当前投票活动
	 * 
	 * @param contestant 选手
	 * @return
	 */
	public int reviseContestant(Contestant contestant) {
		/*
		 * insert into contestant(contestantId, name, introduce) values ((select contestantCount from activity where activity.id = 2) + 1, '杀马特团长', '测试介绍2');
		 * update activity set contestantCount = contestantCount + 1 where id = 2;
		 */
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int res = 0;
		// 添加数据
		try {
			String sql;
			// 如果有弃权操作
			if(contestant.getGiveup() != null) {
				sql = "update contestant set giveup = ? where id = ?";
				pre = connection.prepareStatement(sql);
				pre.setInt(2, contestant.getId());
				pre.setInt(1, contestant.getGiveup());
			} else {
				if("".equals(contestant.getImage()) || contestant.getImage() == null) {
					sql = "update contestant set `name` = ?, introduce = ? where id = ?";
					pre = connection.prepareStatement(sql);
					pre.setString(1, contestant.getName());
					pre.setString(2, contestant.getIntroduce());
					pre.setInt(3, contestant.getId());
				} else {
					sql = "update contestant set `name` = ?, introduce = ?, image = ? where id = ?";
					pre = connection.prepareStatement(sql);
					pre.setString(1, contestant.getName());
					pre.setString(2, contestant.getIntroduce());
					pre.setString(3, contestant.getImage());
					pre.setInt(4, contestant.getId());
				}
				
			}
			res = pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		
		return res;
		
	}
	
	/**
	 * 查询插入的选手id
	 * @return id
	 */
	public Contestant getLast() {
		// select * from contestant order by id desc limit 1;
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		Contestant contestant = null;
		try {
			pre = connection.prepareStatement("select id, contestantId from contestant order by id desc limit 1");
			res = pre.executeQuery();
			if(res.next()) {
				contestant = new Contestant();
				contestant.setId(res.getInt("id"));
				contestant.setContestantid(res.getInt("contestantId"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return contestant;
	}
	
	/**
	 * 按id查询选手
	 * 
	 * @param id
	 * @return
	 */
	public Contestant getContestantById(int id) {
		// select * from contestant order by id desc limit 1;
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		Contestant contestant = null;
		try {
			pre = connection.prepareStatement("select * from contestant where id = ?");
			pre.setInt(1, id);
			res = pre.executeQuery();
			if(res.next()) {
				contestant = new Contestant();
				contestant.setId(res.getInt("id"));
				contestant.setContestantid(res.getInt("contestantId"));
				contestant.setName(res.getString("name"));
				contestant.setIntroduce(res.getString("introduce"));
				contestant.setImage(res.getString("image"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return contestant;
	}

	@Override
	public int delContestant(int id) {
		// TODO Auto-generated method stub
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int count = 0;
		try {
			pre = connection.prepareStatement("delete from contestant where id = ?");
			pre.setInt(1, id);
			count = pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return count;
	}
	
}
