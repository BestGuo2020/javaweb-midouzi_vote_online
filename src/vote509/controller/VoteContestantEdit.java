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
			// �жϻid�Ƿ����
			if(!("".equals(aid) || null == aid || "null".equals(aid))) {
				// ��ȡѡ��id
				String cid = request.getParameter("id");
				if(cid != null) {
					Integer a = Integer.parseInt(cid);
					Contestant contestant = cDao.getContestantById(a);
					request.setAttribute("contestant", contestant);
				}
				request.setAttribute("aid", aid);
				request.getRequestDispatcher("/WEB-INF/jsp/backend/contestantEdit.jsp").forward(request, response);
			} else {
				OtherUtils.alert("�û�����ڣ�", "/inlinevote/manage/activity", response);
			}
			
		} else {
			OtherUtils.alertByframe("��¼״̬ʧЧ�������µ�¼��", "/inlinevote/manage/login", response);
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
			// �ж�����Ĺ���
			String fun = request.getParameter("type");
			if("update".equals(fun)) {
				User u = (User) request.getSession().getAttribute("user");
				OtherUtils.dealWithFormsContestant(request, u);
				// ѡ��id
				Object id = request.getAttribute("id");
				// ��ȡ�������ݣ��ŵ�ʵ��Bean��
				String name = (String) request.getAttribute("name");
				String introduce = (String) request.getAttribute("introduce");
				String image = (String) request.getAttribute("image");
				// ����ѡ��ʵ����
				Contestant contestant = new Contestant();
				contestant.setName(name);
				contestant.setIntroduce(introduce);
				contestant.setImage(image);
				// �ж�id�Ƿ���ڣ�id���ڣ���Ϊ���²���������Ϊ�½�����
				if("".equals(id) || id == null) {
					// ��ȡ�id
					String a = (String) request.getAttribute("aid");
					int aid = Integer.parseInt(a);
					int res = cDao.addContestant(aid, contestant);
					if(res > 0) {
						json.append("status", "success");
						json.append("msg", "ѡ����ӳɹ�");
					} else {
						json.append("status", "error");
						json.append("msg", "ѡ�����ʧ��");
					}
				} else {
					String s = (String) id;
					// ��ѡ��id��ӵ�ʵ������
					contestant.setId(Integer.parseInt(s));
					int res = cDao.reviseContestant(contestant);
					if(res > 0) {
						json.append("status", "success");
						json.append("msg", "ѡ�ָ��ĳɹ�");
					} else {
						json.append("status", "error");
						json.append("msg", "ѡ�ָ���ʧ��");
					}
				}
			} else if ("giveup".equals(fun)) {
				// ��������
				String isGiveup = request.getParameter("isGiveup");
				// ѡ��id
				String id = request.getParameter("cid");
				Contestant contestant = new Contestant();
				contestant.setGiveup(new Integer(isGiveup));
				contestant.setId(new Integer(id));
				int res = cDao.reviseContestant(contestant);
				if(res > 0) {
					json.append("status", "success");
					json.append("msg", "�����ɹ�");
				} else {
					json.append("status", "error");
					json.append("msg", "����ʧ��");
				}
			} else if ("del".equals(fun)) {
				// ɾ������
				// ѡ��id
				String id = request.getParameter("cid");
				if (!("".equals(id) || id == null)) {
					int res= cDao.delContestant(Integer.parseInt(id));
					if(res > 0) {
						json.append("status", "success");
						json.append("msg", "�����ɹ�");
						
					} else {
						json.append("status", "error");
						json.append("msg", "����ʧ��");
					}
				} else {
					json.append("status", "error");
					json.append("msg", "����ʧ��");
				}
			}
		} else {
			json.append("status", "login");
			json.append("msg", "��¼״̬ʧЧ�������µ�¼��");
		}
		response.getWriter().print(json);
	}

}
