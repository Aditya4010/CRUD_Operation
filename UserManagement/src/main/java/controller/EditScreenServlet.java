package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
	   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter out = res.getWriter();
		
		//Set Content type
		res.setContentType("text/html");
		
		// get the id
		
		int id = Integer.parseInt(req.getParameter("id"));
		
		//link the bootstrap
		out.println("<link rel ='stylesheet' href= 'css/bootstrap.css'></link>");
	
		//load jdbc Driver

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Generate the connection
			Connection con = DriverManager.getConnection("jdbc:mysql:///usermanagement","root", "Aditya@4010");
			
		PreparedStatement pst=con.prepareStatement("select name,email,mobile,dob,city,gender from user_side where id = ?");
			
		// Set value
		pst.setInt(1, id);
        //select query so take resultset
		
		ResultSet rs = pst.executeQuery();
		rs.next();
		out.println("<div style='margin:auto; width: 500px; margin-top:100px'>");
		out.println("<form action ='edit?id="+id+"' method='post'>");
	    out.println("<table class = table table-hover table-striped>");
	    out.println("<tr>");
	    out.println("<td>Name</td>"); 
	    out.println("<td><input type ='text' name = 'name'value ='"+rs.getString(1)+"'></td>"); 
	    out.println("</tr>");
	    out.println("<tr>");
	    out.println("<td>Email</td>"); 
	    out.println("<td><input type ='email' name = 'email'value ='"+rs.getString(2)+"'></td>"); 
	    out.println("</tr>");
	    out.println("<tr>");
	    out.println("<td>Mobile</td>"); 
	    out.println("<td><input type ='text' name = 'mobile'value ='"+rs.getString(3)+"'></td>"); 
	    out.println("</tr>");
	    out.println("<tr>");
	    out.println("<td>DOB</td>"); 
	    out.println("<td><input type ='date' name = 'dob'value ='"+rs.getString(4)+"'></td>"); 
	    out.println("</tr>");
	    out.println("<tr>");
	    out.println("<td>City</td>"); 
	    out.println("<td><input type ='text' name = 'city' value ='"+rs.getString(5)+"'></td>"); 
	    out.println("</tr>");
	    out.println("<tr>");
	    out.println("<td>Gender</td>"); 
	    out.println("<td><input type ='text' name = 'gender' value ='"+rs.getString(6)+"'></td>"); 
	    out.println("</tr>");
	    out.println("<tr>");
	    out.println("<td><button type = 'submit'class= 'btn btn-outline-success'>Edit</button></td>"); 
	    out.println("<td><button type = 'reset' class= 'btn btn-outline-danger'>Cancel</button</td>"); 
	    out.println("</tr>");
	    out.println("</table>");
		out.println("</form>");
		}catch(Exception e) {
			
			out.println("<h2 class='bg-danger text-light text-center'>" +e.getMessage());
			e.printStackTrace();
		}
		
		out.println("<a  href = 'home.html' <button class= 'btn btn-outline-success'>Home</button></a>");
		
		out.println("</div>");
		//close the stream
		out.close();
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}


