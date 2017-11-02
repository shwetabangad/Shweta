package com.java.towing.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.towing.bean.PoliceStationBean;
import com.java.towing.db.PoliceStationDBWrapper;

/**
 * Servlet implementation class Police_Station_Add_Servlet
 */
@WebServlet("/Police_Station_Add_Servlet")
public class Police_Station_Add_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Police_Station_Add_Servlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		PoliceStationBean policeStationBean=new PoliceStationBean();
		
		policeStationBean.setPoliceStationName(request.getParameter("name"));
		policeStationBean.setPoliceStationAddress(request.getParameter("address"));
		policeStationBean.setPoliceStationLat(Double.parseDouble(request.getParameter("lat")));
		policeStationBean.setPoliceStationLong(Double.parseDouble(request.getParameter("long")));
		
		PoliceStationDBWrapper policeStationDBWrapper=new PoliceStationDBWrapper();
		int result=policeStationDBWrapper.insertPoliceStation(policeStationBean);
		
		if (result ==1) {
			response.sendRedirect("police_station_view.jsp");
			
		} else {
			response.sendRedirect("police_station_add.jsp");
		
	}
	}
}


