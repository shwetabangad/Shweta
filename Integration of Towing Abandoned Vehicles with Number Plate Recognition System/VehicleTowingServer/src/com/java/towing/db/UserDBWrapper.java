package com.java.towing.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.towing.bean.UserBean;



public class UserDBWrapper {
	
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
			public int insertUser(UserBean userBean) {
				DBConnection dBConnection = new DBConnection();
				connection = dBConnection.connect();
				String query = "insert into user_table(user_firstname, user_lastname, user_age, user_gender, user_address, user_emailid, user_mobileno) values('"
						+ userBean.getUser_firstname()
						+ "','"
						+ userBean.getUser_lastname()
						+ "','"
						+ userBean.getUser_age()
						+ "','"
						+ userBean.getUser_gender()
						+ "','"
						+ userBean.getUser_address()
						+ "','"
						+ userBean.getUser_emailid()
						+ "','"
						+ userBean.getUser_mobileno()
						+ "')";
				try {
					Statement statement = connection.createStatement();
					return statement.executeUpdate(query);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
			
			//VIEW 
			
			@SuppressWarnings("unused")
			private ResultSet fetchAllUser() {
				DBConnection dBConnection = new DBConnection();
				connection = dBConnection.connect();
				String query = "select * from user_table";
				ResultSet resultSet = null;
				try {
					Statement statement = connection.createStatement();
					resultSet = statement.executeQuery(query);
				} catch (Exception e) {
					System.out.println(e);
				}
				return resultSet;
			}
			
			private ResultSet fetchAllUserForLicence() {
				DBConnection dBConnection = new DBConnection();
				connection = dBConnection.connect();
				String query = "select * from user_table;";
				ResultSet resultSet = null;
				try {
					Statement statement = connection.createStatement();
					resultSet = statement.executeQuery(query);
				} catch (Exception e) {
					System.out.println(e);
				}
				return resultSet;
			}

			
			public ArrayList<UserBean> fetchAllUserInfo() {
				ResultSet resultSet = fetchAllUserForLicence();
				ArrayList<UserBean> UserbeanList = new ArrayList<UserBean>();
				if (resultSet != null) {
					try {
						resultSet.beforeFirst();
						while (resultSet.next()) {
							UserbeanList.add(fetchUserFromResultSet(resultSet));
						}
						resultSet.close();
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				return UserbeanList;
			}

			private UserBean fetchUserFromResultSet(ResultSet resultSet) {
				UserBean userBean = new UserBean();
				try {
					userBean.setUser_id(resultSet.getInt("user_id"));
					userBean.setUser_firstname(resultSet.getString("user_firstname"));
					userBean.setUser_lastname(resultSet.getString("user_lastname"));
					userBean.setUser_age(resultSet.getInt("user_age"));
					userBean.setUser_gender(resultSet.getString("user_gender"));
					userBean.setUser_address(resultSet.getString("user_address"));
					userBean.setUser_emailid(resultSet.getString("user_emailid"));
					userBean.setUser_mobileno(resultSet.getString("user_mobileno"));
					/*userBean.setUser_username(resultSet.getString("user_username"));
					userBean.setUser_password(resultSet.getString("user_password"));*/
				} catch (Exception e) {
					System.out.println(e);
				}
				return userBean;
			}
	//FIND USER BY ID
			
			public void FindUserById(UserBean userBean) {
				DBConnection dBConnection = new DBConnection();
				connection = dBConnection.connect();
				try {
					preparestatement = connection
							.prepareStatement("Select * from user_table where user_id=?;");
					preparestatement.setInt(1, userBean.getUser_id());
					ResultSet resultSet = preparestatement.executeQuery();
					resultSet.beforeFirst();
					if (resultSet.next()) {
						userBean.setUser_id(resultSet.getInt("user_id"));
						userBean.setUser_firstname(resultSet.getString("user_firstname"));
						userBean.setUser_lastname(resultSet.getString("user_lastname"));
						userBean.setUser_age(resultSet.getInt("user_age"));
						userBean.setUser_gender(resultSet.getString("user_gender"));
						userBean.setUser_address(resultSet.getString("user_address"));
						userBean.setUser_emailid(resultSet.getString("user_emailid"));
						userBean.setUser_mobileno(resultSet.getString("user_mobileno"));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	//UPDATE 
			
			public int updateUser(UserBean userBean) {
				DBConnection dBConnection = new DBConnection();
				connection = dBConnection.connect();
				String query = "update user_table set user_firstname = '" + userBean.getUser_firstname()  + "' ," + 
													 "user_lastname = '"  + userBean.getUser_lastname()   + "' ," +
													 "user_age ='" 		  + userBean.getUser_age()		  + "' ," +
													 "user_gender = '" 	  + userBean.getUser_gender()     + "' ," +
													 "user_address ='"	  + userBean.getUser_address()	  + "' ," +
													 "user_emailid = '"   + userBean.getUser_emailid()    + "' ," +
													 "user_mobileno ='"   + userBean.getUser_mobileno()	  + "' ," +
													/* "user_username = '"  + userBean.getUser_username()   + "' ," +
													 "user_password ='"   + userBean.getUser_password()	  + "'  " +*/
													 "where user_id= '"	  + userBean.getUser_id() 		  +"' ";
				try {
					Statement statement = connection.createStatement();
					return statement.executeUpdate(query);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}
	//DELETE 		

			public int deleteUser(String User_Id) {
				DBConnection dBConnection = new DBConnection();
				connection = dBConnection.connect();
				String query = "DELETE from user_table where user_id = '" + User_Id + "'";
				try {
					Statement statement = connection.createStatement();
					return statement.executeUpdate(query);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}

}
