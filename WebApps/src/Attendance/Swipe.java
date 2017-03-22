
package Attendance;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Attendance/Swipe")
public class Swipe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<StudentModel> roster = new ArrayList<>();
		request.getServletContext().setAttribute("roster", roster);
		request.getRequestDispatcher( "/WEB-INF/Swipe.jsp" ).forward(request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String swipe = request.getParameter("swipe")==null?"":request.getParameter("swipe");
	
		if(swipe != "" && swipe != null){
			Date date = new Date();
			SimpleDateFormat hour = new SimpleDateFormat("h");
			SimpleDateFormat minute = new SimpleDateFormat("mm");
		
			int timeHour = Integer.parseInt(hour.format(date));
			int timeMinute = Integer.parseInt(minute.format(date));

			String lastName = swipe.substring(swipe.indexOf('^')+1, swipe.indexOf('/'));
			String firstName = swipe.substring(swipe.indexOf('/')+1, swipe.indexOf('^', swipe.indexOf('/')+1));
			String cin = swipe.substring(swipe.length()-10, swipe.length()-1);
			
			StudentModel student = new StudentModel(firstName,lastName,cin);
			request.setAttribute("swipe", student);
			
			int lateHour = (int) request.getSession().getAttribute("lateHour");
			int lateMinute = (int) request.getSession().getAttribute("lateMinute");

			if(timeHour<=lateHour){
				if(timeMinute<=lateMinute){
					request.getSession().setAttribute("status", "On Time!!");
				}else{
					request.getSession().setAttribute("status", "Late!");
				}
			}else{
				request.getSession().setAttribute("status", "Late!");
			}
		}else{
			request.setAttribute("swipe", null);
		}
		
		request.getRequestDispatcher( "/WEB-INF/Swipe.jsp" ).forward(request, response );
	}

}