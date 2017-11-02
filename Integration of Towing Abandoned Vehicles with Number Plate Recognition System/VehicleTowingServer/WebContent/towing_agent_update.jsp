<?xml version="1.0" encoding="ISO-8859-1" ?>
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
<script src="http://maps.googleapis.com/maps/api/js"></script>
<script>
	function initialize() {
		var mapProp = {
			center : new google.maps.LatLng(18.48693534562608,
					73.81954193115234),
			zoom : 15,
			mapTypeId : google.maps.MapTypeId.ROADMAP
		/* ROADMAP (normal, default 2D map)
														   SATELLITE (photographic map)
														   HYBRID (photographic map + roads and city names)
														   TERRAIN (map with mountains, rivers, etc.) */
		};
		var map = new google.maps.Map(document.getElementById("googleMap"),
				mapProp);

		google.maps.event
				.addListener(
						map,
						'click',
						function(event) {
							// alert( "Latitude: "+event.latLng.lat()+" "+", longitude: "+event.latLng.lng() ); 
							document.getElementById("register_lat").value = event.latLng
									.lat();
							document.getElementById("register_long").value = event.latLng
									.lng();
						});

	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>
</head>
<body>
	<%
		int towing_agent_id = Integer.parseInt(request.getParameter("id"));
		TowingAgentBean towingAgentBean = new TowingAgentBean();
		towingAgentBean.setTowing_agent_id(towing_agent_id);

		TowingAgentDBWrapper dbWrapper = new TowingAgentDBWrapper();
		TowingAgentBean bean = dbWrapper.FindTowingAgentById(towingAgentBean);
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
			<h2>Towing Agent Update Form</h2>
			<table border="0">
				<tr>
					<td width="25%">
						<form name="form" action="Towing_Agent_Update_Servlet" method="post"  onsubmit="return validateRegistration(form);" >
							<table border="0" cellspacing="15">
								<tbody>
									<tr>
										<td><b>ID*:</b></td>
										<td><input type="text" name="id" id="id"
											readonly="readonly" value="<%=bean.getTowing_agent_id()%>" /></td>
									</tr>
									<tr>
										<td><b>FirstName*:</b></td>
										<td><input type="text" name="firstname" id="firstname"
											value="<%=bean.getTowing_agent_firstname()%>" /></td>
									</tr>
									<tr>
										<td><b>LastName*:</b></td>
										<td><input type="text" name="lastname" id="lastname"
											value="<%=bean.getTowing_agente_lastname()%>" /></td>
									</tr>
									<tr>
										<td><b>Gender:</b></td>
										<td><select name="gender" id="gender" size="1">
												<option value="<%=bean.getTowing_agent_gender()%>"><%=bean.getTowing_agent_gender().toUpperCase()%></option>
												<%
													if (bean.getTowing_agent_gender().equals("male")) {
												%>
												<option value="female">FEMALE</option>

												<%
													} else {
												%>
												<option value="male">MALE</option>
												<%
													}
												%>
										</select></td>
									</tr>
									<tr>
										<td><b>Address:</b></td>
										<td><textarea cols="22" rows="3" name="address" id="address" ><%=bean.getTowing_agent_address()%></textarea></td>
									</tr>
									<tr>
										<td><b>Email ID*:</b></td>
										<td><input type="text" name="emailid" id="emailid" value="<%=bean.getTowing_agent_emailid()%>"/></td>
									</tr>
									<tr>
										<td><b>Mobile NO.*:</b></td>
										<td><input type="text" name="mobileno" id="mobileno" value="<%=bean.getTowing_agent_mobileno()%>"/></td>
									</tr>
									<tr>
										<td><b>UserName*:</b></td>
										<td><input type="text" name="username" id="username" value="<%=bean.getTowing_agente_username()%>"/></td>
									</tr>
									<tr>
										<td><b>Password*:</b></td>
										<td><input type="password" name="password" id="password" value="<%=bean.getTowing_agent_password()%>"/></td>
									</tr>
									<tr>
										<td><b>Re Password*:</b></td>
										<td><input type="password" name="repassword"
											id="repassword" value="<%=bean.getTowing_agent_password()%>" /></td>
									</tr>
									<tr>
										<td><b>Register Latitude*:</b></td>
										<td><input type="text" name="register_lat"
											id="register_lat" readonly="readonly" value="<%=bean.getTowing_agent_register_lat()%>"/></td>
									</tr>
									<tr>
										<td><b>Register Longitude*:</b></td>
										<td><input type="text" name="register_long"
											id="register_long" readonly="readonly" value="<%=bean.getTowing_agent_register_long()%>"/></td>
									</tr>
									<%-- <tr>
										<td><b>Current Latitude*:</b></td>
										<td><input type="text" name="current_lat"
											id="current_lat" readonly="readonly" value="<%=bean.getTraffic_police_current_lat()%>"/></td>
									</tr>
									<tr>
										<td><b>Current Longitude*:</b></td>
										<td><input type="text" name="current_long"
											id="current_long" readonly="readonly" value="<%=bean.getTraffic_police_current_long()%>"/></td>
									</tr> --%>
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
					<td width="75%"><div id="googleMap" style="width: 100%; height: 490px;"></div></td>
				</tr>
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