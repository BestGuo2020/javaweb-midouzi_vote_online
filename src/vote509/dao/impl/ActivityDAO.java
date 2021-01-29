package vote509.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vote509.dao.IActivityDAO;
import vote509.dbconnect.DBConnector;
import vote509.entity.Activity;
import vote509.util.DateUtils;
import vote509.vo.ActivityVo;

/**
 * 操作活动表的数据访问对象类
 * @author He Guo
 *
 */
public class ActivityDAO implements IActivityDAO {
	
	/**
	 * 统计执行的活动，带状态的
	 * 
	 * @param status 活动状态
	 * @return 活动数量
	 */
	public int countActivity(int status) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			pre = connection.prepareStatement("select count(*) as count from activity where status = ?");
			pre.setInt(1, status);
			res = pre.executeQuery();
			if(res.next())
				count = res.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}
	
	/**
	 * 统计执行的活动，带状态的，指定用户id的
	 * 
	 * @param user 用户id
	 * @param status 状态id
	 * @return 活动数量
	 */
	public int countActivity(int user, int status) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			pre = connection.prepareStatement("select count(*) as count from activity where status = ? and userId = ?");
			pre.setInt(1, status);
			pre.setInt(2, user);
			res = pre.executeQuery();
			if(res.next())
				count = res.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}
	
	/**
	 * 统计执行的活动，指定用户id的
	 * 
	 * @param user 用户id
	 * @return 活动数量
	 */
	public int countActivityByUser(int user) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			pre = connection.prepareStatement("select count(*) as count from activity where userId = ?");
			pre.setInt(1, user);
			res = pre.executeQuery();
			if(res.next())
				count = res.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}
	
	/**
	 * 根据用户，查询所有活动访问量统计
	 * 
	 * @param user
	 * @return 总访问量
	 */
	public int countLooks(int user) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			pre = connection.prepareStatement("select sum(looks) count from activity where userId = ?");
			pre.setInt(1, user);
			res = pre.executeQuery();
			if(res.next())
				count = res.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}
	
	/**
	 * 根据用户，查询所有活动总票数
	 * 
	 * @param user
	 * @return 总票数
	 */
	public int countTickets(int user) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			pre = connection.prepareStatement("select sum(countVotes) count from activity where userId = ?");
			pre.setInt(1, user);
			res = pre.executeQuery();
			if(res.next())
				count = res.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}
	
	/**
	 * 查询当前普通用户的活动数
	 * 
	 * @param user 用户id
	 * @return 总票数
	 */
	public int countGeneuserActivity(int user) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			pre = connection.prepareStatement("select count(*) count from activity where userId in (select id from user where id = ?);");
			pre.setInt(1, user);
			res = pre.executeQuery();
			if(res.next())
				count = res.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}

	/**
	 * 查询当前用户最近正在进行的活动
	 * 
	 * @return
	 */
	public List<ActivityVo> getActivityRecent(int id) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		List<ActivityVo> list = new ArrayList<>();
		try {
			String sql = "select activity.id as aid, name, username, countVotes, contestantCount " + 
					"from activity join user on activity.userId = user.id where userId = ? and status = 1 limit 5";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			res = pre.executeQuery();
			while(res.next()) {
				ActivityVo activity = new ActivityVo();
				activity.setAid(res.getInt("aid"));
				activity.setContestantCount(res.getInt("contestantCount"));
				activity.setCountVotes(res.getInt("countVotes"));
				activity.setName(res.getString("name"));
				activity.setUsername(res.getString("username"));
				list.add(activity);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return list;
		
	}
	
	/**
	 * 查询当前用户的全部的活动
	 * 
	 * @param start 当前页数
	 * @param length 当前页有多少内容
	 * @param id 用户id
	 * @return
	 */
	public List<ActivityVo> getActivityAll(int start, int length, int id) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		List<ActivityVo> list = new ArrayList<>();
		try {
			start = length * (start - 1); // 获取几列
			String sql = "select activity.id as aid, userId, name, username, countVotes, "
					+ "contestantCount, startTime, stopTime, status, image, times, looks " + 
					"from activity join user on activity.userId = user.id where user.id = ? limit ?, ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			pre.setInt(2, start);
			pre.setInt(3, length);
			res = pre.executeQuery();
			while(res.next()) {
				setAcvitivyEntityVos(res, list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return list;
		
	}
	
	/**
	 * 查询当前用户的活动，按活动状态查找
	 * 
	 * @param start 当前页数
	 * @param length 当前页有多少内容
	 * @param status 活动状态
	 * @param id 用户id
	 * @return
	 */
	public List<ActivityVo> getActivityAll(int start, int length, int status, int id) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		List<ActivityVo> list = new ArrayList<>();
		try {
			start = length * (start - 1); // 获取几列
			String sql = "select activity.id as aid, userId, name, username, countVotes, contestantCount, startTime, stopTime, status, image, times, looks " + 
					"from activity join user on activity.userId = user.id where user.id = ? and status = ? limit ?, ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			pre.setInt(2, status);
			pre.setInt(3, start);
			pre.setInt(4, length);
			res = pre.executeQuery();
			while(res.next()) {
				setAcvitivyEntityVos(res, list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return list;
		
	}
	
	/**
	 * 按活动名称查询当前用户的创建的活动
	 * 
	 * @param start 当前页数
	 * @param length 当前页有多少内容
	 * @param status 活动状态
	 * @param id 用户id
	 * @param name 活动名称
	 * @return
	 */
	public List<ActivityVo> getActivityByName(int start, int length, int id, int status, String name) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		List<ActivityVo> list = new ArrayList<>();
		try {
			start = length * (start - 1); // 获取几列
			String sql = "select activity.id as aid, userId, name, username, countVotes, contestantCount, startTime, stopTime, status, image, times, looks " + 
					"from activity join user on activity.userId = user.id "
					+ "where user.id = ? and status = ? and name like \"%\"?\"%\" limit ?, ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			pre.setInt(2, status);
			pre.setString(3, name);
			pre.setInt(4, start);
			pre.setInt(5, length);
			res = pre.executeQuery();
			while(res.next()) {
				setAcvitivyEntityVos(res, list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return list;
		
	}
	
	/**
	 * 按活动名称查询当前用户的创建的活动
	 * 
	 * @param start 当前页数
	 * @param length 当前页有多少内容
	 * @param id 用户id
	 * @param name 活动名称
	 * @return
	 */
	public List<ActivityVo> getActivityByName(int start, int length, int id, String name) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		List<ActivityVo> list = new ArrayList<>();
		try {
			start = length * (start - 1); // 获取几列
			String sql = "select activity.id as aid, userId, name, username, countVotes, contestantCount, startTime, stopTime, status, image, times, looks " + 
					"from activity join user on activity.userId = user.id "
					+ "where user.id = ? and name like \"%\"?\"%\" limit ?, ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			pre.setString(2, name);
			pre.setInt(3, start);
			pre.setInt(4, length);
			res = pre.executeQuery();
			while(res.next()) {
				setAcvitivyEntityVos(res, list);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return list;
		
	}
	
	public int getActivityByNameCount(int id, int status, String name) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			String sql = "select count(*)" + 
					"from activity join user on activity.userId = user.id "
					+ "where user.id = ? and status = ? and name like \"%\"?\"%\"";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			pre.setInt(2, status);
			pre.setString(3, name);
			res = pre.executeQuery();
			if(res.next()) {
				count = res.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}
	
	public int getActivityByNameCount(int id, String name) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			String sql = "select count(*)" + 
					"from activity join user on activity.userId = user.id "
					+ "where user.id = ? and name like \"%\"?\"%\"";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			pre.setString(2, name);
			res = pre.executeQuery();
			if(res.next()) {
				count = res.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}
	
	public int getActivityByCategoryCount(int id, int status, String name) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			String sql = "select count(*)" + 
					"from activity join user on activity.userId = user.id "
					+ "where user.id = ? and status = ? and name like \"%\"?\"%\"";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			pre.setInt(2, status);
			pre.setString(3, name);
			res = pre.executeQuery();
			if(res.next()) {
				count = res.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}
	
	public int getActivityByCategoryCount(int id, String name) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		int count = 0;
		try {
			String sql = "select count(*)" + 
					"from activity join user on activity.userId = user.id "
					+ "where user.id = ? and name like \"%\"?\"%\"";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, id);
			pre.setString(2, name);
			res = pre.executeQuery();
			if(res.next()) {
				count = res.getInt(1);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return count;
	}
	
	/**
	 * 添加活动
	 * @return
	 */
	public int addActivity(Activity activity) {
		//insert into activity(`name`, introduce, startTime, stopTime, userId, times) 
		// values ('测试活动2', '测试介绍2', '2020-12-5 15:29:10', '2020-12-9 15:29:10', 1, 5, 1);
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int res = 0;
		try {
			if("".equals(activity.getImage())) {
				String sql = "insert into activity(`name`, introduce, startTime, stopTime, userId, times) " + 
						" values (?, ?, ?, ?, ?, ?)";
				pre = connection.prepareStatement(sql);
				pre.setString(1, activity.getName());
				pre.setString(2, activity.getIntroduece());
				pre.setString(3, DateUtils.getTime(activity.getStarttime()));
				pre.setString(4, DateUtils.getTime(activity.getStoptime()));
				pre.setInt(5, activity.getUserid());
				pre.setInt(6, activity.getTimes());
			} else {
				String sql = "insert into activity(`name`, introduce, startTime, stopTime, userId, times, image) " + 
						" values (?, ?, ?, ?, ?, ?, ?)";
				pre = connection.prepareStatement(sql);
				pre.setString(1, activity.getName());
				pre.setString(2, activity.getIntroduece());
				pre.setString(3, DateUtils.getTime(activity.getStarttime()));
				pre.setString(4, DateUtils.getTime(activity.getStoptime()));
				pre.setInt(5, activity.getUserid());
				pre.setInt(6, activity.getTimes());
				pre.setString(7, activity.getImage());
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
	 * 修改活动
	 * @return
	 */
	public int reviseActivity(Activity activity) {
		//insert into activity(`name`, introduce, startTime, stopTime, userId, times) 
		// values ('测试活动2', '测试介绍2', '2020-12-5 15:29:10', '2020-12-9 15:29:10', 1, 5, 1);
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int res = 0;
		try {
			if("".equals(activity.getImage())) {
				String sql = "update activity set `name` = ?, introduce = ?, startTime = ?, stopTime = ?, times = ?" + 
						" where id = ? and userId = ?";
				pre = connection.prepareStatement(sql);
				pre.setString(1, activity.getName());
				pre.setString(2, activity.getIntroduece());
				pre.setString(3, DateUtils.getTime(activity.getStarttime()));
				pre.setString(4, DateUtils.getTime(activity.getStoptime()));
				pre.setInt(5, activity.getTimes());
				pre.setInt(6, activity.getId());
				pre.setInt(7, activity.getUserid());
			} else {
				String sql = "update activity set `name` = ?, introduce = ?, startTime = ?, "
						+ "stopTime = ?, times = ?, image = ? " + 
						" where id = ? and userId = ?";
				pre = connection.prepareStatement(sql);
				pre.setString(1, activity.getName());
				pre.setString(2, activity.getIntroduece());
				pre.setString(3, DateUtils.getTime(activity.getStarttime()));
				pre.setString(4, DateUtils.getTime(activity.getStoptime()));
				pre.setInt(5, activity.getTimes());
				pre.setString(6, activity.getImage());
				pre.setInt(7, activity.getId());
				pre.setInt(8, activity.getUserid());
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
	 * 修改活动的浏览数
	 * @return
	 */
	public int reviseActivity(int aid, int looks) {
		//insert into activity(`name`, introduce, startTime, stopTime, userId, times) 
		// values ('测试活动2', '测试介绍2', '2020-12-5 15:29:10', '2020-12-9 15:29:10', 1, 5, 1);
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int res = 0;
		try {
			String sql = "update activity set looks = ? where id = ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(2, aid);
			pre.setInt(1, looks);
			res = pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return res;
	}
	
	/**
	 * 添加很多实体
	 * @param res
	 * @param list
	 * @throws SQLException
	 */
	private void setAcvitivyEntityVos(ResultSet res, List<ActivityVo> list) throws SQLException {
		ActivityVo activity = new ActivityVo();
		setAcvitivyEntity(res, activity);
		list.add(activity);
	}
	
	/**
	 * 添加实体
	 * @param res
	 * @param activity
	 * @throws SQLException
	 */
	private void setAcvitivyEntity(ResultSet res, ActivityVo activity) throws SQLException {
		activity.setAid(res.getInt("aid"));
		activity.setUid(res.getInt("userId"));
		activity.setContestantCount(res.getInt("contestantCount"));
		activity.setCountVotes(res.getInt("countVotes"));
		activity.setName(res.getString("name"));
		activity.setUsername(res.getString("username"));
		activity.setStartTime(res.getDate("startTime"));
		activity.setStopTime(res.getDate("stopTime"));
		activity.setStatus(res.getInt("status"));
		activity.setImage(res.getString("image"));
	}
	
	/**
	 * 获取实体，带详细信息
	 * @param res
	 * @param activity
	 * @throws SQLException
	 */
	private void setAcvitivyEntityDetail(ResultSet res, ActivityVo activity) throws SQLException {
		activity.setAid(res.getInt("aid"));
		activity.setUid(res.getInt("userId"));
		activity.setContestantCount(res.getInt("contestantCount"));
		activity.setCountVotes(res.getInt("countVotes"));
		activity.setName(res.getString("name"));
		activity.setUsername(res.getString("username"));
		activity.setStartTime(res.getDate("startTime"));
		activity.setStopTime(res.getDate("stopTime"));
		activity.setStatus(res.getInt("status"));
		activity.setIntroduce(res.getString("introduce"));
		activity.setImage(res.getString("image"));
		activity.setTimes(res.getInt("times"));
		activity.setLooks(res.getInt("looks"));
	}
	
	/**
	 * 获取实体，带详细信息
	 * @param res
	 * @param activity
	 * @throws SQLException
	 */
	private void setAcvitivyEntityDetail2(ResultSet res, Activity activity) throws SQLException {
		activity.setId(res.getInt("id"));
		activity.setUserid(res.getInt("userId"));
		activity.setName(res.getString("name"));
		activity.setStarttime(res.getDate("startTime"));
		activity.setStoptime(res.getDate("stopTime"));
		activity.setIntroduece(res.getString("introduce"));
		activity.setTimes(res.getInt("times"));
		activity.setStatus(res.getInt("status"));
		activity.setContestantcount(res.getInt("contestantCount"));
		activity.setCountvotes(res.getInt("countVotes"));
		activity.setLooks(res.getInt("looks"));
	}
	
	/**
	 * 通过id获取
	 * @param uid 用户id
	 * @param aid 活动id
	 * @return
	 */
	public ActivityVo getActivityByUidAndAid(int uid, int aid) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		ActivityVo activityVo = null;
		try {
			String sql = "select activity.id as aid, userId, introduce, name, username, countVotes, contestantCount, startTime, stopTime, status, image, select, times, looks " + 
					"from activity join user on activity.userId = user.id "
					+ "where user.id = ? and activity.id = ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, uid);
			pre.setInt(2, aid);
			res = pre.executeQuery();
			if(res.next()) {
				activityVo = new ActivityVo();
				setAcvitivyEntityDetail(res, activityVo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return activityVo;
		
	}
	
	/**
	 * 通过id获取
	 * @param uid 用户id
	 * @param aid 活动id
	 * @return
	 */
	public Activity getActivityByUidAndAid2(int uid, int aid) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		Activity activity = null;
		try {
			String sql = "select * from activity where id = ? and userId = ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, aid);
			pre.setInt(2, uid);
			res = pre.executeQuery();
			if(res.next()) {
				activity = new Activity();
				setAcvitivyEntityDetail2(res, activity);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return activity;
		
	}
	
	/**
	 * 删除活动
	 * 
	 * @param uid 用户id
	 * @param aid 活动id
	 * @return
	 */
	public int deleteByUidAndAid(int uid, int aid) {
		
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int res = 0;
		try {
			String sql = "delete from activity where id = ? and userId = ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, aid);
			pre.setInt(2, uid);
			res = pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return res;
		
	}
	
	/**
	 * 更改活动状态
	 */
	@Override
	public void updateActivityStatus() {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		try {
			String str = "update activity set `status` = 0 where CURDATE() < startTime";
			pre = connection.prepareStatement(str);
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(null, pre, null);
		}
		
		try {
			String str = "update activity set `status` = 1 where CURDATE() > startTime and CURDATE() < stopTime";
			pre = connection.prepareStatement(str);
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(null, pre, null);
		}
		
		try {
			String str = "update activity set `status` = 2 where CURDATE() > stopTime";
			pre = connection.prepareStatement(str);
			pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
	}

	@Override
	public List<Activity> getActivityBySimple(int start, int length) {
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		ResultSet res = null;
		List<Activity> list = new ArrayList<>();
		try {
			start = length * (start - 1); // 获取几列
			String sql = "select id, name, userId, image from activity order by startTime desc limit ?, ?";
			pre = connection.prepareStatement(sql);
			pre.setInt(1, start);
			pre.setInt(2, length);
			res = pre.executeQuery();
			while(res.next()) {
				Activity activity = new Activity();
				activity.setId(res.getInt("id"));
				activity.setName(res.getString("name"));
				activity.setUserid(res.getInt("userId"));
				activity.setImage(res.getString("image"));
				list.add(activity);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, res);
		}
		return list;
	}

	@Override
	public int recordCountTicket(int id) {
		// update activity set countVotes = countVotes + 1 where id = 1
		Connection connection = DBConnector.getConn();
		PreparedStatement pre = null;
		int res = 0;
		try {
			pre = connection.prepareStatement("update activity set countVotes = countVotes + 1 where id = ?");
			pre.setInt(1, id);
			res = pre.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnector.close(connection, pre, null);
		}
		return res;
	}

	/**
	 * 批量删除活动编号
	 * @param idArr
	 * @return
	 */
	public int deleteByAids(String[] idArr) {
		Connection conn = DBConnector.getConn();
		StringBuffer sql = new StringBuffer();
		sql.append("delete from activity where id in (");

		for (int i = 0; i < idArr.length; i++) {
			if(i == idArr.length - 1) 
				sql.append("?)");
			else 
				sql.append("?,");
		}

		PreparedStatement ps = null;
		int res = 0;
		try {
			ps = conn.prepareStatement(sql.toString());
			for (int i = 1; i <= idArr.length; i++)
			    ps.setInt(i, Integer.parseInt(idArr[i - 1]));
			res = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.close(conn, ps, null);
		}
		return res;
	}	
}
