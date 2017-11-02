package com.java.towing.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.util.DefaultPrettyPrinter.FixedSpaceIndenter;

import com.java.SMSApi.SMSApi;
import com.java.towing.bean.IncidenteBean;
import com.java.towing.bean.PoliceStationBean;
import com.java.towing.bean.UserBean;
import com.java.towing.db.IncidenteDBWrapper;
import com.java.towing.db.PoliceStationDBWrapper;
import com.java.towing.db.UserDBWrapper;
import com.java.towing.db.VehicleNoDBWrapper;
import com.java.towing.utilty.Utility;

/**
 * Servlet implementation class Incidente_Update_Servlet
 */
@WebServlet("/Incidente_Update_Servlet")
public class UpdateNoPlateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateNoPlateServlet() {
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

		String Id=request.getParameter("id");
		int incidentId=Integer.parseInt(Id);

		String NoPlate=request.getParameter("noPlate");
		String lat=request.getParameter("lat");
		String lon=request.getParameter("long");
		System.out.println("lat"+lat);
		System.out.println("lon"+lon);

		Double lat1=Double.parseDouble(lat);
		Double long1=Double.parseDouble(lon);

		IncidenteBean incidenteBean = new IncidenteBean();

		incidenteBean.setIncidenteId(Integer.parseInt(Id));

		incidenteBean.setNoPlateText(NoPlate);

		IncidenteDBWrapper incidenteDBWrapper = new IncidenteDBWrapper();
		int result=incidenteDBWrapper.UpdateNoPlate(incidenteBean);

		PoliceStationDBWrapper  policeStationDBWrapper = new PoliceStationDBWrapper();
		ArrayList<PoliceStationBean> policeStationBeanList=policeStationDBWrapper.fetchAllPoliceStationInfo();

		int policeStationId = 0;
		String policeStationName ="";
		String policeStationAddress="";
		Double minimumDistance=1000000.000;
		Double currentDistance=0.0;

		for(PoliceStationBean stationBean:policeStationBeanList)
		{
			currentDistance=Utility.distance(stationBean.getPoliceStationLat(), stationBean.getPoliceStationLong(), lat1, long1, "k");

			if(currentDistance<minimumDistance)
			{
				minimumDistance=currentDistance;
				policeStationId = stationBean.getPoliceStationId();
				policeStationName=stationBean.getPoliceStationName();
				policeStationAddress=stationBean.getPoliceStationAddress();

			}
			System.out.println("policeStationName"+policeStationName);
		}

		//Update incident table with userId and VehicleNo.
		VehicleNoDBWrapper vehicleNoDBWrapper = new VehicleNoDBWrapper();
		int userId=vehicleNoDBWrapper.fetchUseridByVehicleNo(NoPlate);
		System.out.println("userId"+userId);
		incidenteDBWrapper.UpdateIncident(NoPlate, userId, incidentId);


		//getFine Ammount
		int fineAmount=incidenteDBWrapper.fetchFineByUser(userId);
		System.out.println("fineAmount"+fineAmount);


		//Send Sms To User
		UserDBWrapper userDBWrapper = new UserDBWrapper();
		UserBean userBean= new UserBean();
		userBean.setUser_id(userId);
		userDBWrapper.FindUserById(userBean);
		String mobileNo=userBean.getUser_mobileno();
		System.out.println("MobileNo"+mobileNo);

		int smsStatus = 1;
		try {
			SMSApi.sendSMSMesage(mobileNo, "You Are vehicle has been towed please reach police Station "+policeStationName+","+policeStationAddress+" & pay ruppes "+fineAmount+"");
		} catch (Exception e) {
			smsStatus=0;
			System.err.println("SMS Sending Failed... Check your Internet connection");
		}	

		// Update fine amount and SMS status.
		incidenteDBWrapper.updateFineAmount(incidentId, fineAmount, smsStatus, policeStationId);

		if(result==1)

		{
			response.sendRedirect("incident_view.jsp");
		}


	}

}
