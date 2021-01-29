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
 * 监听器
 * servlet初始化的时候
 */
@WebListener
public class Listener233 implements ServletContextListener {

	// 创建定时器
    private final Timer timer = new Timer();
    // 创建ActivityDAO
    private IActivityDAO activityDAO = new ActivityDAO();
    private IVoteRecordDAO voteRecordDAO = new VoteRecordDAO();

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
        // 容器销毁时，销毁定时任务
    	timer.cancel();
    }

	/**
	 * 设置定时器内容
	 */
    public void contextInitialized(ServletContextEvent sce)  {
    	System.out.println("开始轮训数据库......");
        timer.schedule(new TimerTask() {
        	@Override
        	public void run() {
        		// 更新活动状态
        		activityDAO.updateActivityStatus();
        		// 如果到了深夜，清空数据
        		String time = DateUtils.getTime("HH");
        		if(time.equals("00")) {
        			voteRecordDAO.delete();
        		}
        	}
        }, 2000, 1000);
    }
	
}
