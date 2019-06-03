package com.davidcheah;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String username = req.getParameter("user");

		PrintWriter out = res.getWriter();
		out.println("do get Hello user: " + username);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		String username = req.getParameter("user");

		PrintWriter out = res.getWriter();
		out.println("do psot Hello user: " + username);
	}
}
