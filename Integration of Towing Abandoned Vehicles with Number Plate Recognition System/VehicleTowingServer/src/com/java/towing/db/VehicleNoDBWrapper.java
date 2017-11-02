package com.java.towing.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.towing.bean.VehicleNoBean;

public class VehicleNoDBWrapper {

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

	// INSERT data into database
	public int insertVehicleNo(VehicleNoBean vehicleBean) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "insert into vehicle_no_table(vehicle_number, vehicle_user_id, vehicle_type) values('"
				+ vehicleBean.getVehicle_number()
				+ "','"
				+ vehicleBean.getVehicle_user_id()
				+ "','"
				+ vehicleBean.getVehicle_type()
				+ "')";
		try {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}



	public int fetchUseridByVehicleNo(String vehicleNo) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		try {
			preparestatement = connection.prepareStatement("select vehicle_user_id from vehicle_no_table where vehicle_number like ?");

			vehicleNo = vehicleNo.substring(2, 6);
			vehicleNo = "%" + vehicleNo + "%";
			System.out.println("vehicleNo:" + vehicleNo);
			preparestatement.setString(1, vehicleNo);

			ResultSet resultSet = null;

			resultSet = preparestatement.executeQuery();

			if(resultSet.next())
			{
				int id=resultSet.getInt("vehicle_user_id");
				return id;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	//VIEW 

	private ResultSet fetchAllVehicle() {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "select * from vehicle_no_table";
		ResultSet resultSet = null;
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultSet;
	}

	public ArrayList<VehicleNoBean> fetchAllVehicleNoInfo() {
		ResultSet resultSet = fetchAllVehicle();
		ArrayList<VehicleNoBean> vehiclebeanList = new ArrayList<VehicleNoBean>();
		if (resultSet != null) {
			try {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					vehiclebeanList.add(fetchVehicleFromResultSet(resultSet));
				}
				resultSet.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return vehiclebeanList;
	}

	private VehicleNoBean fetchVehicleFromResultSet(ResultSet resultSet) {
		VehicleNoBean vehicleBean = new VehicleNoBean();

		try {
			vehicleBean.setVehicle_no_id(resultSet.getInt("vehicle_id"));
			vehicleBean.setVehicle_number(resultSet.getString("vehicle_number"));
			vehicleBean.setVehicle_user_id(resultSet.getInt("vehicle_user_id"));
			vehicleBean.setVehicle_type(resultSet.getString("vehicle_type"));

		} catch (Exception e) {
			System.out.println(e);
		}
		return vehicleBean;
	}
	//FIND USER BY ID

	/*public VehicleNoBean FindDrivingLicenseById(VehicleNoBean drivingLicenseBean) {
				DBConnection dBConnection = new DBConnection();
				connection = dBConnection.connect();
				try {
					preparestatement = connection
							.prepareStatement("Select *from driving_license_table where driving_license_id=?;");
					preparestatement.setInt(1, drivingLicenseBean.getDriving_license_id());
					ResultSet resultSet = preparestatement.executeQuery();
					resultSet.beforeFirst();
					if (resultSet.next()) {
						drivingLicenseBean.setDriving_license_id(resultSet.getInt("driving_license_id"));
						drivingLicenseBean.setDriving_license_number(resultSet.getString("driving_license_number"));
						drivingLicenseBean.setDriving_license_user_id(resultSet.getInt("driving_license_user_id"));
						drivingLicenseBean.setDriving_license_type(resultSet.getString("driving_license_type"));
						return drivingLicenseBean;
					}
					return null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
	//UPDATE 

			public int updateDrivingLicense(VehicleNoBean drivingLicenseBean) {
				DBConnection dBConnection = new DBConnection();
				connection = dBConnection.connect();
				String query = "update driving_license_table set driving_license_number = '" + drivingLicenseBean.getDriving_license_number()  	+ "' ," + 
													 			"driving_license_user_id = '"+ drivingLicenseBean.getDriving_license_user_id()  + "' ," +
													 			"driving_license_type ='" 	 + drivingLicenseBean.getDriving_license_type()		+ "' " +
													 			"where driving_license_id= '"+ drivingLicenseBean.getDriving_license_id() 		+"' ";
				try {
					Statement statement = connection.createStatement();
					return statement.executeUpdate(query);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return 0;
			}*/
	//DELETE 		

	public int deleteVehicle(String vehicle_Id) {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "DELETE from vehicle_no_table where vehicle_id = '" + vehicle_Id + "'";
		try {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
