package com.java.towing.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.towing.bean.TowingAgentBean;

public class TowingAgentDBWrapper {

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
	public int insertTowingAgent(TowingAgentBean towingAgentBean) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "insert into towing_agent_table(towing_agent_firstname, towing_agent_lastname, towing_agent_gender, towing_agent_address, towing_agent_mobileno, towing_agent_emailid, towing_agent_username, towing_agent_password, towing_agent_register_lat, towing_agent_register_long) values('"
				+ towingAgentBean.getTowing_agent_firstname()
				+ "','"
				+ towingAgentBean.getTowing_agente_lastname()
				+ "','"
				+ towingAgentBean.getTowing_agent_gender()
				+ "','"
				+ towingAgentBean.getTowing_agent_address()
				+ "','"
				+ towingAgentBean.getTowing_agent_mobileno()
				+ "','"
				+ towingAgentBean.getTowing_agent_emailid()
				+ "','"
				+ towingAgentBean.getTowing_agente_username()
				+ "','"
				+ towingAgentBean.getTowing_agent_password()
				+ "','"
				+ towingAgentBean.getTowing_agent_register_lat()
				+ "','"
				+ towingAgentBean.getTowing_agent_register_long() + "')";
		try {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// VIEW

	public ResultSet fetchAllTrafficPolice() {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "select * from towing_agent_table";
		ResultSet resultSet = null;
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultSet;
	}

	public ArrayList<TowingAgentBean> fetchAllTowingAgentInfo() {
		ResultSet resultSet = fetchAllTrafficPolice();
		ArrayList<TowingAgentBean> towingAgentbeanList = new ArrayList<TowingAgentBean>();
		if (resultSet != null) {
			try {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					towingAgentbeanList
							.add(fetchTowingAgentFromResultSet(resultSet));
				}
				resultSet.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return towingAgentbeanList;
	}

	private TowingAgentBean fetchTowingAgentFromResultSet(
			ResultSet resultSet) {
		TowingAgentBean towingAgentBean = new TowingAgentBean();
		try {
			towingAgentBean.setTowing_agent_id(resultSet
					.getInt("towing_agent_id"));
			towingAgentBean.setTowing_agent_firstname(resultSet
					.getString("towing_agent_firstname"));
			towingAgentBean.setTowing_agente_lastname(resultSet
					.getString("towing_agent_lastname"));
			towingAgentBean.setTowing_agent_gender(resultSet
					.getString("towing_agent_gender"));
			towingAgentBean.setTowing_agent_address(resultSet
					.getString("towing_agent_address"));
			towingAgentBean.setTowing_agent_mobileno(resultSet
					.getString("towing_agent_mobileno"));
			towingAgentBean.setTowing_agent_emailid(resultSet
					.getString("towing_agent_emailid"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return towingAgentBean;
	}

	// FIND USER BY ID

	public TowingAgentBean FindTowingAgentById(
			TowingAgentBean towingAgentBean) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		try {
			preparestatement = connection
					.prepareStatement("Select *from towing_agent_table where towing_agent_id=?;");
			preparestatement
					.setInt(1, towingAgentBean.getTowing_agent_id());
			ResultSet resultSet = preparestatement.executeQuery();
			resultSet.beforeFirst();
			if (resultSet.next()) {
				towingAgentBean.setTowing_agent_id(resultSet
						.getInt("towing_agent_id"));
				towingAgentBean.setTowing_agent_firstname(resultSet
						.getString("towing_agent_firstname"));
				towingAgentBean.setTowing_agente_lastname(resultSet
						.getString("towing_agent_lastname"));
				towingAgentBean.setTowing_agent_gender(resultSet
						.getString("towing_agent_gender"));
				towingAgentBean.setTowing_agent_address(resultSet
						.getString("towing_agent_address"));
				towingAgentBean.setTowing_agent_mobileno(resultSet
						.getString("towing_agent_mobileno"));
				towingAgentBean.setTowing_agent_emailid(resultSet
						.getString("towing_agent_emailid"));
				towingAgentBean.setTowing_agente_username(resultSet
						.getString("towing_agent_username"));
				towingAgentBean.setTowing_agent_password(resultSet
						.getString("towing-agent_password"));
				towingAgentBean.setTowing_agent_register_lat(resultSet
						.getDouble("towing-agent_register_lat"));
				towingAgentBean.setTowing_agent_register_long(resultSet
						.getDouble("towing-agent_register_long"));
				return towingAgentBean;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// UPDATE

	public int updateTowingAgent(TowingAgentBean towingAgentBean) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "update towing_agent_table set towing_agent_firstname = '"
				+ towingAgentBean.getTowing_agent_firstname()
				+ "','"
				+ towingAgentBean.getTowing_agente_lastname()
				+ "','"
				+ towingAgentBean.getTowing_agent_gender()
				+ "','"
				+ towingAgentBean.getTowing_agent_address()
				+ "','"
				+ towingAgentBean.getTowing_agent_mobileno()
				+ "','"
				+ towingAgentBean.getTowing_agent_emailid()
				+ "','"
				+ towingAgentBean.getTowing_agente_username()
				+ "','"
				+ towingAgentBean.getTowing_agent_password()
				+ "','"
				+ towingAgentBean.getTowing_agent_register_lat()
				+ "','"
				+ towingAgentBean.getTowing_agent_register_long() 
				
				+ "where traffic_police_id= '"
				+ towingAgentBean.getTowing_agent_id() + "' ";
		try {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// DELETE

	public int deleteTowingAgent(String TowingAgent_Id) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "DELETE from towing_agent_table where towing_agent_id = '"
				+ TowingAgent_Id + "'";
		try {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public TowingAgentBean updateTowingAgentStatus(
			TowingAgentBean towingAgentBean) {
		try {
			System.out.println("TrafficPolice");
			DBConnection dBConnection = new DBConnection();
			connection = dBConnection.connect();
			String query = "update towing_agent_table set towing_agent_current_lat='"
					+ towingAgentBean.getTowing_agent_current_lat()
					+ "',towing_agent_current_long ='"
					+ towingAgentBean.getTowing_agent_current_long()
					+ "' where towing_agent_id= '"
					+ towingAgentBean.getTowing_agent_id() + "'";
			Statement statement = connection.createStatement();
			statement.executeUpdate(query);
			String squery = "Select towing_agent_firstname,towing_agent_lastname from towing_agent_table where towing_agent_id ="
					+ towingAgentBean.getTowing_agent_id();
			ResultSet resultSet = statement.executeQuery(squery);
			resultSet.beforeFirst();
			while (resultSet.next()) {
				towingAgentBean.setTowing_agent_firstname(resultSet
						.getString("towing_agent_firstname"));
				towingAgentBean.setTowing_agente_lastname(resultSet
						.getString("towing_agent_lastname"));
				return towingAgentBean;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
