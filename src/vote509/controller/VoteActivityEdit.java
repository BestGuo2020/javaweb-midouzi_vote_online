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
			// �ж�����Ĺ���
			String aid = request.getParameter("aid");
			// ����޸Ļ
			if(!("".equals(aid) || null == aid || "null".equals(aid))) {
				User u = (User) request.getSession().getAttribute("user");
				ActivityDAO activityDAO = new ActivityDAO();
				int a = Integer.parseInt(aid);
				Activity aVo = activityDAO.getActivityByUidAndAid2(u.getId(), a);
				request.setAttribute("activity", aVo);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/backend/activityEdit.jsp").forward(request, response);
			
		} else {
			OtherUtils.alertByframe("��¼״̬ʧЧ�������µ�¼��", "/inlinevote/manage/login", response);
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
				// ������
				OtherUtils.dealWithFormsActivity(request, u);
				// ����Activity����
				Activity activity = new Activity();
				// ȡֵ
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
				// �ж�id�Ƿ���ڣ�id���ڣ�����£����򲻸���
				if(id == null) {
					int res = acDao.addActivity(activity);
					if(res > 0) {
						json.append("status", "success");
						json.append("msg", "ͶƱ���ӳɹ�");
					} else {
						json.append("status", "error");
						json.append("msg", "ͶƱ����ʧ��");
					}
				} else {
					String s = (String) id;
					activity.setId(Integer.parseInt(s));
					int res = acDao.reviseActivity(activity);
					if(res > 0) {
						json.append("status", "success");
						json.append("msg", "ͶƱ��޸ĳɹ�");
					} else {
						json.append("status", "error");
						json.append("msg", "ͶƱ��޸�ʧ��");
					}
				}
			} else {
				Integer aid = Integer.parseInt(request.getParameter("aid"));
				int res = acDao.deleteByUidAndAid(u.getId(), aid);
				if(res > 0) {
					json.append("status", "success");
					json.append("msg", "ͶƱ�ɾ���ɹ�");
				} else {
					json.append("status", "error");
					json.append("msg", "ͶƱ�ɾ��ʧ��");
				}
			}
		} else {
			json.append("status", "login");
			json.append("msg", "��¼״̬ʧЧ�������µ�¼��");
		}
		response.getWriter().print(json);
	}
}
