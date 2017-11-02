package com.java.towing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.towing.bean.TowingAgentBean;
import com.java.towing.db.TowingAgentDBWrapper;

/**
 * Servlet implementation class Traffic_Police_Update_Servlet
 */
@WebServlet("/Towing_Agent_Update_Servlet")
public class Towing_Agent_Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Towing_Agent_Update_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try
		{
		System.out.println("Inside Traffic Police Update Servlet");
TowingAgentBean towingAgentBean = new TowingAgentBean();
		
		towingAgentBean.setTowing_agent_firstname(request.getParameter("firstname"));
		towingAgentBean.setTowing_agente_lastname(request.getParameter("lastname"));
		towingAgentBean.setTowing_agent_gender(request.getParameter("gender"));
		towingAgentBean.setTowing_agent_address(request.getParameter("address"));
		towingAgentBean.setTowing_agent_emailid(request.getParameter("emailid"));
		towingAgentBean.setTowing_agent_mobileno(request.getParameter("mobileno"));
		towingAgentBean.setTowing_agente_username(request.getParameter("username"));
		towingAgentBean.setTowing_agent_password(request.getParameter("password"));
		towingAgentBean.setTowing_agent_register_lat(Double.parseDouble(request.getParameter("register_lat")));
		towingAgentBean.setTowing_agent_register_long(Double.parseDouble(request.getParameter("register_long")));
		
		
		TowingAgentDBWrapper dbWrapper = new TowingAgentDBWrapper();
		dbWrapper.updateTowingAgent(towingAgentBean);
		if (towingAgentBean != null) {
			response.sendRedirect("traffic_police_view.jsp");
			
		} else {
			response.sendRedirect("error.jsp");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

}
