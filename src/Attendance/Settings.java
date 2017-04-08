package Attendance;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Attendance/Settings")
public class Settings extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher( "/WEB-INF/Settings.jsp" ).forward(request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int hour = 0;
		int minute = 0;
		String ampm = "";
		String late = null;
		try{
			hour = Integer.parseInt(request.getParameter("hour"));
			minute = Integer.parseInt(request.getParameter("minute"));
			ampm = request.getParameter("ampm")==null?"":request.getParameter("ampm");
		}catch(Exception e){
			request.getSession().setAttribute("error", "What you do wrong!?!?");
			request.getRequestDispatcher( "/WEB-INF/Settings.jsp" ).forward(request, response );
		}
		
		late = hour+":"+minute+" "+ampm;
		request.getSession().setAttribute("error", null);
		request.getSession().setAttribute("late", "Past "+late+" will be considered late!");
		request.getSession().setAttribute("lateHour", hour);
		request.getSession().setAttribute("lateMinute", minute);
		request.getSession().setAttribute("ampm", ampm);
		request.getRequestDispatcher( "/WEB-INF/Settings.jsp" ).forward(request, response );
	}

}
