<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.java.towing.bean.*"%>
<%@page import="com.java.towing.db.IncidenteDBWrapper"%>
<%@page import="com.java.towing.db.PoliceStationDBWrapper"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="css/main.css"
	media="screen" />
<title>incident view</title>
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
			<h2>Recent Incident View Table</h2>
			<%
				try {
					IncidenteDBWrapper incidenteDBWrapper=new IncidenteDBWrapper();
					ArrayList<IncidenteBean> incidentBeanList = incidenteDBWrapper.fetchIncidentInfo();
			%>
			<table border="0" cellspacing="10" cellpadding="3">
				<thead>
					<tr>
						<th>DATE</th>
						<th>IMAGE</th>
						<th>NO PLATE TEXT</th>
						<th>FINE AMOUNT</th>
						<th>SMS SENT</th>
					</tr>
				</thead>
				<%
					for (int i = 0; i < incidentBeanList.size(); i++) {

							int id = incidentBeanList.get(i).getIncidenteId();
							String discription = incidentBeanList.get(i).getIncidenteDescription();
							int fine=incidentBeanList.get(i).getIncidenteFineAmount();
							String noPlate=incidentBeanList.get(i).getNoPlateText();
							String date = incidentBeanList.get(i).getIncidenteDate();
							Double lat=incidentBeanList.get(i).getIncidentLat();
							Double longi=incidentBeanList.get(i).getIncidentLong();
							String smsSent=incidentBeanList.get(i).getSmsStatus();
							int status = incidentBeanList.get(i).getIncidentStatus();
							
							if(status == 0)
							{
				%>
				<tbody>
					<tr>

						<td><%=date%></td>
						<td><img height="100" width="100"
							src="IncidentImageServlet?id=<%=id%>" /></td>
						<td><input type="text" name="noPlate" id="noPlate" readonly
							value="<%=noPlate%>" size="10" /></td>
						<td align="center"><%=fine%></td>

						<td align="center">
							<%if(smsSent.equals("1")){%> <a>YES</a> <% }else
								{%> <a>NO</a> <%}%>
						</td>

					</tr>
					<%
						}
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