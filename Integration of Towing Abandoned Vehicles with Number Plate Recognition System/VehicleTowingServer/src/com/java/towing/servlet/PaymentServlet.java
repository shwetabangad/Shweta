package com.java.towing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.SMSApi.SMSApi;
import com.java.towing.bean.IncidenteBean;
import com.java.towing.db.IncidenteDBWrapper;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id=request.getParameter("id");
		IncidenteDBWrapper incidenteDBWrapper = new IncidenteDBWrapper();
		int result = incidenteDBWrapper.UpdateIncidentPayment(id);

		String userMobileNo=incidenteDBWrapper.FetchUserByIncidentId(id);
		IncidenteBean incidenteBean = new IncidenteBean();
		incidenteBean.setIncidenteId(Integer.parseInt(id));

		incidenteBean=incidenteDBWrapper.FetchIncidentById(incidenteBean);

		String msg = "Thanks for your payment of Rs:" + incidenteBean.getIncidenteFineAmount() +" towards your tow vehicle. From: Vehicle Towing System";
		try {
			SMSApi.sendSMSMesage(userMobileNo, msg);
		} catch (Exception e) {

			System.err.println("SMS Sending Failed... Check your Internet connection");
		}

		response.sendRedirect("incident_view.jsp");

	}

}
