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
			// 查询全部活动
			int count = activityDAO.countActivityByUser(u.getId());
			// 查询正在进行的活动
			int doing = activityDAO.countActivity(u.getId(), 1);
			// 查询总票数
			int countTicket = activityDAO.countTickets(u.getId());
			// 查询所有所有活动访问量
			int looks = activityDAO.countLooks(u.getId());
			request.setAttribute("countActivity", count);
			request.setAttribute("activityDoing", doing);
			request.setAttribute("countTickets", countTicket);
			request.setAttribute("countLooks", looks);
			
			// 查询最近5项的活动
			List<ActivityVo> activities = activityDAO.getActivityRecent(u.getId());
			request.setAttribute("activities", activities);
			
			request.getRequestDispatcher("/WEB-INF/jsp/backend/dashboard.jsp").forward(request, response);
		} else {
			OtherUtils.alertByframe("登录状态失效，请重新登录！", "/inlinevote/manage/login", response);
		}
		
	}

	/**
	 * 处理的post请求
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(OtherUtils.getLoginStatus("user", request.getSession())) {
			// 判断请求的功能
			String fun = request.getParameter("type");
			if("update".equals(fun)) {
				
				UserDAO userDAO = new UserDAO();
				
				User u = (User) request.getSession().getAttribute("user");
				
				// 处理用户信息的表单
				OtherUtils.dealWithForms(request, u);
				
				String username = (String) request.getAttribute("username");
				String password = (String) request.getAttribute("password");
				String email = (String) request.getAttribute("email");
				
				// 更改用户名
				int res1 = userDAO.update(u.getId(), username);
				if(res1 != 0) {
					u.setUsername(username);
				}
				// 更改头像
				int res2 = userDAO.updateHeader(u.getId(), (String) request.getAttribute("header"));
				if(res2 != 0) {
					u.setHeader((String) request.getAttribute("header"));
				}
				// 更改邮箱和密码
				int res3 = userDAO.update(u.getId(), email, password);
				if(res3 == 0) {
					OtherUtils.alertByframe("信息更新完成！", "/inlinevote/manage/index", response);
				}
				else {
					request.getSession().removeAttribute("user");
					OtherUtils.alertByframe("信息更新完成，您的邮箱和密码已经重新更改，请重新登录！", "/inlinevote/manage/login", response);
				}
				
			}
		} else {
			OtherUtils.alertByframe("登录状态失效，请重新登录！", "/inlinevote/manage/login", response);
		}
	}

}
