package vote509.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vote509.dao.impl.*;
import vote509.entity.User;
import vote509.util.OtherUtils;
import vote509.vo.ActivityVo;

/**
 * Servlet implementation class VoteUserinfoManager
 */
@WebServlet("/manage/userinfo")
public class VoteUserinfoManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteUserinfoManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(OtherUtils.getLoginStatus("user", request.getSession())) {
			
			User u = (User) request.getSession().getAttribute("user");
			
			ActivityDAO activityDAO = new ActivityDAO();
			// ��ѯȫ���
			int count = activityDAO.countActivityByUser(u.getId());
			// ��ѯ���ڽ��еĻ
			int doing = activityDAO.countActivity(u.getId(), 1);
			// ��ѯ��Ʊ��
			int countTicket = activityDAO.countTickets(u.getId());
			// ��ѯ�������л������
			int looks = activityDAO.countLooks(u.getId());
			request.setAttribute("countActivity", count);
			request.setAttribute("activityDoing", doing);
			request.setAttribute("countTickets", countTicket);
			request.setAttribute("countLooks", looks);
			
			// ��ѯ���5��Ļ
			List<ActivityVo> activities = activityDAO.getActivityRecent(u.getId());
			request.setAttribute("activities", activities);
			
			request.getRequestDispatcher("/WEB-INF/jsp/backend/dashboard.jsp").forward(request, response);
		} else {
			OtherUtils.alertByframe("��¼״̬ʧЧ�������µ�¼��", "/inlinevote/manage/login", response);
		}
		
	}

	/**
	 * �����post����
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(OtherUtils.getLoginStatus("user", request.getSession())) {
			// �ж�����Ĺ���
			String fun = request.getParameter("type");
			if("update".equals(fun)) {
				
				UserDAO userDAO = new UserDAO();
				
				User u = (User) request.getSession().getAttribute("user");
				
				// �����û���Ϣ�ı�
				OtherUtils.dealWithForms(request, u);
				
				String username = (String) request.getAttribute("username");
				String password = (String) request.getAttribute("password");
				String email = (String) request.getAttribute("email");
				
				// �����û���
				int res1 = userDAO.update(u.getId(), username);
				if(res1 != 0) {
					u.setUsername(username);
				}
				// ����ͷ��
				int res2 = userDAO.updateHeader(u.getId(), (String) request.getAttribute("header"));
				if(res2 != 0) {
					u.setHeader((String) request.getAttribute("header"));
				}
				// �������������
				int res3 = userDAO.update(u.getId(), email, password);
				if(res3 == 0) {
					OtherUtils.alertByframe("��Ϣ������ɣ�", "/inlinevote/manage/index", response);
				}
				else {
					request.getSession().removeAttribute("user");
					OtherUtils.alertByframe("��Ϣ������ɣ���������������Ѿ����¸��ģ������µ�¼��", "/inlinevote/manage/login", response);
				}
				
			}
		} else {
			OtherUtils.alertByframe("��¼״̬ʧЧ�������µ�¼��", "/inlinevote/manage/login", response);
		}
	}

}
