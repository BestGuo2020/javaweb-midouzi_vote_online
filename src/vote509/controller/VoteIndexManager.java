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
 * 投票后台主界面
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
		// 判断用户的登录状态
		if(OtherUtils.getLoginStatus("user", request.getSession())) {
			// 得到状态
			String status = request.getParameter("status");
			if("del".equals(status)) {
				UserDAO uDao = new UserDAO();
				// 用户注销
				Integer id = new Integer(request.getParameter("id"));
				// 返回注销状态
				int res = uDao.deleteById(id);
				if(res > 0)
					OtherUtils.alert("用户成功注销，该账号已不可用！", "/inlinevote/", response);
				else
					OtherUtils.alert("用户注销失败！", "/inlinevote/", response);
			} else {
				request.getRequestDispatcher("/WEB-INF/jsp/backend/index.jsp").forward(request, response);
			}
		} else {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append("<script>alert('登录状态失效，请重新登录！'); location.href='/inlinevote/manage/login'</script>");
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
