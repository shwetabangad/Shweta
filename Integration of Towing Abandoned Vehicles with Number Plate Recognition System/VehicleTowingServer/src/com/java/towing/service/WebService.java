package com.java.towing.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.java.SMSApi.SMSApi;
import com.java.towing.Base64;
import com.java.towing.bean.IncidenteBean;
import com.java.towing.bean.PoliceStationBean;
import com.java.towing.bean.TowingAgentBean;
import com.java.towing.bean.UserBean;
import com.java.towing.db.IncidenteDBWrapper;
import com.java.towing.db.LoginDBWrapper;
import com.java.towing.db.PoliceStationDBWrapper;
import com.java.towing.db.TowingAgentDBWrapper;
import com.java.towing.db.UserDBWrapper;
import com.java.towing.db.VehicleNoDBWrapper;
import com.java.towing.utilty.NumberRecognation;
import com.java.towing.utilty.Utility;

@Path("webService")
public class WebService {

	// private TakeAwayBean takeAwayBean = new TakeAwayBean();

	// The @Context annotation allows us to have certain contextual objects
	// injected into this class.11
	// UriInfo object allows us to get URI information (no kidding).
	@Context
	UriInfo uriInfo;

	// Another "injected" object. This allows us to use the information that's
	// part of any incoming request.
	// We could, for example, get header information, or the requestor's
	// address.
	@Context
	// Request request;
	// Response response;
	HttpServletRequest request;
	HttpServletResponse response;
	public static String path = "";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String respondAsReady() {
		return "Web service is ready!";
	}


	@POST
	@Path("/TowingAgentValidate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject TrafficPoliceValidate(String data) {

		System.out.println("In TowingAgent Validate");
		System.out.println(data);

		JSONObject jsonObject = null;
		try {
			// No. of Input Field Access from Client Side using JSON_OBJECT
			jsonObject = new JSONObject(data);

			String towingAgentUname = jsonObject.getString("UserName");
			String towingAgentPwd = jsonObject.getString("PassWord");


			TowingAgentBean towingAgentBean = new TowingAgentBean();

			towingAgentBean.setTowing_agente_username(towingAgentUname);
			towingAgentBean.setTowing_agent_password(towingAgentPwd);

			// No. of Input Field INSERT Into DATABASE USING LOGIN_DBWRAPPER
			// CLASS
			LoginDBWrapper dbWrapper = new LoginDBWrapper();

			TowingAgentBean bean = dbWrapper.validateTowingAgent(towingAgentBean);

			if (bean != null) {
				jsonObject = new JSONObject();
				jsonObject.put("TowingAgentID", bean.getTowing_agent_id());
				System.out.println(jsonObject);
			} else {
				jsonObject = new JSONObject();
				jsonObject.put("TowingAgentID", 0);
				System.out.println(jsonObject);
			}
		} catch (Exception e) {
			System.out.println(e);
			try {
				jsonObject = new JSONObject();
				jsonObject.put("TrafficPoliceID", -1);
			} catch (Exception e1) {
				System.out.println(e1);
			}
		}
		return jsonObject;
	}



	@POST
	@Path("/TowingAgentLatLong")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject TrafficPoliceLatLong(String data) {

		System.out.println("In TowingAgent LatLong");
		System.out.println(data);

		JSONObject jsonObject = null;
		try {
			// No. of Input Field Access from Client Side using JSON_OBJECT
			jsonObject = new JSONObject(data);

			String towingAgentID = jsonObject.getString("UserID");
			String towingAgentLat = jsonObject.getString("Latitude");
			String towingAgentLong = jsonObject.getString("Longitude");



			TowingAgentBean towingAgentBean = new TowingAgentBean();

			towingAgentBean.setTowing_agent_id(Integer.parseInt(towingAgentID));
			towingAgentBean.setTowing_agent_current_lat(Double.parseDouble(towingAgentLat));
			towingAgentBean.setTowing_agent_current_long(Double.parseDouble(towingAgentLong));
			// No. of Input Field INSERT Into DATABASE USING LOGIN_DBWRAPPER
			// CLASS

			TowingAgentDBWrapper towingAgentDBWrapper = new TowingAgentDBWrapper();
			TowingAgentBean bean = towingAgentDBWrapper.updateTowingAgentStatus(towingAgentBean);
			if (bean != null) {
				jsonObject = new JSONObject();
				jsonObject.put("UserName", bean.getTowing_agent_firstname()+" "+bean.getTowing_agente_lastname());
				System.out.println(jsonObject);
			} else {
				jsonObject = new JSONObject();
				jsonObject.put("UserName", "");
				System.out.println(jsonObject);
			}
		} catch (Exception e) {
			System.out.println(e);
			try {
				jsonObject = new JSONObject();
				jsonObject.put("UserName",-1);
			} catch (Exception e1) {
				System.out.println(e1);
			}
		}
		return jsonObject;
	}


