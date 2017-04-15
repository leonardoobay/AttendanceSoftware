package Attendance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Attendance/AddStudent")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String swipe = request.getParameter("swipe")==null?"":request.getParameter("swipe");
		String lastName = swipe.substring(swipe.indexOf('^')+1, swipe.indexOf('/'));
		String firstName = swipe.substring(swipe.indexOf('/')+1, swipe.indexOf('^', swipe.indexOf('/')+1));
		String cin = swipe.substring(swipe.length()-10, swipe.length()-1);
		
		request.getSession().setAttribute("newLastName", lastName);
		request.getSession().setAttribute("newFirstName", firstName);
		request.getSession().setAttribute("newCin", cin);
		request.getRequestDispatcher( "/WEB-INF/AddStudent.jsp" ).forward(request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String lastName = (String) request.getSession().getAttribute("newLastName");
		String firstName = (String) request.getSession().getAttribute("newFirstName");
		String cin = (String) request.getSession().getAttribute("newCin");
		
		StudentModel newStudent = new StudentModel(firstName,lastName,cin);
		Connection c = null;
		 try
	        {
			 	String url = "jdbc:mysql://localhost/stars";
				String username = "";
				String password = "";
	            
	            String sql = "insert into student (firstName, lastName, cin, user_id) values (firstName=?, lastName=?, cin=?, user_id=?)";
	            c = DriverManager.getConnection( url, username, password );
	            
	            PreparedStatement pstmt = c.prepareStatement( sql );;
	        
	            pstmt.setString(1, firstName);
	            pstmt.setString(2, lastName);
	            pstmt.setString(3, cin);
	            pstmt.setInt(4, 1);
	            pstmt.executeUpdate();
	        }
	        catch( SQLException e )
	        {
	            throw new ServletException( e );
	        }
	        finally
	        {
	            try
	            {
	                if( c != null ) c.close();
	            }
	            catch( SQLException e )
	            {
	                throw new ServletException( e );
	            }
	        }
		
		request.getSession().setAttribute("newStudent", newStudent);
		request.getRequestDispatcher( "/WEB-INF/AddStudent.jsp" ).forward(request, response );

		
	}

}
