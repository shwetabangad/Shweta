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
 * Servlet implementation class Driving_License_Update_Servlet
 */
@WebServlet("/Driving_License_Update_Servlet")
public class Vehicle_No_Update_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Vehicle_No_Update_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*try {

			System.out.println("Inside Driving License Update Servlet");
			VehicleNoBean drivingLicenseBean = new VehicleNoBean();
			
			drivingLicenseBean.setDriving_license_id(Integer.parseInt(request.getParameter("id")));
			drivingLicenseBean.setDriving_license_number(request.getParameter("drivinglicenceNumber"));
			drivingLicenseBean.setDriving_license_user_id(Integer.parseInt(request.getParameter("userid")));
			drivingLicenseBean.setDriving_license_type(request.getParameter("drivinglicenseType"));
			
			VehicleNoDBWrapper dbWrapper = new VehicleNoDBWrapper();
			dbWrapper.updateDrivingLicense(drivingLicenseBean);
			if (drivingLicenseBean != null) {
				response.sendRedirect("driving_license_view.jsp");
				
			} else {
				response.sendRedirect("error.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	}
}
