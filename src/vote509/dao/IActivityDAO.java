package vote509.dao;

import java.util.List;

import vote509.entity.Activity;
import vote509.vo.ActivityVo;

/**
 * 操作活动表的数据访问对象类
 * @author He Guo
 *
 */
public interface IActivityDAO {
	
	/**
	 * 统计执行的活动，带状态的，指定用户id的
	 * 
	 * @param user 用户id
	 * @param status 状态id
	 * @return 活动数量
	 */
	public int countActivity(int user, int status);
	
	/**
	 * 统计执行的活动，指定用户id的
	 * 
	 * @param user 用户id
	 * @return 活动数量
	 */
	public int countActivityByUser(int user);
	
	/**
	 * 根据用户，查询所有活动访问量统计
	 * 
	 * @param user
	 * @return 总访问量
	 */
	public int countLooks(int user);
	
	/**
	 * 根据用户，查询所有活动总票数
	 * 
	 * @param user
	 * @return 总票数
	 */
	public int countTickets(int user);
	
	/**
	 * 查询当前普通用户的活动数
	 * 
	 * @param user 用户id
	 * @return 总票数
	 */
	public int countGeneuserActivity(int user);

	/**
	 * 查询当前用户最近正在进行的活动
	 * 
	 * @return
	 */
	public List<ActivityVo> getActivityRecent(int id);
	
	/**
	 * 查询当前用户的全部的活动
	 * 
	 * @param start 当前页数
	 * @param length 当前页有多少内容
	 * @param id 用户id
	 * @return
	 */
	public List<ActivityVo> getActivityAll(int start, int length, int id);
	
	/**
	 * 查询当前用户的活动，按活动状态查找
	 * 
	 * @param start 当前页数
	 * @param length 当前页有多少内容
	 * @param status 活动状态
	 * @param id 用户id
	 * @return
	 */
	public List<ActivityVo> getActivityAll(int start, int length, int status, int id);
	
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
	public List<ActivityVo> getActivityByName(int start, int length, int id, int status, String name);
	
	/**
	 * 按活动名称查询当前用户的创建的活动
	 * 
	 * @param start 当前页数
	 * @param length 当前页有多少内容
	 * @param id 用户id
	 * @param name 活动名称
	 * @return
	 */
	public List<ActivityVo> getActivityByName(int start, int length, int id, String name);
	
	public int getActivityByNameCount(int id, int status, String name);
	
	public int getActivityByNameCount(int id, String name);
	
	public int getActivityByCategoryCount(int id, int status, String name);
	
	public int getActivityByCategoryCount(int id, String name);
	
	/**
	 * 添加活动
	 * @return
	 */
	public int addActivity(Activity activity);
	
	/**
	 * 添加活动
	 * @return
	 */
	public int reviseActivity(Activity activity);
	
	/**
	 * 通过id获取
	 * @param uid 用户id
	 * @param aid 活动id
	 * @return
	 */
	public ActivityVo getActivityByUidAndAid(int uid, int aid);
	
	/**
	 * 通过id获取
	 * @param uid 用户id
	 * @param aid 活动id
	 * @return
	 */
	public Activity getActivityByUidAndAid2(int uid, int aid);
	
	/**
	 * 删除活动
	 * 
	 * @param uid 用户id
	 * @param aid 活动id
	 * @return
	 */
	public int deleteByUidAndAid(int uid, int aid);
	
	/**
	 * 更新活动状态
	 */
	public void updateActivityStatus();
	
	/**
	 * 记录总票数
	 * 
	 * @param id 活动id
	 * @return
	 */
	public int recordCountTicket(int id);
	
	/**
	 * 查询活动的标题，头像，活动id
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Activity> getActivityBySimple(int start, int length);
	
	/**
	 * 修改活动的浏览数
	 * @return
	 */
	public int reviseActivity(int aid, int looks);

}
