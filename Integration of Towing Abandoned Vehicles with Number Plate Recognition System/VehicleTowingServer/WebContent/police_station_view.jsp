<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.java.towing.bean.*"%>
<%@page import="com.java.towing.db.PoliceStationDBWrapper"%>
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
<title>police station view</title>
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
			<h2>Police Station View Table</h2>
			<%
				try {
					PoliceStationDBWrapper policeStationDBWrapper=new PoliceStationDBWrapper();
					ArrayList<PoliceStationBean> policeStationBeanList = policeStationDBWrapper.fetchAllPoliceStationInfo();
			%>
			<table border="0" cellspacing="5" cellpadding="3">
				<thead>
					<tr>
						<th>ID</th>
						<th>NAME</th>
						<th>ADDRESS</th>
						
						<th>DELETE</th>
					</tr>
				</thead>
				<%
					for (int i = 0; i < policeStationBeanList.size(); i++) {

							int id = policeStationBeanList.get(i).getPoliceStationId();
							String name = policeStationBeanList.get(i).getPoliceStationName();
							String address = policeStationBeanList.get(i).getPoliceStationAddress();
							
				%>
				<tbody>
					<tr>
						<td><%=id%></td>
						<td><%=name%></td>
						<td><%=address%></td>
												<%-- <td><a href="police_station_update.jsp?id=<%=id%>">Update</a></td> --%>
						<td><a href="police_station_delete.jsp?id=<%=id%>"
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