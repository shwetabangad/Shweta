<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.java.towing.bean.*"%>
<%@page import="com.java.towing.db.TowingAgentDBWrapper"%>
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
<title>Towing Agent view</title>
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
			<h2>Towing Agent View Table</h2>
			<%
				try {
							TowingAgentDBWrapper dbWrapper =new  TowingAgentDBWrapper();
							ArrayList<TowingAgentBean> towingAgentBeanList = dbWrapper.fetchAllTowingAgentInfo();
			%>
			<table border="0" cellspacing="5" cellpadding="3">
				<thead>
					<tr>
						<th>ID</th>
						<th>FIRST NAME</th>
						<th>LAST NAME</th>
						<th>GENDER</th>
						<th>ADDRESS</th>
						<th>EMAIL ID</th>
						<th>MOBILE NO</th>
						<!-- <th>LATITUDE</th>
						<th>LONGITUDE</th> -->
						<!-- <th>UPDATE</th> -->
						<th>DELETE</th>
					</tr>
				</thead>
				<%
					for (int i = 0; i < towingAgentBeanList.size(); i++) {

							int id = towingAgentBeanList.get(i).getTowing_agent_id();
							String firstname = towingAgentBeanList.get(i).getTowing_agent_firstname();
							String lastname = towingAgentBeanList.get(i).getTowing_agente_lastname();
							String gender = towingAgentBeanList.get(i).getTowing_agent_gender();
							String address = towingAgentBeanList.get(i).getTowing_agent_address();
							String emailid = towingAgentBeanList.get(i).getTowing_agent_emailid();
							String mobileno = towingAgentBeanList.get(i).getTowing_agent_mobileno();
							/* Double lat = trafficePoliceBeanList.get(i).getTraffic_police_register_lat();
							Double longi = trafficePoliceBeanList.get(i).getTraffic_police_register_long(); */
				%>
				<tbody>
					<tr>
						<td><%=id%></td>
						<td><%=firstname%></td>
						<td><%=lastname%></td>
						<td><%=gender%></td>
						<td><%=address%></td>
						<td><%=emailid%></td>
						<td><%=mobileno%></td>
						<%-- <td><%=lat%></td>
						<td><%=longi%></td> --%>
						<%-- <td><a href="towing_agent_update.jsp?id=<%=id%>">Update</a></td> --%>
						<td><a href="towing_agent_delete.jsp?id=<%=id%>"
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