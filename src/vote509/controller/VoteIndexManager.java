package vote509.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vote509.dao.impl.UserDAO;
import vote509.util.OtherUtils;

/**
 * ͶƱ��̨������
 */
@WebServlet("/manage/index")
public class VoteIndexManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteIndexManager() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �ж��û��ĵ�¼״̬
		if(OtherUtils.getLoginStatus("user", request.getSession())) {
			// �õ�״̬
			String status = request.getParameter("status");
			if("del".equals(status)) {
				UserDAO uDao = new UserDAO();
				// �û�ע��
				Integer id = new Integer(request.getParameter("id"));
				// ����ע��״̬
				int res = uDao.deleteById(id);
				if(res > 0)
					OtherUtils.alert("�û��ɹ�ע�������˺��Ѳ����ã�", "/inlinevote/", response);
				else
					OtherUtils.alert("�û�ע��ʧ�ܣ�", "/inlinevote/", response);
			} else {
				request.getRequestDispatcher("/WEB-INF/jsp/backend/index.jsp").forward(request, response);
			}
		} else {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append("<script>alert('��¼״̬ʧЧ�������µ�¼��'); location.href='/inlinevote/manage/login'</script>");
		}
			
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
