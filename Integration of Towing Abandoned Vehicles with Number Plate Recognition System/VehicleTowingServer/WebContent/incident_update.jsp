<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.util.ArrayList"%>
<%@page import="com.java.towing.db.IncidenteDBWrapper"%>
<%@page import="com.java.towing.bean.IncidenteBean"%>
<%@page import="com.java.towing.db.TowingAgentDBWrapper"%>
<%@page import="com.java.towing.bean.TowingAgentBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" />
<script type="text/javascript" src="js/TrafficPoliceValidation.js"></script>
<title>traffic police update</title>

</head>
<body>
	<%
		int incidenteId = Integer.parseInt(request.getParameter("id"));
		
		IncidenteBean incidenteBean = new IncidenteBean();
		incidenteBean.setIncidenteId(incidenteId);
		

		IncidenteDBWrapper incidenteDBWrapper = new IncidenteDBWrapper();
		IncidenteBean bean= incidenteDBWrapper.FetchIncidentById(incidenteBean);
	%>
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
			<h2>Incident Update Form</h2>
			<table border="0">
				<tr>
					<td width="25%">
						<form name="form" action="Incidente_Update_Servlet" method="post"  onsubmit="return validateRegistration(form);" >
							<table border="0" cellspacing="15">
								<tbody>
									<tr>
										<td><b>ID:</b></td>
										<td><input type="text" name="id" id="id"
											readonly="readonly" value="<%=bean.getIncidenteId()%>" /></td>
									</tr>
									<tr>
										<td><b>DISCRIPTION:</b></td>
										<td><input type="text" name="discription" id="discription"
											value="<%=bean.getIncidenteDescription()%>" /></td>
									</tr>
									<tr>
										<td><b>NO PLATE TEXT:</b></td>
										<td><input type="text" name="noPlate" id="noPlate"
											value="<%=bean.getNoPlateText()%>" /></td>
									</tr>
									
									<tr>
										<td><b>FINE AMOUNT:</b></td>
										<td><textarea cols="22" rows="3" name="fine" id="fine" ><%=bean.getIncidenteFineAmount()%></textarea></td>
									</tr>
									
									
									
									
								</tbody>
								<tfoot align="center">
									<tr>
										<td></td>
										<td><input type="submit" value="Update" name="submit"
											id="submit" />&nbsp;&nbsp;&nbsp; <input type="button"
											value="Cancle" name="cancle" id="cancle"
											onclick="javascript:window.location='traffic_police_view.jsp';" /></td>
									</tr>
								</tfoot>
							</table>
						</form>
					</td >
						<td><img height="200" width="200" src="IncidentImageServlet?id=<%=incidenteId%>"/></td>				</tr>
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