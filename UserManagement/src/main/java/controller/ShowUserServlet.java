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

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter out = res.getWriter();
		
		//Set Content type
		res.setContentType("text/html");
		
		//link the bootstrap
		out.println("<link rel ='stylesheet' href= 'css/bootstrap.css'></link>");
	
		out.println("<marquee><h1 class= 'text-primary'>User Data</h1></marquee>");
		//load jdbc Driver

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Generate the connection
			Connection con = DriverManager.getConnection("jdbc:mysql:///usermanagement","root", "Aditya@4010");
			
		PreparedStatement pst=con.prepareStatement("select id,name,email,mobile,dob,city,gender from user_side");
			
        //select query so take resultset
		
		ResultSet rs = pst.executeQuery();
		out.println("<div style='margin:auto; width: 900px; margin-top:100px'>");
	out.println("<table class='table table-hover table-striped'>");
		out.println("<tr>");
		   out.println("<th>ID</th>");
		   out.println("<th>Name</th>");
		   out.println("<th>Email</th>");
		   out.println("<th>Mobile No</th>");
		   out.println("<th> DOB</th>");
		   out.println("<th>City</th>");
		   out.println("<th>Gender</th>");
		   // Add Edit and Delete buttons in show user page
		   out.println("<th>Edit</th>");
		   out.println("<th>Delete</th>");
		   out.println("</tr>");
		
		while(rs.next()) {
	    out.println("<tr>");
			out.println("<td>"+rs.getInt(1)+"</td>");
			out.println("<td>"+rs.getString(2)+"</td>");
			out.println("<td>"+rs.getString(3)+"</td>");
			out.println("<td>"+rs.getString(4)+"</td>");
			out.println("<td>"+rs.getString(5)+"</td>");
			out.println("<td>"+rs.getString(6)+"</td>");
			out.println("<td>"+rs.getString(7)+"</td>");
			// edit and delete option for selecting particular row 
			out.println("<td><a href='editurl?id="+rs.getInt(1)+"'>Edit</a></td>");
			out.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
	    out.println("</tr>");
	
		}
	out.println("</table>");
		  
		}catch(Exception e) {
			
			out.println("<h2 class='bg-danger text-light text-center'>" +e.getMessage());
			e.printStackTrace();
		}
		
		out.println("<button class= 'btn btn-outline-success d-block'<a  href = 'home.html' >Home</a></button>");
		
		out.println("</div>");
		//close the stream
		out.close();
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}
}
