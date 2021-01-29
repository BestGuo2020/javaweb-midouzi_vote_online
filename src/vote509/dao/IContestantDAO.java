package vote509.dao;

import java.util.List;

import vote509.entity.Contestant;
import vote509.vo.ContestantVo;

/**
 * ����ѡ�ֱ�����ݷ��ʶ�����
 * @author He Guo
 *
 */
public interface IContestantDAO {
	
	/**
	 * ����ź��û�����ѯ
	 * 
	 * @param activityId �id
	 * @param userId �û�id
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId);
	
	/**
	 * ����š��û�����ѡ�ֺŲ�ѯ
	 * 
	 * @param activityId ���
	 * @param userId �û�id
	 * @param giveup ����״̬
	 * @param contestantId ѡ�ֺ�
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup);
	
	/**
	 * ����š��û�����ѡ�ֺŲ�ѯ
	 * 
	 * @param activityId ���
	 * @param userId �û�id
	 * @param giveup ����״̬
	 * @param contestantId ѡ�ֺ�
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup, int contestantId);
	
	/**
	 * ����š��û�����ѡ������ѯ
	 * 
	 * @param activityId ���
	 * @param userId �û�id
	 * @param giveup ����״̬
	 * @param name ѡ�ֺ�
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup, String name);
	
	/**
	 * ����š��û�����ѡƱ�����ѯ
	 * 
	 * @param activityId ���
	 * @param userId �û�id
	 * @param giveup ����״̬
	 * @param min ��Сֵ
	 * @Param max ���ֵ
	 * @return
	 */
	public List<ContestantVo> getContestantByActivity(int activityId, int userId, int giveup, int min, int max);
	
	/**
	 * ���ѡ�ֵ���ǰͶƱ�
	 * 
	 * @param id ����
	 * @param contestant ѡ��
	 * @return
	 */
	public int addContestant(int id, Contestant contestant);
	
	/**
	 * �޸�ѡ�ֵ���ǰͶƱ�
	 * 
	 * @param contestant ѡ��
	 * @return
	 */
	public int reviseContestant(Contestant contestant);
	
	/**
	 * �޸�ѡ�ֵ���ǰͶƱ�
	 * 
	 * @param contestant ѡ��
	 * @return
	 */
	public int delContestant(int id);
	
	/**
	 * ��ѯ�����ѡ��id
	 * @return id
	 */
	public Contestant getLast();
	
	/**
	 * ��id��ѯѡ��
	 * 
	 * @param id
	 * @return
	 */
	public Contestant getContestantById(int id);
	
}
