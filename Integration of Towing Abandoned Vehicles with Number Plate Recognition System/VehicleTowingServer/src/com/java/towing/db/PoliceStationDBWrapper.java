package com.java.towing.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.towing.bean.IncidenteBean;
import com.java.towing.bean.PoliceStationBean;
import com.java.towing.bean.TowingAgentBean;

public class PoliceStationDBWrapper {

	Connection connection = null;
	PreparedStatement preparestatement = null;
	ResultSet rs = null;

	public void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// INSERT
	public int insertPoliceStation(PoliceStationBean policeStationBean) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "insert into police_station_table(police_station_id,police_station_name, police_station_address, police_station_lat, police_station_long) values('"
				+ policeStationBean.getPoliceStationId()
				+ "','"
				+ policeStationBean.getPoliceStationName()
				+ "','"
				+ policeStationBean.getPoliceStationAddress()
				+ "','"
				+ policeStationBean.getPoliceStationLat()
				+ "','"
				+ policeStationBean.getPoliceStationLong()
				+ "')";
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// VIEW

	public ResultSet fetchAllPoliceStation() {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "select * from police_station_table";
		ResultSet resultSet = null;
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultSet;
	}

	public ArrayList<PoliceStationBean> fetchAllPoliceStationInfo() {
		ResultSet resultSet = fetchAllPoliceStation();
		ArrayList<PoliceStationBean> policeStationbeanList = new ArrayList<PoliceStationBean>();
		if (resultSet != null) {
			try {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					policeStationbeanList.add(fetchPoliceStationFromResultSet(resultSet));
				}
				resultSet.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return policeStationbeanList;
	}

	private PoliceStationBean fetchPoliceStationFromResultSet(
			ResultSet resultSet) {
		PoliceStationBean policeStationBean = new PoliceStationBean();
		try {
			policeStationBean.setPoliceStationId(resultSet
					.getInt("police_station_id"));
			policeStationBean.setPoliceStationName(resultSet
					.getString("police_station_name"));
			policeStationBean.setPoliceStationAddress(resultSet
					.getString("police_station_address"));

			policeStationBean.setPoliceStationLat(resultSet
					.getDouble("police_station_lat"));
			policeStationBean.setPoliceStationLong(resultSet
					.getDouble("police_station_long"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return policeStationBean;
	}

	public PoliceStationBean FetchPoliceStationById(
			int policeStationId) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		try {
			preparestatement = connection.prepareStatement("Select * from police_station_table where police_station_id=?");
			preparestatement.setInt(1, policeStationId);
			ResultSet resultSet = preparestatement.executeQuery();
			resultSet.beforeFirst();
			if(resultSet.next()) {
				System.out.println("1");
				return fetchPoliceStationFromResultSet(resultSet);
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int deletePoliceStation(String policeStationId) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "DELETE from police_station_table where police_station_id = '"
				+ policeStationId + "'";
		try {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}


}
