package com.java.towing.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.java.towing.bean.IncidenteBean;
import com.java.towing.bean.PoliceStationBean;

public class IncidenteDBWrapper {

	Connection connection = null;
	PreparedStatement preparestatement = null;
	Statement statement= null;
	ResultSet rs = null;

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// INSERT
	public IncidenteBean insertNewIncidente(IncidenteBean incidenteBean) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();

		try {



			FileOutputStream vehiclefileOutputStream = new FileOutputStream("vehicleImafeFile.jpg");
			vehiclefileOutputStream.write(incidenteBean.getIncidenteVehicleNoImage());
			vehiclefileOutputStream.flush();
			vehiclefileOutputStream.close();		
			//

			FileInputStream inVehicle = new FileInputStream(new File("vehicleImafeFile.jpg"));

			preparestatement= connection.prepareStatement( "INSERT INTO incidente_table (incidente_description,incidente_vehicle_no_image,incidente_user_id,incidente_towing_agent_id,incidente_fine_amount,incidente_lat,incidente_long,incidente_date) VALUES (?,?,?,?,?,?,?,now())");

			preparestatement.setString(1, incidenteBean.getIncidenteDescription());
			preparestatement.setBinaryStream(2, inVehicle);
			preparestatement.setInt(3, incidenteBean.getIncidenteUserId());
			preparestatement.setInt(4, incidenteBean.getIncidenteTowingAgentId());
			preparestatement.setInt(5, incidenteBean.getIncidenteFineAmount());
			preparestatement.setDouble(6, incidenteBean.getIncidentLat());
			preparestatement.setDouble(7, incidenteBean.getIncidentLong());
			preparestatement.executeUpdate();

			preparestatement=connection.prepareStatement("select max(incidente_id) from incidente_table");
			System.out.println();
			ResultSet rs=preparestatement.executeQuery();

			rs.beforeFirst();
			if(rs.next())
			{
				incidenteBean.setIncidenteId(rs.getInt("max(incidente_id)"));
			}


			return incidenteBean;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}