	@POST
	@Path("/AddNewIncidente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject AddNewIncidente(String data) {
		path = request.getContextPath();

		path = this.request.getPathInfo();
		path = Thread.currentThread().getContextClassLoader().getResource("com").getPath();
		System.out.println("In Regiter NewIncidente:" + path);
		path = path.substring(1);
		System.out.println("In Regiter NewIncidente:" + path);
		path = path.substring(0, path.indexOf("classes"));
		System.out.println("In Regiter NewIncidente:" + path);
		//path += "lib/";
		//System.out.println("In Regiter NewIncidente:" + path);
		//System.out.println(data);

		JSONObject jsonObject = null;
		try {
			// No. of Input Field Access from Client Side using JSON_OBJECT
			jsonObject = new JSONObject(data);


			String towingAgentID = jsonObject.getString("userid");
			String vehicleImageFile = jsonObject.getString("imageData");
			String lat=jsonObject.getString("lat");
			String longitude=jsonObject.getString("longitude");


			Double lat1=Double.parseDouble(lat);
			Double long1=Double.parseDouble(longitude);

			String encodedVehicleImageFile = vehicleImageFile;
			byte[] vehicleImageFileByteArray = Base64.decode( encodedVehicleImageFile );

			IncidenteBean incidenteBean = new IncidenteBean();

			incidenteBean.setIncidenteDescription("abc");
			incidenteBean.setIncidenteFineAmount(0);
			incidenteBean.setIncidenteTowingAgentId(Integer.parseInt(towingAgentID));
			incidenteBean.setIncidenteVehicleNoImage(vehicleImageFileByteArray);
			incidenteBean.setIncidentLat(Double.parseDouble(lat));
			incidenteBean.setIncidentLong(Double.parseDouble(longitude));
			// No. of Input Field INSERT Into DATABASS
			// CLASS
			IncidenteDBWrapper incidenteDBWrapper = new IncidenteDBWrapper();

			IncidenteBean bean = incidenteDBWrapper.insertNewIncidente(incidenteBean);

			int incidentId=bean.getIncidenteId();

			System.out.println("incidentId........."+incidentId);
			//police Station Distance Calculation

			PoliceStationDBWrapper  policeStationDBWrapper = new PoliceStationDBWrapper();
			ArrayList<PoliceStationBean> policeStationBeanList=policeStationDBWrapper.fetchAllPoliceStationInfo();

			String policeStationName ="";
			int policeStationId = 0;
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


			//convert Image Into text

			InputStream in = new ByteArrayInputStream(vehicleImageFileByteArray);
			BufferedImage bufferedImage = ImageIO.read(in);

			BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
					bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
			newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);

			ImageIO.write(newBufferedImage, "jpg", new File("E:\\a.jpg"));

			Thread.sleep(1000);
			
			String vehicleNo=NumberRecognation.FinalString(new File("E:\\a.jpg"));
			System.out.println("vehicleNo" + vehicleNo);

			if(vehicleNo.length() > 10)
				vehicleNo = vehicleNo.substring(0, 10);


			//Update incident table with userId and VehicleNo.

			VehicleNoDBWrapper vehicleNoDBWrapper = new VehicleNoDBWrapper();
			int userId=vehicleNoDBWrapper.fetchUseridByVehicleNo(vehicleNo);
			System.out.println("userId"+userId);


			int result = incidenteDBWrapper.UpdateIncident(vehicleNo, userId, incidentId);
			System.out.println("result"+result);

			if(userId != 0)
			{

				//getFine Ammount
				int fineAmount=incidenteDBWrapper.fetchFineByUser(userId);
				System.out.println("fineAmount"+fineAmount);

				//Send Sms To User
				UserDBWrapper userDBWrapper = new UserDBWrapper();
				UserBean userBean= new UserBean();
				userBean.setUser_id(userId);

				userDBWrapper.FindUserById(userBean);

				String mobileNo=userBean.getUser_mobileno();
				System.out.println("MobileNo:"+mobileNo);

				int smsStatus = 1;
				try {
					SMSApi.sendSMSMesage(mobileNo, "Your vehicle has been towed. Please reach police Station "+policeStationName+"," +policeStationAddress+" & pay rs."+fineAmount+"");
				} catch (Exception e) {
					smsStatus = 0;
					System.err.println("SMS Sending Failed... Check your Internet connection");
				}

				// Update fine amount and SMS status.
				incidenteDBWrapper.updateFineAmount(incidentId, fineAmount, smsStatus, policeStationId);
			}

			jsonObject = new JSONObject();
			jsonObject.put("IncidenteID", bean.getIncidenteId());
			System.out.println(jsonObject);
		} catch (Exception e) {
			System.out.println(e);
			try {
				jsonObject = new JSONObject();
				jsonObject.put("IncidenteID", -1);
			} catch (Exception e1) {
				System.out.println(e1);
			}
		}
		return jsonObject;
	}
	@POST
	@Path("/ShowAllIncidente")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONArray ShowAllIncidente(String data) {

		System.out.println("In ShowIncidente");
		System.out.println(data);

		JSONObject jsonObject = null;
		try {
			// No. of Input Field Access from Client Side using JSON_OBJECT
			jsonObject = new JSONObject(data);
			String incidenteId = jsonObject.getString("agentid");
			IncidenteBean incidenteBean = new IncidenteBean();
			incidenteBean.setIncidenteTowingAgentId(Integer.parseInt(incidenteId));
			IncidenteDBWrapper incidenteDBWrapper = new IncidenteDBWrapper();

			JSONArray jsonArray = new JSONArray();
			ArrayList<IncidenteBean> incidenteBeanList=incidenteDBWrapper.fetchIncidentInfoByAgentId(incidenteBean);
			for(IncidenteBean bean : incidenteBeanList)
			{
				System.out.println("i am here..");
				jsonObject = new JSONObject();
				jsonObject.put("incidenteId", bean.getIncidenteId());
				jsonObject.put("fineAmount", bean.getIncidenteFineAmount());
				jsonObject.put("status", bean.getIncidentStatus());
				jsonObject.put("vehicleNo", bean.getNoPlateText());

				byte[] fileContent=bean.getIncidenteVehicleNoImage();

				String fileData=Base64.encodeBytes(fileContent);
				jsonObject.put("vehicleNoImage", fileData);
				jsonArray.put(jsonObject);
				//System.out.println(jsonObject);
			}
			return jsonArray;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jsonObject = new JSONObject();
				jsonObject.put("IncidenteID", -1);
			} catch (Exception e1) {
				System.out.println(e1);
			}
		}
		return null;
	}
}
