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
 * ͶƱ����չʾ
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
		// ��ȡ�û�id
		Integer uid = new Integer(request.getParameter("uId"));
		// ��ȡ�id
		Integer aid = new Integer(request.getParameter("aId"));
		Activity activity = acDAO.getActivityByUidAndAid2(uid, aid);
		User u = uDao.selectById(uid);
		// ��ȡ��ѯ����
		String type = request.getParameter("type");
		List<ContestantVo> contestants = null;
		if(null != type || "null".equals(type) || "".equals(type)) {
			String keyword = request.getParameter("keyword");
			// ������������ѯ
			if("1".equals(type)) {
				if("".equals(keyword)) {
					contestants = contestantDAO.getContestantByActivity(aid, uid, 0);
					request.setAttribute("msg", "�㻹û�������ţ�");
				} else {
					int n = Integer.parseInt(keyword); // ѡ�ֺŽ���
					// 0Ϊδ��Ȩ��ѡ��
					contestants = contestantDAO.getContestantByActivity(aid, uid, 0, n);
				}
			} else if("2".equals(type)) {
				contestants = contestantDAO.getContestantByActivity(aid, uid, 0, keyword);
			} else if("3".equals(type)){
				// �ж��Ƿ�ƥ�䣬����(20-30)
				if(OtherUtils.regexArea(keyword)) {
					int min = Integer.parseInt(keyword.split("-")[0]);
					int max = Integer.parseInt(keyword.split("-")[1]);
					contestants = contestantDAO.getContestantByActivity(aid, uid, 0, min, max);
				} else {
					contestants = contestantDAO.getContestantByActivity(aid, uid, 0);
					request.setAttribute("msg", "�����ʽ������ȷ��ʽΪ��20-30��");
				}
			} else {
				// ��ѯȫ��ͶƱ��
				contestants = contestantDAO.getContestantByActivity(aid, uid, 0);
			}
		} else {
			// ���������
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
		// ���÷��ص�����
		JSONObject json = new JSONObject();
		
		String cid = request.getParameter("cid");
		String uid = request.getParameter("uid");
		String aid = request.getParameter("aid");
		// ��ȡip
		String ip = request.getRemoteAddr();
		// ��ѯ�id
		Activity ac = acDAO.getActivityByUidAndAid2(new Integer(uid), new Integer(aid));
		// ��ȡ�״̬
		if (ac.getStatus() == 0) {
			json.append("status", "error");
			json.append("msg", "������ͶƱ���ûδ��ʼ��");
		} else if(ac.getStatus() == 1) {
			// ��ȡ��Ͷѡ����
			int availTimes = ac.getTimes();
			int times = recordDAO.countIpAndAid(ip, new Integer(aid));
			if(times < availTimes) {
				// ��ѯ�ü�¼�Ƿ����
				VoteRecord record = new VoteRecord();
				record.setIp(ip);
				record.setAid(new Integer(aid));
				record.setCid(new Integer(cid));
				
				int res1 = recordDAO.recordExist(record); // ͶƱ��¼���ڵı�־
				
				if(res1 == 0) {
					recordDAO.addRecord(record);
					recordDAO.recordActivity(new Integer(aid), new Integer(cid));
					acDAO.recordCountTicket(new Integer(aid));
					json.append("status", "ok");
					json.append("msg", "ͶƱ�ɹ���");
				} else {
					json.append("status", "error");
					json.append("msg", "��ѡ��������Ѿ�Ͷ���ˣ����������ɣ�");
				}
			} else {
				json.append("status", "error");
				json.append("msg", "�������ͶƱ�����Ѿ����꣬���������ɣ�");
			}
			
		} else {
			json.append("status", "error");
			json.append("msg", "�û�Ѿ��������Ѿ��޷�ͶƱ�ˣ�");
		}
		response.getWriter().print(json);
	}
}
