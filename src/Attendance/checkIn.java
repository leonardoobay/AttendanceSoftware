package Attendance;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Attendance/checkIn")
public class checkIn extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher( "/WEB-INF/Swipe.jsp" ).forward(request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<StudentModel> roster = (ArrayList<StudentModel>) request.getServletContext().getAttribute("roster");
		StudentModel student = new StudentModel(request.getParameter("firstName"),request.getParameter("lastName"),request.getParameter("cin"));

		roster.add(student);
		
		request.getServletContext().setAttribute("roster", roster);
		request.getRequestDispatcher( "/WEB-INF/AttendanceList.jsp" ).forward(request, response );
	}

}
