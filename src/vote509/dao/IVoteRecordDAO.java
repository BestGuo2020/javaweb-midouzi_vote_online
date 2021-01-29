package vote509.dao;

import vote509.entity.VoteRecord;

/**
 * 投票记录表
 * @author He Guo
 *
 */
public interface IVoteRecordDAO {
	/**
	 * 清空消息记录
	 * @return
	 */
	public int delete();
	
	public int addRecord(VoteRecord record);
	
	public int recordExist(VoteRecord record);
	
	public int recordActivity(int aid, int cid);
	
	public int countIpAndAid(String ip, int aid);
}
