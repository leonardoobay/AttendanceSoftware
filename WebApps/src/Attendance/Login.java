package Attendance;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Attendance/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("error", null);
		request.getRequestDispatcher( "/WEB-INF/Login.jsp" ).forward(request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username")==null?"":request.getParameter("username");
		String pw = request.getParameter("pw")==null?"":request.getParameter("pw");
		
		if(username.equals("username") && pw.equals("password")){
			request.getSession().setAttribute("user", "user");
			response.sendRedirect("Swipe");
		}else{
			request.getSession().setAttribute("error", "Username/Password is incorrect");
			request.getRequestDispatcher( "/WEB-INF/Login.jsp" ).forward(request, response );
		}
	}

}
