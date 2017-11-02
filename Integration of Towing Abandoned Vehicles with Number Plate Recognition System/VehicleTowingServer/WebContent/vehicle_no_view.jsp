<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.java.towing.bean.*"%>
<%@page import="com.java.towing.db.VehicleNoDBWrapper"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.java.towing.db.UserDBWrapper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="css/main.css"
	media="screen" />
<title>Vehicle view</title>
</head>
<body>
	<div id="content">

		<!-- Header-->
		<%@ include file="header.jsp"%>
		<!-- End Header-->

		<!-- Navigation -->
		<%@ include file="navigation.jsp"%>
		<!-- End Navigation -->

		<div style="clear: both;"></div>

		<!-- Menu -->
		<%@ include file="menu.jsp"%>
		<!-- End Menu -->

		<!-- Container -->
		<div class="third last">
			<h2>Vehicle No View Table</h2>
			<%
				try {
							VehicleNoDBWrapper dbWrapper = new VehicleNoDBWrapper();
							ArrayList<VehicleNoBean> vehicleBeanList = dbWrapper.fetchAllVehicleNoInfo();
			%>
			<table border="0" cellspacing="5" cellpadding="3">
				<thead>
					<tr>
						<th>ID</th>
						<th>VEHICLE NUMBER</th>
						<th>USER NAME</th>
						
						<th>UPDATE</th>
						<th>DELETE</th>
					</tr>
				</thead>
				<%
					for (int i = 0; i < vehicleBeanList.size(); i++) {

							int id = vehicleBeanList.get(i).getVehicle_no_id();
							String number = vehicleBeanList.get(i).getVehicle_number();
							int userid = vehicleBeanList.get(i).getVehicle_user_id();
							String type = vehicleBeanList.get(i).getVehicle_type();
							
				%>
				<tbody>
					<tr>
						<td><%=id%></td>
						<td><%=number%></td>
						<td><%=userid%></td>
						<td><%=type%></td>
						<%-- <td><a href="driving_license_update.jsp?id=<%=id%>">Update</a></td> --%>
						<td><a href="vehicle_no_delete.jsp?id=<%=id%>"
							onclick="return confirm('Are you sure you want to delete ID:   <%=id%>')">Delete</a></td>
					</tr>
					<%
					}/* for block loop end */
						} /* try block end */
						catch (Exception ex) {
							out.println("Unable to connect to database.");
						}
					%>
				</tbody>
			</table>

		</div>
		<!-- End Container -->

		<div style="clear: both;"></div>
	</div>

	<!-- Footer -->
	<%@ include file="footer.jsp"%>
	<!-- End Footer -->

	<div align=center></div>
</body>
</html>