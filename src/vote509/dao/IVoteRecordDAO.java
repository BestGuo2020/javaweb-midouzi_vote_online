package vote509.dao;

import vote509.entity.VoteRecord;

/**
 * ͶƱ��¼��
 * @author He Guo
 *
 */
public interface IVoteRecordDAO {
	/**
	 * �����Ϣ��¼
	 * @return
	 */
	public int delete();
	
	public int addRecord(VoteRecord record);
	
	public int recordExist(VoteRecord record);
	
	public int recordActivity(int aid, int cid);
	
	public int countIpAndAid(String ip, int aid);
}
