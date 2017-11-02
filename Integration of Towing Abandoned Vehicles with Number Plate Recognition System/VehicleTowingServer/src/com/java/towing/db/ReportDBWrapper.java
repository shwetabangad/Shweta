package com.java.towing.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.java.towing.bean.ReportBean;

public class ReportDBWrapper {

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






//fetch daily report
	public ResultSet fetchAllReportDaily() {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "Select count(*) as count,incidente_date as date,sum(incidente_fine_amount) as fine from incidente_table where incidente_date in" +
				"(select DISTINCT incidente_table.incidente_date from incidente_table )" +
				"group by incidente_date";
		ResultSet resultSet = null;
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultSet;
	}

	public ArrayList<ReportBean> fetchAllReportoInfDaily() {
		ResultSet resultSet = fetchAllReportDaily();
		ArrayList<ReportBean> reportBeanList = new ArrayList<ReportBean>();
		if (resultSet != null) {
			try {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					reportBeanList.add(fetchReportDataFromResultSet(resultSet));
				}
				resultSet.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return reportBeanList;
	}

	private ReportBean fetchReportDataFromResultSet(
			ResultSet resultSet) {
		ReportBean reportBean = new ReportBean();
		try {
			reportBean.setCount(resultSet.getInt("count"));
			reportBean.setDate(resultSet.getString("date"));
			reportBean.setFineAmount(resultSet.getString("fine"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return reportBean;
	}

	//fetch monthly report
	public ResultSet fetchAllReportMonthly() {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "Select DISTINCT Monthname(incidente_date) as month,count(*) as count, sum(incidente_fine_amount) as fine from incidente_table where Monthname(incidente_date) in" +
				"(select DISTINCT Monthname(incidente_date) from incidente_table ) " +
				"group by Monthname(incidente_date);";
		ResultSet resultSet = null;
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultSet;
	}

	public ArrayList<ReportBean> fetchAllReportoInfoMonthly() {
		ResultSet resultSet = fetchAllReportMonthly();
		ArrayList<ReportBean> reportBeanList = new ArrayList<ReportBean>();
		if (resultSet != null) {
			try {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					reportBeanList.add(fetchReportDataMonthlyFromResultSet(resultSet));
				}
				resultSet.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return reportBeanList;
	}

	private ReportBean fetchReportDataMonthlyFromResultSet(
			ResultSet resultSet) {
		ReportBean reportBean = new ReportBean();
		try {
			reportBean.setCount(resultSet.getInt("count"));
			reportBean.setDate(resultSet.getString("month"));
			reportBean.setFineAmount(resultSet.getString("fine"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return reportBean;
	}

	//fetch yearly report
	public ResultSet fetchAllReportYearly() {
		DBConnection dBConnection = new DBConnection();
		connection = dBConnection.connect();
		String query = "Select count(*) as count,Year(incidente_date) as year,sum(incidente_fine_amount) as fine from incidente_table where incidente_date in" +
				"(select DISTINCT Year(incidente_date) from incidente_table )" +
				"group by Year(incidente_date)";
		ResultSet resultSet = null;
		try {
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		}
		return resultSet;
	}

	public ArrayList<ReportBean> fetchAllReportinfoYearly() {
		ResultSet resultSet = fetchAllReportYearly();
		ArrayList<ReportBean> reportBeanList = new ArrayList<ReportBean>();
		if (resultSet != null) {
			try {
				resultSet.beforeFirst();
				while (resultSet.next()) {
					reportBeanList.add(fetchReportDataYearFromResultSet(resultSet));
				}
				resultSet.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return reportBeanList;
	}


	private ReportBean fetchReportDataYearFromResultSet(
			ResultSet resultSet) {
		ReportBean reportBean = new ReportBean();
		try {
			reportBean.setCount(resultSet.getInt("count"));
			reportBean.setDate(resultSet.getString("year"));
			reportBean.setFineAmount(resultSet.getString("fine"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return reportBean;
	}

}
