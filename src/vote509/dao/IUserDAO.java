package vote509.dao;

import vote509.entity.User;

public interface IUserDAO {

	/**
	 * �����û�
	 */
	public int insert(User record);

	/**
	 * �����û�ȫ������
	 */
	public int update(User record);
	
	/**
	 * �����û���
	 * @return
	 */
	public int update(int id, String username);
	
	/**
	 * ����ͷ��
	 * @return
	 */
	public int updateHeader(int id, String header);
	
	/**
	 * �����û������������
	 * @return
	 */
	public int update(int id, String email, String password);
	
	

	public int deleteById(Integer id);
	
	/**
	 * �������������в�ѯ
	 * 
	 * @param email ����
	 * @param password ����
	 * @param viewName ��ͼ��
	 * @return
	 */
	public User selectByEmailAndPassword(String email, String password);
	
	/**
	 * ��ȡ�û���
	 * @return �û�����
	 */
	public int countUser();

	/**
	 * ͨ��id��ȡ�û���Ϣ
	 */
	public User selectById(int id);
}
