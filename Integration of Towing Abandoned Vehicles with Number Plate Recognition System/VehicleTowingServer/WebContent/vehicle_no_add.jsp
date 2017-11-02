<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.java.towing.bean.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.java.towing.db.UserDBWrapper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" />
<script type="text/javascript" src="js/DrivingLicenseValidation.js"></script>
<title>Vehicle No add</title>
</head>
<body>
<%
UserDBWrapper dbWrapper =new UserDBWrapper();
ArrayList<UserBean> userBeanList = dbWrapper.fetchAllUserInfo(); 
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
			<h2>Vehicle No Register Form</h2>
			<form name="form" action="Vehicle_No_Add_Servlet" method="post"  onsubmit="return validateRegistration(form);">

				<table border="0" cellspacing="15">
					<tbody>
						<tr>
							<td><b>Vehicle Number*:</b></td>
							<td><input type="text" name="vehicleNumber" id="vehicleNumber"/></td>
						</tr>
						<tr>
							<td><b>User Name*:</b></td>
<!-- 							<td><input type="text" name="userid" id="userid"/></td> -->
								<td><select name="userid" id="userid">
								<%
								for (int i = 0; i < userBeanList.size(); i++) 
								{%>
								<option value=<%=userBeanList.get(i).getUser_id()%>><%=userBeanList.get(i).getUser_firstname() %></option>
								<%} %>
								</select></td>
						</tr>
						 <tr>
							<td><b>Vehicle Type*:</b></td>
							<td><select name="vehicleType" id="vehicleType" size="1">
							<option value="Two Wheelere">Two Wheeler</option>
							<option value="Four Wheeler">Four Wheeler</option>
							
							</select></td>
						</tr> 
					</tbody>

					<tfoot align="center">
						<tr>
							<td></td>
							<td><input type="submit" value="Add" name="submit" />&nbsp;&nbsp;&nbsp;
								<input type="reset" value="Reset" name="Reset" /></td>
						</tr>
					</tfoot>
				</table>
			</form>
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