package vote509.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import vote509.dao.IActivityDAO;
import vote509.dao.impl.*;
import vote509.entity.Contestant;
import vote509.entity.User;
import vote509.util.OtherUtils;
import vote509.vo.ActivityVo;

/**
 * Servlet implementation class VoteActivityEdit
 */
@WebServlet("/manage/contestantEdit")
public class VoteContestantEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteContestantEdit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(OtherUtils.getLoginStatus("user", request.getSession())) {
			ContestantDAO cDao = new ContestantDAO();
			String aid = request.getParameter("aid");
			// 判断活动id是否存在
			if(!("".equals(aid) || null == aid || "null".equals(aid))) {
				// 获取选手id
				String cid = request.getParameter("id");
				if(cid != null) {
					Integer a = Integer.parseInt(cid);
					Contestant contestant = cDao.getContestantById(a);
					request.setAttribute("contestant", contestant);
				}
				request.setAttribute("aid", aid);
				request.getRequestDispatcher("/WEB-INF/jsp/backend/contestantEdit.jsp").forward(request, response);
			} else {
				OtherUtils.alert("该活动不存在！", "/inlinevote/manage/activity", response);
			}
			
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
		if(OtherUtils.getLoginStatus("user", request.getSession())) {
			ContestantDAO cDao = new ContestantDAO();
			// 判断请求的功能
			String fun = request.getParameter("type");
			if("update".equals(fun)) {
				User u = (User) request.getSession().getAttribute("user");
				OtherUtils.dealWithFormsContestant(request, u);
				// 选手id
				Object id = request.getAttribute("id");
				// 获取表单的数据，放到实体Bean中
				String name = (String) request.getAttribute("name");
				String introduce = (String) request.getAttribute("introduce");
				String image = (String) request.getAttribute("image");
				// 创建选手实体类
				Contestant contestant = new Contestant();
				contestant.setName(name);
				contestant.setIntroduce(introduce);
				contestant.setImage(image);
				// 判断id是否存在，id存在，则为更新操作，否则为新建操作
				if("".equals(id) || id == null) {
					// 获取活动id
					String a = (String) request.getAttribute("aid");
					int aid = Integer.parseInt(a);
					int res = cDao.addContestant(aid, contestant);
					if(res > 0) {
						json.append("status", "success");
						json.append("msg", "选手添加成功");
					} else {
						json.append("status", "error");
						json.append("msg", "选手添加失败");
					}
				} else {
					String s = (String) id;
					// 将选手id添加到实体类中
					contestant.setId(Integer.parseInt(s));
					int res = cDao.reviseContestant(contestant);
					if(res > 0) {
						json.append("status", "success");
						json.append("msg", "选手更改成功");
					} else {
						json.append("status", "error");
						json.append("msg", "选手更改失败");
					}
				}
			} else if ("giveup".equals(fun)) {
				// 放弃操作
				String isGiveup = request.getParameter("isGiveup");
				// 选手id
				String id = request.getParameter("cid");
				Contestant contestant = new Contestant();
				contestant.setGiveup(new Integer(isGiveup));
				contestant.setId(new Integer(id));
				int res = cDao.reviseContestant(contestant);
				if(res > 0) {
					json.append("status", "success");
					json.append("msg", "操作成功");
				} else {
					json.append("status", "error");
					json.append("msg", "操作失败");
				}
			} else if ("del".equals(fun)) {
				// 删除操作
				// 选手id
				String id = request.getParameter("cid");
				if (!("".equals(id) || id == null)) {
					int res= cDao.delContestant(Integer.parseInt(id));
					if(res > 0) {
						json.append("status", "success");
						json.append("msg", "操作成功");
						
					} else {
						json.append("status", "error");
						json.append("msg", "操作失败");
					}
				} else {
					json.append("status", "error");
					json.append("msg", "操作失败");
				}
			}
		} else {
			json.append("status", "login");
			json.append("msg", "登录状态失效，请重新登录！");
		}
		response.getWriter().print(json);
	}

}
