package Attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		
		 try{
			String url = "jdbc:mysql://localhost/stars";
			String username = "";
			String password = "";
            
			c = DriverManager.getConnection(url,username,password);
			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from users");
			
			while(rs.next()){
				if(rs.getString("username").equals(user) && rs.getString("password").equals(pw)){
					request.getSession().setAttribute("user", user);
					pass=true;
				}
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
			response.sendRedirect("Swipe");
		}else{
			request.getRequestDispatcher( "/WEB-INF/Login.jsp" ).forward(request, response );
		}
	}

}
