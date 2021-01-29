package vote509.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import vote509.dao.impl.*;
import vote509.entity.User;
import vote509.util.OtherUtils;
import vote509.vo.ActivityVo;

/**
 * Servlet implementation class VoteActivityManager
 */
@WebServlet("/manage/activity")
public class VoteActivityManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ActivityDAO activityDAO = new ActivityDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteActivityManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 查询当前用户的活动类别
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(OtherUtils.getLoginStatus("user", request.getSession())) {
			String status = request.getParameter("status"); // 活动状态类别查询
			String type = request.getParameter("type"); // 查询类别
			String keyword = request.getParameter("keyword"); // 查询关键字
			User u = (User) request.getSession().getAttribute("user");
			// 参数为空，或者参数类别为all
			if(status == null || "all".equals(status)) {
				String page = request.getParameter("page");
				request.setAttribute("count", activityDAO.countGeneuserActivity(u.getId())); // 获取当前用户的活动数
				List<ActivityVo> activities = null;
				request.setAttribute("page", page == null ? "1" : page);
				if(page != null) {
					if(type == null || "".equals(type) || "null".equals(type))
						activities = activityDAO.getActivityAll(Integer.parseInt(page), 10, u.getId());
					else {
						activities = activityDAO.getActivityByName(Integer.parseInt(page), 10, u.getId(), keyword);
						request.setAttribute("count", activityDAO.getActivityByNameCount(u.getId(), keyword));
					}
				}
				else if ("".equals(page) || page == null) {
					if(type == null || "".equals(type) || "null".equals(type))
						activities = activityDAO.getActivityAll(1, 10, u.getId());
					else {
						activities = activityDAO.getActivityByName(1, 10, u.getId(), keyword);
						request.setAttribute("count", activityDAO.getActivityByNameCount(u.getId(), keyword));
					}
				}
				request.setAttribute("activities", activities);
			// 参数为nostart，显示未开始的活动
			} else if("nostart".equals(status)) {
				String page = request.getParameter("page");
				int count = activityDAO.countActivity(u.getId(), 0);
				request.setAttribute("count", count); // 获取当前用户的活动数
				List<ActivityVo> activities = null;
				request.setAttribute("page", page == null ? "1" : page);
				if(page != null) {
					if(type == null || "".equals(type) || "null".equals(type))
						activities = activityDAO.getActivityAll(Integer.parseInt(page), 10, 0, u.getId());
					else {
						activities = activityDAO.getActivityByName(Integer.parseInt(page), 10, u.getId(), 0, keyword);
						request.setAttribute("count", activityDAO.getActivityByNameCount(u.getId(), 0, keyword));
					}
				}
				else if ("".equals(page) || page == null) {
					// activities = activityDAO.getActivityAll(1, 10, 0, u.getId());
					if(type == null || "".equals(type) || "null".equals(type))
						activities = activityDAO.getActivityAll(1, 10, 0, u.getId());
					else {
						activities = activityDAO.getActivityByName(1, 10, u.getId(), 0, keyword);
						request.setAttribute("count", activityDAO.getActivityByNameCount(u.getId(), 0, keyword));
					}
				}
				request.setAttribute("activities", activities);
			// 参数为start，显示参加的活动
			} else if("start".equals(status)) {
				String page = request.getParameter("page");
				request.setAttribute("count", activityDAO.countActivity(u.getId(), 1)); // 获取当前用户的活动数
				List<ActivityVo> activities = null;
				request.setAttribute("page", page == null ? "1" : page);
				if(page != null) {
					// activities = activityDAO.getActivityAll(Integer.parseInt(page), 10, 1, u.getId());
					if(type == null || "".equals(type) || "null".equals(type))
						activities = activityDAO.getActivityAll(Integer.parseInt(page), 10, 1, u.getId());
					else {
						activities = activityDAO.getActivityByName(Integer.parseInt(page), 10, u.getId(), 1, keyword);
						request.setAttribute("count", activityDAO.getActivityByNameCount(u.getId(), 1, keyword));
					}
				}
				else if ("".equals(page) || page == null || "null".equals(type)) {
					// activities = activityDAO.getActivityAll(1, 10, 1, u.getId());
					if(type == null || "".equals(type))
						activities = activityDAO.getActivityAll(1, 10, 1, u.getId());
					else {
						activities = activityDAO.getActivityByName(1, 10, u.getId(), 1, keyword);
						request.setAttribute("count", activityDAO.getActivityByNameCount(u.getId(), 1, keyword));
					}
				}
				request.setAttribute("activities", activities);
			// 参数为stop，显示结束的活动
			} else if("end".equals(status)) {
				String page = request.getParameter("page");
				request.setAttribute("count", activityDAO.countActivity(u.getId(), 2)); // 获取当前用户的活动数
				List<ActivityVo> activities = null;
				request.setAttribute("page", page == null ? "1" : page);
				if(page != null) {
					// activities = activityDAO.getActivityAll(Integer.parseInt(page), 10, 2, u.getId());
					if(type == null || "".equals(type) || "null".equals(type))
						activities = activityDAO.getActivityAll(Integer.parseInt(page), 10, 2, u.getId());
					else {
						activities = activityDAO.getActivityByName(Integer.parseInt(page), 10, u.getId(), 2, keyword);
						request.setAttribute("count", activityDAO.getActivityByNameCount(u.getId(), 2, keyword));
					}
				}
					
				else if ("".equals(page) || page == null) {
					// activities = activityDAO.getActivityAll(1, 10, 2, u.getId());
					if(type == null || "".equals(type) || "null".equals(type))
						activities = activityDAO.getActivityAll(1, 10, 2, u.getId());
					else {
						activities = activityDAO.getActivityByName(1, 10, u.getId(), 2, keyword);
						request.setAttribute("count", activityDAO.getActivityByNameCount(u.getId(), 2, keyword));
					}
				}
				request.setAttribute("activities", activities);
			// 传的是其它的参数
			} else {
				String page = request.getParameter("page");
				request.setAttribute("count", activityDAO.countGeneuserActivity(u.getId())); // 获取当前用户的活动数
				List<ActivityVo> activities = null;
				request.setAttribute("page", page == null ? "1" : page);
				if(page != null) {
					if(type == null || "".equals(type) || "null".equals(type))
						activities = activityDAO.getActivityAll(Integer.parseInt(page), 10, u.getId());
					else {
						activities = activityDAO.getActivityByName(Integer.parseInt(page), 10, u.getId(), keyword);
						request.setAttribute("count", activityDAO.getActivityByNameCount(u.getId(), keyword));
					}
				}
				else if ("".equals(page) || page == null) {
					if(type == null || "".equals(type) || "null".equals(type))
						activities = activityDAO.getActivityAll(1, 10, u.getId());
					else {
						activities = activityDAO.getActivityByName(1, 10, u.getId(), keyword);
						request.setAttribute("count", activityDAO.getActivityByNameCount(u.getId(), keyword));
					}
				}
				request.setAttribute("activities", activities);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/backend/activity.jsp").forward(request, response);
		} else {
			OtherUtils.alertByframe("登录状态失效，请重新登录！", "/inlinevote/manage/login", response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = new JSONObject();
		
		if (OtherUtils.getLoginStatus("user", request.getSession())) {
			if ("delete".equals(request.getParameter("status"))) {
				String[] idArr = request.getParameter("aids").split(",");
				int res = activityDAO.deleteByAids(idArr);
				if (res > 0) {
					json.append("status", "success");
					json.append("msg", "活动删除成功！一共删除了" + res + "条活动记录");
				} else {
					json.append("status", "error");
					json.append("msg", "活动删除失败！");
				}
			}
		} else {
			json.append("status", "login");
			json.append("msg", "登录状态失效，请重新登录！");
		}
		response.getWriter().print(json);
	}
	
}
