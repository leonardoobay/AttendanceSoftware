package Attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Attendance/Settings")
public class Settings extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher( "/WEB-INF/Settings.jsp" ).forward(request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//verify on-time deadline 
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
		
		//save settings in DB for future use
		Connection c = null;
		int instructorID = (int) request.getSession().getAttribute("instructorID");
		String courseName = request.getParameter("courseName");
		Time deadline = new Time(hour, minute, 0);
		
		try{
			String url = "jdbc:mysql://localhost/stars";
			String username = "";
			String password = "";
            
            String sql = "update class set deadline=? where instructor_id=?  AND course_name=?";
            c = DriverManager.getConnection( url, username, password );
            
            PreparedStatement pstmt = c.prepareStatement( sql );;
        
            pstmt.setTime(1, deadline);
            pstmt.setInt(2,instructorID);
            pstmt.setString(3, courseName);

            pstmt.executeUpdate();
			
		 }catch( SQLException e ){
			 throw new ServletException( e );
	     }
	     finally{
            try{
                if( c != null ) c.close();
            }
            catch( SQLException e ){
                throw new ServletException( e );
            }
	     }
		
		late = hour+":"+minute+" "+ampm;
		request.getSession().setAttribute("error", null);
		request.getSession().setAttribute("late", courseName+": Past "+late+" will be considered late!");
		request.getSession().setAttribute("lateHour", hour);
		request.getSession().setAttribute("lateMinute", minute);
		request.getSession().setAttribute("ampm", ampm);
		request.getRequestDispatcher( "/WEB-INF/Settings.jsp" ).forward(request, response );
	}

}
