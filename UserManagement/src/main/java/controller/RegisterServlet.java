package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//get PrintWriter
		PrintWriter out = res.getWriter();
		
		//Set Content type
		res.setContentType("text/html");
		
		//link the bootstrap
		out.println("<link rel ='stylesheet' href= 'css/bootstrap.css'></link>");
		
		
		//get the values
		String name = req.getParameter("userName");
		String email = req.getParameter("email");
		String mobile = req.getParameter("mobile");
		String dob = req.getParameter("dob");
		String city = req.getParameter("city");
		String gender = req.getParameter("gender");
		
		//load jdbc Driver

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Generate the connection
			Connection con = DriverManager.getConnection("jdbc:mysql:///usermanagement","root", "Aditya@4010");
			
		PreparedStatement pst=con.prepareStatement("insert into user_side(name,email,mobile,dob,city,gender) values (?,?,?,?,?,?)");
			

		    // Set The Values
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, mobile);
			pst.setString(4, dob);
			pst.setString(5, city);
			pst.setString(6, gender);
			
			//execute the Query
			int i = pst.executeUpdate();
			  out.println("<div class='card' style='margin:auto; width:300px; margin-top:100px'>");
			if(i!=0)
			{
				out.println("<h2 class='bg-danger text-light text-center'>Record Registered Successfully</h2>");
			}
			else {
				out.println("<h2 class='bg-danger text-light text-center'>Record Not Registered</h2");
			}
			
			
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
