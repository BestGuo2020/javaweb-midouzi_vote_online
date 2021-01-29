package vote509.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import vote509.dao.IActivityDAO;
import vote509.dao.IContestantDAO;
import vote509.dao.IUserDAO;
import vote509.dao.IVoteRecordDAO;
import vote509.dao.impl.ActivityDAO;
import vote509.dao.impl.ContestantDAO;
import vote509.dao.impl.UserDAO;
import vote509.dao.impl.VoteRecordDAO;
import vote509.entity.Activity;
import vote509.entity.Contestant;
import vote509.entity.User;
import vote509.entity.VoteRecord;
import vote509.util.OtherUtils;
import vote509.vo.ActivityVo;
import vote509.vo.ContestantVo;

/**
 * 投票界面展示
 */
@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	IActivityDAO acDAO = new ActivityDAO();
	IVoteRecordDAO recordDAO = new VoteRecordDAO();
	IContestantDAO contestantDAO = new ContestantDAO();
	IUserDAO uDao = new UserDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		// 获取用户id
		Integer uid = new Integer(request.getParameter("uId"));
		// 获取活动id
		Integer aid = new Integer(request.getParameter("aId"));
		Activity activity = acDAO.getActivityByUidAndAid2(uid, aid);
		User u = uDao.selectById(uid);
		// 获取查询条件
		String type = request.getParameter("type");
		List<ContestantVo> contestants = null;
		if(null != type || "null".equals(type) || "".equals(type)) {
			String keyword = request.getParameter("keyword");
			// 按三种条件查询
			if("1".equals(type)) {
				if("".equals(keyword)) {
					contestants = contestantDAO.getContestantByActivity(aid, uid, 0);
					request.setAttribute("msg", "你还没有输入编号！");
				} else {
					int n = Integer.parseInt(keyword); // 选手号解析
					// 0为未弃权的选手
					contestants = contestantDAO.getContestantByActivity(aid, uid, 0, n);
				}
			} else if("2".equals(type)) {
				contestants = contestantDAO.getContestantByActivity(aid, uid, 0, keyword);
			} else if("3".equals(type)){
				// 判断是否匹配，例如(20-30)
				if(OtherUtils.regexArea(keyword)) {
					int min = Integer.parseInt(keyword.split("-")[0]);
					int max = Integer.parseInt(keyword.split("-")[1]);
					contestants = contestantDAO.getContestantByActivity(aid, uid, 0, min, max);
				} else {
					contestants = contestantDAO.getContestantByActivity(aid, uid, 0);
					request.setAttribute("msg", "输入格式有误，正确格式为“20-30”");
				}
			} else {
				// 查询全部投票数
				contestants = contestantDAO.getContestantByActivity(aid, uid, 0);
			}
		} else {
			// 增加浏览量
			int looks = activity.getLooks();
			acDAO.reviseActivity(aid, ++looks);
			contestants = contestantDAO.getContestantByActivity(aid, uid, 0);
		}
		request.setAttribute("activity", activity);
		request.setAttribute("contestants", contestants);
		request.setAttribute("user", u);
		request.getRequestDispatcher("/WEB-INF/jsp/frontend/voteMain.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// 设置返回的数据
		JSONObject json = new JSONObject();
		
		String cid = request.getParameter("cid");
		String uid = request.getParameter("uid");
		String aid = request.getParameter("aid");
		// 获取ip
		String ip = request.getRemoteAddr();
		// 查询活动id
		Activity ac = acDAO.getActivityByUidAndAid2(new Integer(uid), new Integer(aid));
		// 获取活动状态
		if (ac.getStatus() == 0) {
			json.append("status", "error");
			json.append("msg", "还不能投票，该活动未开始！");
		} else if(ac.getStatus() == 1) {
			// 获取可投选手数
			int availTimes = ac.getTimes();
			int times = recordDAO.countIpAndAid(ip, new Integer(aid));
			if(times < availTimes) {
				// 查询该记录是否存在
				VoteRecord record = new VoteRecord();
				record.setIp(ip);
				record.setAid(new Integer(aid));
				record.setCid(new Integer(cid));
				
				int res1 = recordDAO.recordExist(record); // 投票记录存在的标志
				
				if(res1 == 0) {
					recordDAO.addRecord(record);
					recordDAO.recordActivity(new Integer(aid), new Integer(cid));
					acDAO.recordCountTicket(new Integer(aid));
					json.append("status", "ok");
					json.append("msg", "投票成功！");
				} else {
					json.append("status", "error");
					json.append("msg", "该选手你今天已经投过了，明天再来吧！");
				}
			} else {
				json.append("status", "error");
				json.append("msg", "今天你的投票次数已经用完，明天再来吧！");
			}
			
		} else {
			json.append("status", "error");
			json.append("msg", "该活动已经结束，已经无法投票了！");
		}
		response.getWriter().print(json);
	}
}
