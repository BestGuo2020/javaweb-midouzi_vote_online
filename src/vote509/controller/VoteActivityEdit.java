package vote509.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import vote509.dao.impl.*;
import vote509.entity.Activity;
import vote509.entity.User;
import vote509.util.DateUtils;
import vote509.util.OtherUtils;
import vote509.vo.ActivityVo;

/**
 * Servlet implementation class VoteActivityEdit
 */
@WebServlet("/manage/activityEdit")
public class VoteActivityEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteActivityEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(OtherUtils.getLoginStatus("user", request.getSession())) {
			// 判断请求的功能
			String aid = request.getParameter("aid");
			// 如果修改活动
			if(!("".equals(aid) || null == aid || "null".equals(aid))) {
				User u = (User) request.getSession().getAttribute("user");
				ActivityDAO activityDAO = new ActivityDAO();
				int a = Integer.parseInt(aid);
				Activity aVo = activityDAO.getActivityByUidAndAid2(u.getId(), a);
				request.setAttribute("activity", aVo);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/backend/activityEdit.jsp").forward(request, response);
			
		} else {
			OtherUtils.alertByframe("登录状态失效，请重新登录！", "/inlinevote/manage/login", response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		JSONObject json = new JSONObject();
		if(OtherUtils.getLoginStatus("user", request.getSession())) {
			ActivityDAO acDao = new ActivityDAO();
			User u = (User) request.getSession().getAttribute("user");
			String type = request.getParameter("type");
			if("update".equals(type)) {
				// 表单处理
				OtherUtils.dealWithFormsActivity(request, u);
				// 创建Activity对象
				Activity activity = new Activity();
				// 取值
				String activityName = (String) request.getAttribute("activityName");
				String image = (String) request.getAttribute("image");
				String introduce = (String) request.getAttribute("introduce");
				String timearea = (String) request.getAttribute("area");
				String select = (String) request.getAttribute("select");
				String times = (String) request.getAttribute("times");
				activity.setName(activityName);
				activity.setIntroduece(introduce);
				try {
					activity.setStarttime(DateUtils.setTimeToDate(timearea.split("-")[0]));
					activity.setStoptime(DateUtils.setTimeToDate(timearea.split("-")[1]));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				activity.setTimes(new Integer(times));
				activity.setUserid(u.getId());
				activity.setImage(image);
				
				Object id = request.getAttribute("id");
				// 判断id是否存在，id存在，则更新，否则不更新
				if(id == null) {
					int res = acDao.addActivity(activity);
					if(res > 0) {
						json.append("status", "success");
						json.append("msg", "投票活动添加成功");
					} else {
						json.append("status", "error");
						json.append("msg", "投票活动添加失败");
					}
				} else {
					String s = (String) id;
					activity.setId(Integer.parseInt(s));
					int res = acDao.reviseActivity(activity);
					if(res > 0) {
						json.append("status", "success");
						json.append("msg", "投票活动修改成功");
					} else {
						json.append("status", "error");
						json.append("msg", "投票活动修改失败");
					}
				}
			} else {
				Integer aid = Integer.parseInt(request.getParameter("aid"));
				int res = acDao.deleteByUidAndAid(u.getId(), aid);
				if(res > 0) {
					json.append("status", "success");
					json.append("msg", "投票活动删除成功");
				} else {
					json.append("status", "error");
					json.append("msg", "投票活动删除失败");
				}
			}
		} else {
			json.append("status", "login");
			json.append("msg", "登录状态失效，请重新登录！");
		}
		response.getWriter().print(json);
	}
}
