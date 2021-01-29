package vote509.init;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import vote509.dao.IActivityDAO;
import vote509.dao.IVoteRecordDAO;
import vote509.dao.impl.*;
import vote509.util.DateUtils;


/**
 * ������
 * servlet��ʼ����ʱ��
 */
@WebListener
public class Listener233 implements ServletContextListener {

	// ������ʱ��
    private final Timer timer = new Timer();
    // ����ActivityDAO
    private IActivityDAO activityDAO = new ActivityDAO();
    private IVoteRecordDAO voteRecordDAO = new VoteRecordDAO();

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
        // ��������ʱ�����ٶ�ʱ����
    	timer.cancel();
    }

	/**
	 * ���ö�ʱ������
	 */
    public void contextInitialized(ServletContextEvent sce)  {
    	System.out.println("��ʼ��ѵ���ݿ�......");
        timer.schedule(new TimerTask() {
        	@Override
        	public void run() {
        		// ���»״̬
        		activityDAO.updateActivityStatus();
        		// ���������ҹ���������
        		String time = DateUtils.getTime("HH");
        		if(time.equals("00")) {
        			voteRecordDAO.delete();
        		}
        	}
        }, 2000, 1000);
    }
	
}
