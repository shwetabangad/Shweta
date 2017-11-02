package com.java.towing.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.towing.bean.VehicleNoBean;
import com.java.towing.db.VehicleNoDBWrapper;

/**
 * Servlet implementation class Driving_Licence_Add_Servlet
 */
@WebServlet("/Vehicle_No_Add_Servlet")
public class Vehicle_No_Add_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Vehicle_No_Add_Servlet() {
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
		
		try {

			System.out.println("Inside Driving License Registration Servlet");
			VehicleNoBean vehicleBean = new VehicleNoBean();
			
			vehicleBean.setVehicle_number(request.getParameter("vehicleNumber"));
			vehicleBean.setVehicle_user_id(Integer.parseInt(request.getParameter("userid")));
			vehicleBean.setVehicle_type(request.getParameter("vehicleType"));
			
			VehicleNoDBWrapper dbWrapper = new VehicleNoDBWrapper();
			dbWrapper.insertVehicleNo(vehicleBean);
			if (vehicleBean != null) {
				response.sendRedirect("vehicle_no_view.jsp");
				
			} else {
				response.sendRedirect("vehicle_no_add.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
