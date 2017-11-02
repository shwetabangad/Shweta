package com.android.vehicletowing.webService;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.text.TextUtils;

import com.android.vehicletowing.activity.Base64;
import com.android.vehicletowing.bean.IncidenteBean;
import com.android.vehicletowing.bean.TowingAgentBean;

public class WebServiceParser {
	@SuppressWarnings("unused")
	private Context context;
	private WebServiceCommunictor wSCommunictor;

	public WebServiceParser(Context context, String webServiceURL) {
		this.context = context;
		wSCommunictor = new WebServiceCommunictor(context, webServiceURL);
	}

	public String TrafficPoliceLatLong(Map<String, String> params) {
		String response = wSCommunictor.invokeMethod("TrafficPoliceLatLong",
				params);
		String username = null;
		try {
			if (response != null) {

				JSONObject jsonObject = new JSONObject(response);
				username = jsonObject.getString("UserName");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}

/*	public int TrafficPoliceValidate(Map<String, String> params) {
		String response = wSCommunictor.invokeMethod("TrafficPoliceValidate",
				params);
		int trafficPoliceID = -1;

		try {
			if (response != null) {

				JSONObject jsonObject = new JSONObject(response);
				trafficPoliceID = jsonObject.getInt("TrafficPoliceID");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return trafficPoliceID;
	}
	
	*/
	public TowingAgentBean TowingAgentValidate( Map<String, String> params){
		String response=wSCommunictor.invokeMethod("TowingAgentValidate", params);
		
		try{
			if(!TextUtils.isEmpty(response))
			{
				TowingAgentBean agentBean = new TowingAgentBean();
				JSONObject jsonObject=new JSONObject(response);
				agentBean.setTowing_agent_id(jsonObject.getInt("TowingAgentID"));
				/*userBean.setUseraddress(jsonObject.getString("address"));
				userBean.setUseremail(jsonObject.getString("email"));
				userBean.setUserfirstname(jsonObject.getString("firstname"));
				userBean.setUserlastname(jsonObject.getString("lastname"));
				userBean.setUsermobile(jsonObject.getString("email"));
				userBean.setUserrole(jsonObject.getString("role"));*/
				
				
				
				return agentBean;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	
	
	

	public IncidenteBean AddNewIncidente( Map<String, String> params){
		String response=wSCommunictor.invokeMethod("AddNewIncidente", params);

		try{
			if(!TextUtils.isEmpty(response))
			{
				IncidenteBean incidenteBean = new IncidenteBean();

				JSONObject jsonObject=new JSONObject(response);
			//	incidenteBean.setIncidenteUserId(incidenteUserId);
				
				

				return incidenteBean;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	
	public ArrayList<IncidenteBean> ShowAllIncidente( Map<String, String> params){
		String response=wSCommunictor.invokeMethod("ShowAllIncidente", params);
		ArrayList<IncidenteBean> incidentBeanList = new ArrayList<IncidenteBean>();
		try{
			if(!TextUtils.isEmpty(response))
			{
				JSONArray jsonArray = new JSONArray(response);
				for(int i=0;i<jsonArray.length();i++)
				{
					JSONObject jsonObject = new JSONObject(jsonArray.getString(i));	
					IncidenteBean incidenteBean=new IncidenteBean();
					incidenteBean.setIncidenteId(jsonObject.getInt("incidenteId"));
					incidenteBean.setIncidenteFineAmount(jsonObject.getInt("fineAmount"));
					incidenteBean.setIncidentStatus(jsonObject.getInt("status"));
					String imageString = jsonObject.getString("vehicleNoImage");
					incidenteBean.setNoPlateText(jsonObject.getString("vehicleNo"));
					byte [] imageByte = Base64.decode(imageString);
					incidenteBean.setIncidenteVehicleNoImage(imageByte);
					
					incidentBeanList.add(incidenteBean);
					
				}
				return incidentBeanList;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
}