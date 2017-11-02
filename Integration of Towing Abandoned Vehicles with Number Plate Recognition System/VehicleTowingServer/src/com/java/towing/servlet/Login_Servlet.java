package com.java.towing.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.towing.utilty.NumberRecognation;

/**
 * Servlet implementation class Login_Servlet
 */
@WebServlet("/Login_Servlet")
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login_Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//NumberRecognation.extract(new File("C:\\Users\\abc\\Desktop\\blog.png"));
		try {
			System.out.println("In login servlet ");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			HttpSession session = request.getSession(true);

			if (username.equals("admin") && password.equals("123")) {
				System.out.println("Inside admin section");
				session.setAttribute("role", "ADMIN");
				response.sendRedirect("admin_welcome.jsp");
			} else {
				response.sendRedirect("login.jsp");
				System.out.println("wrong username & password!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}