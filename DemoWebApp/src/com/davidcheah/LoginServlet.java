package com.davidcheah;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	String url = "jdbc:mysql://localhost:3306/demowebappdb";
	String dbUsername = "root";
	String dbPassword = "password";
	
	public void init() {
		// 1. Load JDBC driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}	
	
	public boolean login(String name, String password) {
		try {
			
			// 2. Create a connection
			Connection con = DriverManager.getConnection(url, dbUsername, dbPassword);
			
			// 3. Create a statement
			Statement st = con.createStatement();
			
			String query = "select id from users where username='" + name + "' and password='" + password + "'";
			
			// 4. Create a ResultSet
			ResultSet rs = st.executeQuery(query);
			
			if (rs.next()) {
				// 5. Close all connections
				rs.close();
				st.close();
				con.close();
				return true;
			}
			
			// 5. Close all connections
			rs.close();
			st.close();
			con.close();
			return false;
		}
		catch (SQLException e) {
			System.out.println(e.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		if (username.isEmpty() || password.isEmpty()) {
			res.sendRedirect("LoginPage.jsp");
		} else {
			
			if (login(username, password)) {
				res.sendRedirect("WelcomePage.jsp?user="+username);
			} else {
				res.sendRedirect("LoginPage.jsp");
			}
			
		}
		
	}
}
