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
<link rel="stylesheet" type="text/css" href="css/main.css"
	media="screen" />
<title>user view</title>
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
			<h2>User View Table</h2>
			<%
				try {
					UserDBWrapper dbWrapper = new UserDBWrapper();
					ArrayList<UserBean> userBeanList = dbWrapper.fetchAllUserInfo();
			%>
			<table border="0" cellspacing="5" cellpadding="3">
				<thead>
					<tr>
						<th>ID</th>
						<th>FIRST NAME</th>
						<th>LAST NAME</th>
						<th>AGE</th>
						<th>GENDER</th>
						<th>ADDRESS</th>
						<th>EMAIL ID</th>
						<th>MOBILE NO</th>
					
						<th>DELETE</th>
					</tr>
				</thead>
				<%
					for (int i = 0; i < userBeanList.size(); i++) {

							int id = userBeanList.get(i).getUser_id();
							
							String firstname = userBeanList.get(i).getUser_firstname();
							String lastname = userBeanList.get(i).getUser_lastname();
							int age = userBeanList.get(i).getUser_age();
							String gender = userBeanList.get(i).getUser_gender();
							String address = userBeanList.get(i).getUser_address();
							String emailid = userBeanList.get(i).getUser_emailid();
							String mobileno = userBeanList.get(i).getUser_mobileno();
				%>
				<tbody>
					<tr>
						<td><%=id%></td>
						<td><%=firstname%></td>
						<td><%=lastname%></td>
						<td><%=age%></td>
						<td><%=gender%></td>
						<td><%=address%></td>
						<td><%=emailid%></td>
						<td><%=mobileno%></td>
						<td><a href="user_delete.jsp?id=<%=id%>"
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