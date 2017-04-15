package Attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Attendance/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init( ServletConfig config ) throws ServletException{
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("error", null);
		request.getRequestDispatcher( "/WEB-INF/Login.jsp" ).forward(request, response );
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user")==null?"":request.getParameter("user");
		String pw = request.getParameter("pw")==null?"":request.getParameter("pw");
		
		Connection c = null;
		Boolean pass = false;
		int instructorID = -1;
		
		ArrayList<String> courses = new ArrayList();
		 
		try{
			String url = "jdbc:mysql://localhost/stars";
			String username = "";
			String password = "";
            
			c = DriverManager.getConnection(url,username,password);
			Statement stmt = c.createStatement();
			
			//Queries all instructors that are in DB
			ResultSet rs = stmt.executeQuery("select * from instructors");
			while(rs.next()){
				if(rs.getString("username").equals(user) && rs.getString("password").equals(pw)){
					request.getSession().setAttribute("user", rs.getString("instructor_lastname"));
					instructorID = rs.getInt("instructor_id");
					pass=true;
				}
			}
			
			//Queries for all courses that the user has under his ID
			rs = stmt.executeQuery("select * from class where instructor_id = '"+instructorID+"'");
			while(rs.next()){
				courses.add(rs.getString("course_name"));
			}
			
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
		if(pass){
			request.getSession().setAttribute("instructorID", instructorID);
			request.getSession().setAttribute("courses", courses);
			request.getRequestDispatcher( "/WEB-INF/Setup.jsp" ).forward(request, response );		
		}else{
			request.getRequestDispatcher( "/WEB-INF/Login.jsp" ).forward(request, response );
		}
	}

}
