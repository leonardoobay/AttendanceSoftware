package Attendance;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Attendance/AddStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher( "/WEB-INF/AddStudent.jsp" ).forward(request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String swipe = request.getParameter("swipe")==null?"":request.getParameter("swipe");
		
		String lastName = swipe.substring(swipe.indexOf('^')+1, swipe.indexOf('/'));
		String firstName = swipe.substring(swipe.indexOf('/')+1, swipe.indexOf('^', swipe.indexOf('/')+1));
		String cin = swipe.substring(swipe.length()-10, swipe.length()-1);
		
		StudentModel newStudent = new StudentModel(firstName,lastName,cin);
		
		request.getSession().setAttribute("newStudent", newStudent);
		request.getRequestDispatcher( "/WEB-INF/AddStudent.jsp" ).forward(request, response );

		
	}

}