	public int UpdateIncident(String vehicleNo,int userId,int incidentId)
	{
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();

		System.out.println("vehicleNo"+vehicleNo);
		System.out.println("userId"+userId);
		System.out.println("incidentId"+incidentId);
		System.out.println("In update Incident");

		try {
			preparestatement=connection.prepareStatement("update incidente_table set no_plate_text=?, incidente_user_id=? where incidente_id=?");

			System.out.println("after update ");
			preparestatement.setString(1, vehicleNo);
			preparestatement.setInt(2, userId);
			preparestatement.setInt(3, incidentId);

			preparestatement.executeUpdate();

			return 1;



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}

	public int UpdateNoPlate(IncidenteBean incidenteBean)
	{
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();



		try {
			preparestatement=connection.prepareStatement("update incidente_table set no_plate_text=? where incidente_id=?");

			System.out.println("after update ");

			preparestatement.setString(1, incidenteBean.getNoPlateText());

			preparestatement.setInt(2, incidenteBean.getIncidenteId());

			preparestatement.executeUpdate();

			return 1;



		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}




	public ResultSet fetchAllIncidente() {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "select * from incidente_table";
		ResultSet resultSet = null;
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultSet;
	}

	public ArrayList<IncidenteBean> fetchIncidentInfo() {
		ResultSet resultSet = fetchAllIncidente();
		ArrayList<IncidenteBean> incidentStationbeanList = new ArrayList<IncidenteBean>();
		if (resultSet != null) {
			try {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					incidentStationbeanList.add(fetchIncidentFromResultSet(resultSet));
				}
				resultSet.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return incidentStationbeanList;
	}

	private IncidenteBean fetchIncidentFromResultSet(
			ResultSet resultSet) {
		IncidenteBean incidenteBean = new IncidenteBean();
		try {
			incidenteBean.setIncidenteId(resultSet
					.getInt("incidente_id"));
			incidenteBean.setIncidenteDescription(resultSet
					.getString("incidente_description"));
			incidenteBean.setIncidenteFineAmount(resultSet
					.getInt("incidente_fine_amount"));
			incidenteBean.setNoPlateText(resultSet
					.getString("no_plate_text"));
			incidenteBean.setIncidenteVehicleNoImage(resultSet.getBytes("incidente_vehicle_no_image"));
			incidenteBean.setIncidentLat(resultSet.getDouble("incidente_lat"));
			incidenteBean.setIncidentLong(resultSet.getDouble("incidente_long"));
			incidenteBean.setIncidenteDate(resultSet
					.getString("incidente_date"));
			incidenteBean.setSmsStatus(resultSet
					.getString("sms_sent_status"));
			incidenteBean.setIncidentStatus(resultSet.getInt("incidente_status"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return incidenteBean;
	}


	public ResultSet fetchIncidenteById(IncidenteBean incidenteBean) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		try {
			preparestatement =connection.prepareStatement("select * from incidente_table where incidente_towing_agent_id=?");
			preparestatement.setInt(1, incidenteBean.getIncidenteTowingAgentId());

			ResultSet resultSet = null;

			resultSet = preparestatement.executeQuery();
			return resultSet;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public ArrayList<IncidenteBean> fetchIncidentInfoByAgentId(IncidenteBean incidenteBean) {
		ResultSet resultSet = fetchIncidenteById(incidenteBean);
		ArrayList<IncidenteBean> incidentStationbeanList = new ArrayList<IncidenteBean>();
		if (resultSet != null) {
			try {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					System.out.println("aaa");
					incidentStationbeanList.add(fetchIncidentFromResultSetById(resultSet));
				}
				resultSet.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		else
		{System.out.println("resultSet = null");}
		return incidentStationbeanList;
	}

	public IncidenteBean fetchIncidentFromResultSetById(
			ResultSet resultSet) {
		IncidenteBean incidenteBean = new IncidenteBean();
		try {
			incidenteBean.setIncidenteId(resultSet
					.getInt("incidente_id"));
			incidenteBean.setIncidenteDescription(resultSet
					.getString("incidente_description"));
			incidenteBean.setIncidenteUserId(resultSet
					.getInt("incidente_user_id"));
			incidenteBean.setIncidenteTowingAgentId(resultSet
					.getInt("incidente_towing_agent_id"));
			incidenteBean.setIncidenteFineAmount(resultSet
					.getInt("incidente_fine_amount"));
			incidenteBean.setIncidenteVehicleNoImage(resultSet.getBytes("incidente_vehicle_no_image"));
			incidenteBean.setIncidentLat(resultSet.getDouble("incidente_lat"));
			incidenteBean.setIncidentLong(resultSet.getDouble("incidente_long"));
			incidenteBean.setIncidenteDate(resultSet
					.getString("incidente_date"));
			incidenteBean.setIncidentStatus(resultSet
					.getInt("incidente_status"));
			incidenteBean.setNoPlateText(resultSet
					.getString("no_plate_text"));
			System.out.println("incidente_user_id:" + incidenteBean.getIncidenteId());
		} catch (Exception e) {
			System.out.println(e);
		}
		return incidenteBean;
	}


	public IncidenteBean FetchIncidentById(
			IncidenteBean incidenteBean) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		try {
			preparestatement = connection
					.prepareStatement("Select * from incidente_table where incidente_id=?;");
			preparestatement
			.setInt(1, incidenteBean.getIncidenteId());
			ResultSet resultSet = preparestatement.executeQuery();
			resultSet.beforeFirst();
			if (resultSet.next()) {
				incidenteBean.setIncidenteId(resultSet
						.getInt("incidente_id"));
				incidenteBean.setIncidenteDescription(resultSet
						.getString("incidente_description"));
				incidenteBean.setNoPlateText(resultSet
						.getString("no_plate_text"));
				incidenteBean.setIncidenteFineAmount(resultSet
						.getInt("incidente_fine_amount"));
				incidenteBean.setIncidenteDate(resultSet
						.getString("incidente_date"));

				return incidenteBean;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}



	public int deleteIncident(String incidentId) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "DELETE from incidente_table where incident_id = '"
				+ incidentId + "'";
		try {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int fetchFineByUser(int userId) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		try {
			preparestatement =connection.prepareStatement("select incidente_fine_amount from incidente_table where incidente_user_id=? order by incidente_fine_amount desc");
			preparestatement.setInt(1, userId);
			ResultSet resultSet = null;

			resultSet = preparestatement.executeQuery();

			if(resultSet.next())
			{
				int fineAmount = resultSet.getInt("incidente_fine_amount")+100;
				return fineAmount;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public void updateFineAmount(int incidentId, int fineAmount, int smsStatus, int policeStationId) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();



		try {
			preparestatement=connection.prepareStatement("update incidente_table set incidente_fine_amount=?,sms_sent_status=?, incident_police_station_id=? where incidente_id=?");

			System.out.println("after update ");

			preparestatement.setInt(1, fineAmount);
			preparestatement.setInt(2, smsStatus);
			preparestatement.setInt(3, policeStationId);
			preparestatement.setInt(4, incidentId);

			preparestatement.executeUpdate();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}



	public int UpdateIncidentPayment(String id) {

		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();

		try {
			preparestatement=connection.prepareStatement("update incidente_table set incidente_status=1 where incidente_id=?");

			System.out.println("after update ");
			preparestatement.setString(1, id);
			preparestatement.executeUpdate();
			
			return 1;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public String FetchUserByIncidentId(String id) {
		
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();

		try {
			preparestatement=connection.prepareStatement("Select user_mobileno from vehicle_towing_db.user_table where user_id in(select incidente_user_id from vehicle_towing_db.incidente_table where incidente_id=?)");

			preparestatement.setString(1, id);
			ResultSet resultSet=preparestatement.executeQuery();
			
			if(resultSet.next())
			{
				String mobileNo=resultSet.getString("user_mobileno");
				
				return mobileNo;
			}
			
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return null;
	}

	public HashMap<PoliceStationBean, Integer> fetchIncidentCountPerPoliceStation() {

		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		HashMap<PoliceStationBean, Integer> hashMap = new HashMap<PoliceStationBean, Integer>();
		
		try {
			preparestatement=connection.prepareStatement("select count(*) as count, incidente_table.incident_police_station_id as incident_police_station_id  from incidente_table where incidente_table.incident_police_station_id>0 group by incidente_table.incident_police_station_id");
			ResultSet resultSet=preparestatement.executeQuery();
			
			while(resultSet.next())
			{
				int count = resultSet.getInt("count");
				int policeStationId = resultSet.getInt("incident_police_station_id");
				System.out.println("");
				
				PoliceStationDBWrapper policeStationDBWrapper = new PoliceStationDBWrapper();
				hashMap.put(policeStationDBWrapper.FetchPoliceStationById(policeStationId), count);				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hashMap;
	}
	
	
	//fetch data for chart 
	
	
	
	
	
	
	
	
	
	
	
	
}
