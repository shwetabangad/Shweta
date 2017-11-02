package com.java.towing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.towing.bean.UserBean;
import com.java.towing.db.UserDBWrapper;

/**
 * Servlet implementation class User_Update_Servlet
 */
@WebServlet("/User_Update_Servlet")
public class User_Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User_Update_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {

			System.out.println("Inside User Update Servlet");
			UserBean userBean = new UserBean();
			userBean.setUser_id(Integer.parseInt(request.getParameter("id")));
			userBean.setUser_firstname(request.getParameter("firstname"));
			userBean.setUser_lastname(request.getParameter("lastname"));
			userBean.setUser_age(Integer.parseInt(request.getParameter("age")));
			userBean.setUser_gender(request.getParameter("gender"));
			userBean.setUser_address(request.getParameter("address"));
			userBean.setUser_emailid(request.getParameter("emailid"));
			userBean.setUser_mobileno(request.getParameter("mobileno"));
			userBean.setUser_username(request.getParameter("username"));
			userBean.setUser_password(request.getParameter("password"));
			
			UserDBWrapper dbWrapper= new UserDBWrapper();
			dbWrapper.updateUser(userBean);
			if (userBean != null) {
				response.sendRedirect("user_view.jsp");
				
			} else {
				response.sendRedirect("error.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
