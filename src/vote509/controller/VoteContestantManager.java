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
import vote509.vo.ContestantVo;

@WebServlet("/manage/contestant")
public class VoteContestantManager extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6103424933472993655L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(OtherUtils.getLoginStatus("user", req.getSession())) {
			
			User u = (User) req.getSession().getAttribute("user");
			String activityId = req.getParameter("activityId");
			ContestantDAO cDao = new ContestantDAO();
			// 判断id是否存在
			if(!(null == activityId || "".equals(activityId) || "null".equals(activityId))) {
				String giveup = req.getParameter("giveup");
				String type = req.getParameter("type");
				String keyword = req.getParameter("keyword");
				List<ContestantVo> contestants = null;
				// 判断giveup是否存在
				if(!(null == giveup || "".equals(giveup) || "null".equals(giveup))) {
					int g = Integer.parseInt(giveup); // 获取放弃
					// 按三种条件查询
					if("1".equals(type)) {
						int n = Integer.parseInt(keyword); // 选手号解析
						contestants = cDao.getContestantByActivity(Integer.parseInt(activityId), u.getId(), g, n);
					} else if("2".equals(type)) {
						contestants = cDao.getContestantByActivity(Integer.parseInt(activityId), u.getId(), g, keyword);
					} else if("3".equals(type)){
						// 判断是否匹配，例如(20-30)
						if(OtherUtils.regexArea(keyword)) {
							int min = Integer.parseInt(keyword.split("-")[0]);
							int max = Integer.parseInt(keyword.split("-")[1]);
							contestants = cDao.getContestantByActivity(Integer.parseInt(activityId), u.getId(), g, min, max);
						} else {
							contestants = cDao.getContestantByActivity(Integer.parseInt(activityId), u.getId(), g);
							req.setAttribute("msg", "输入格式有误，正确格式为“xx-xx”");
						}
					} else {
						contestants = cDao.getContestantByActivity(Integer.parseInt(activityId), u.getId(), g);
					}
				} else {
					// 按三种条件查询
					if("1".equals(type)) {
						int n = Integer.parseInt(keyword); // 选手号解析
						// -1为没有在筛选弃权时展示的数据
						contestants = cDao.getContestantByActivity(Integer.parseInt(activityId), u.getId(), -1, n);
					} else if("2".equals(type)) {
						contestants = cDao.getContestantByActivity(Integer.parseInt(activityId), u.getId(), -1, keyword);
					} else if("3".equals(type)){
						// 判断是否匹配，例如(20-30)
						if(OtherUtils.regexArea(keyword)) {
							int min = Integer.parseInt(keyword.split("-")[0]);
							int max = Integer.parseInt(keyword.split("-")[1]);
							contestants = cDao.getContestantByActivity(Integer.parseInt(activityId), u.getId(), -1, min, max);
						} else {
							contestants = cDao.getContestantByActivity(Integer.parseInt(activityId), u.getId(), -1);
							req.setAttribute("msg", "输入格式有误，正确格式为“20-30”");
						}
					} else {
						contestants = cDao.getContestantByActivity(Integer.parseInt(activityId), u.getId());
					}
				}
				req.setAttribute("activityId", activityId);
				req.setAttribute("contestants", contestants);
				req.setAttribute("giveup", giveup);
				req.setAttribute("keyword", keyword);
			}
			req.getRequestDispatcher("/WEB-INF/jsp/backend/contestant.jsp").forward(req, resp);
		} else {
			OtherUtils.alertByframe("登录状态失效，请重新登录！", "/inlinevote/manage/login", resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	

}
