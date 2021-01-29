package vote509.dao;

import java.util.List;

import vote509.entity.Contestant;
import vote509.vo.ContestantVo;

/**
 * 操作选手表的数据访问对象类
 * @author He Guo
 *
 */
public interface IContestantDAO {
	
	/**
	 * 按活动号和用户名查询
	 * 
	 * @param activityId 活动id
	 * @param userId 用户id
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId);
	
	/**
	 * 按活动号、用户名、选手号查询
	 * 
	 * @param activityId 活动号
	 * @param userId 用户id
	 * @param giveup 放弃状态
	 * @param contestantId 选手号
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup);
	
	/**
	 * 按活动号、用户名、选手号查询
	 * 
	 * @param activityId 活动号
	 * @param userId 用户id
	 * @param giveup 放弃状态
	 * @param contestantId 选手号
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup, int contestantId);
	
	/**
	 * 按活动号、用户名、选手名查询
	 * 
	 * @param activityId 活动号
	 * @param userId 用户id
	 * @param giveup 放弃状态
	 * @param name 选手号
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup, String name);
	
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
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup, int min, int max);
	
	/**
	 * 添加选手到当前投票活动
	 * 
	 * @param id 活动编号
	 * @param contestant 选手
	 * @return
	 */
	public int addContestant(int id, Contestant contestant);
	
	/**
	 * 修改选手到当前投票活动
	 * 
	 * @param contestant 选手
	 * @return
	 */
	public int reviseContestant(Contestant contestant);
	
	/**
	 * 修改选手到当前投票活动
	 * 
	 * @param contestant 选手
	 * @return
	 */
	public int delContestant(int id);
	
	/**
	 * 查询插入的选手id
	 * @return id
	 */
	public Contestant getLast();
	
	/**
	 * 按id查询选手
	 * 
	 * @param id
	 * @return
	 */
	public Contestant getContestantById(int id);
	
}
