package vote509.dao;

import java.util.List;

import vote509.entity.Activity;
import vote509.vo.ActivityVo;

/**
 * �����������ݷ��ʶ�����
 * @author He Guo
 *
 */
public interface IActivityDAO {
	
	/**
	 * ͳ��ִ�еĻ����״̬�ģ�ָ���û�id��
	 * 
	 * @param user �û�id
	 * @param status ״̬id
	 * @return �����
	 */
	public int countActivity(int user, int status);
	
	/**
	 * ͳ��ִ�еĻ��ָ���û�id��
	 * 
	 * @param user �û�id
	 * @return �����
	 */
	public int countActivityByUser(int user);
	
	/**
	 * �����û�����ѯ���л������ͳ��
	 * 
	 * @param user
	 * @return �ܷ�����
	 */
	public int countLooks(int user);
	
	/**
	 * �����û�����ѯ���л��Ʊ��
	 * 
	 * @param user
	 * @return ��Ʊ��
	 */
	public int countTickets(int user);
	
	/**
	 * ��ѯ��ǰ��ͨ�û��Ļ��
	 * 
	 * @param user �û�id
	 * @return ��Ʊ��
	 */
	public int countGeneuserActivity(int user);

	/**
	 * ��ѯ��ǰ�û�������ڽ��еĻ
	 * 
	 * @return
	 */
	public List<ActivityVo> getActivityRecent(int id);
	
	/**
	 * ��ѯ��ǰ�û���ȫ���Ļ
	 * 
	 * @param start ��ǰҳ��
	 * @param length ��ǰҳ�ж�������
	 * @param id �û�id
	 * @return
	 */
	public List<ActivityVo> getActivityAll(int start, int length, int id);
	
	/**
	 * ��ѯ��ǰ�û��Ļ�����״̬����
	 * 
	 * @param start ��ǰҳ��
	 * @param length ��ǰҳ�ж�������
	 * @param status �״̬
	 * @param id �û�id
	 * @return
	 */
	public List<ActivityVo> getActivityAll(int start, int length, int status, int id);
	
	/**
	 * ������Ʋ�ѯ��ǰ�û��Ĵ����Ļ
	 * 
	 * @param start ��ǰҳ��
	 * @param length ��ǰҳ�ж�������
	 * @param status �״̬
	 * @param id �û�id
	 * @param name �����
	 * @return
	 */
	public List<ActivityVo> getActivityByName(int start, int length, int id, int status, String name);
	
	/**
	 * ������Ʋ�ѯ��ǰ�û��Ĵ����Ļ
	 * 
	 * @param start ��ǰҳ��
	 * @param length ��ǰҳ�ж�������
	 * @param id �û�id
	 * @param name �����
	 * @return
	 */
	public List<ActivityVo> getActivityByName(int start, int length, int id, String name);
	
	public int getActivityByNameCount(int id, int status, String name);
	
	public int getActivityByNameCount(int id, String name);
	
	public int getActivityByCategoryCount(int id, int status, String name);
	
	public int getActivityByCategoryCount(int id, String name);
	
	/**
	 * ��ӻ
	 * @return
	 */
	public int addActivity(Activity activity);
	
	/**
	 * ��ӻ
	 * @return
	 */
	public int reviseActivity(Activity activity);
	
	/**
	 * ͨ��id��ȡ
	 * @param uid �û�id
	 * @param aid �id
	 * @return
	 */
	public ActivityVo getActivityByUidAndAid(int uid, int aid);
	
	/**
	 * ͨ��id��ȡ
	 * @param uid �û�id
	 * @param aid �id
	 * @return
	 */
	public Activity getActivityByUidAndAid2(int uid, int aid);
	
	/**
	 * ɾ���
	 * 
	 * @param uid �û�id
	 * @param aid �id
	 * @return
	 */
	public int deleteByUidAndAid(int uid, int aid);
	
	/**
	 * ���»״̬
	 */
	public void updateActivityStatus();
	
	/**
	 * ��¼��Ʊ��
	 * 
	 * @param id �id
	 * @return
	 */
	public int recordCountTicket(int id);
	
	/**
	 * ��ѯ��ı��⣬ͷ�񣬻id
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Activity> getActivityBySimple(int start, int length);
	
	/**
	 * �޸Ļ�������
	 * @return
	 */
	public int reviseActivity(int aid, int looks);

}
