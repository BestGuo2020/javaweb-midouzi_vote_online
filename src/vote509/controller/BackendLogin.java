package vote509.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vote509.dao.impl.*;
import vote509.entity.User;
import vote509.util.OtherUtils;

/**
 * Servlet implementation class BackendLogin
 */
@WebServlet("/manage/login")
public class BackendLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackendLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/backend/login.jsp").forward(request, response);
	}

	/**
	 * 在当前路径接收登录请求，登录成功，弹窗。
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDAO userDAO = new UserDAO();
		User u = userDAO.selectByEmailAndPassword(email, password);
		// 判断登录成功
		if(u != null) {
			// 保存会话
			request.getSession().setAttribute("user", u);
			// 设置文档类型
			OtherUtils.alert("登录成功！", "/inlinevote/manage/index", response);
		} else {
			request.setAttribute("info", "登录失败，请检查你的邮箱和密码是否正确！");
			request.getRequestDispatcher("/WEB-INF/jsp/backend/login.jsp").forward(request, response);
		}
	}

}
