package com.java.towing.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.towing.bean.TowingAgentBean;

public class LoginDBWrapper {
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

	// Traffic Police Login
	public TowingAgentBean validateTowingAgent(TowingAgentBean towingAgentBean) {
		System.out.println("validateTowingAgent");
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		try {
			preparestatement = connection
					.prepareStatement("Select *from towing_agent_table where towing_agent_username=? and towing_agent_password=?");

			preparestatement.setString(1, towingAgentBean.getTowing_agente_username());
			preparestatement.setString(2, towingAgentBean.getTowing_agent_password());

			ResultSet rs = preparestatement.executeQuery();
			rs.beforeFirst();
			if (rs.next()) {
				towingAgentBean.setTowing_agent_id(rs.getInt("towing_agent_id"));
				
				return towingAgentBean;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}