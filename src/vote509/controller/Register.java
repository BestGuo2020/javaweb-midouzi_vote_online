package vote509.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import vote509.dbconnect.DBConnector;

@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String againpassword = request.getParameter("confirm_password");
		if (password.equals(againpassword)) {
			Connection conn = DBConnector.getConn();
			String sql = "insert into user (username,password,email) values (?,?,?)";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, username);
				stmt.setString(2, password);
				stmt.setString(3, email);
				int result = stmt.executeUpdate();
				if (result == 1) {
					response.getWriter().append("<script>alert('注册成功'); location.href='manage/login'</script>");
				} else {
					response.getWriter().append("<script>alert('注册失败'); location.href='register.jsp'</script>");
				}
			} catch (SQLException e) {
				response.getWriter().append("<script>alert('该邮箱已经被注册过！！！！！'); location.href='register.jsp'</script>");
			}
		} else {
			response.getWriter().append("<script>alert('两次输入的密码不一致！！！！！'); location.href='register.jsp'</script>");
		}
	}
}