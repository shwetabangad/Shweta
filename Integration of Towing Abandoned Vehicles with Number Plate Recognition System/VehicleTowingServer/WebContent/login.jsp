<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" />
<script type="text/javascript" src="js/LoginValidation.js"></script>
<title>login</title>
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
			<h2>Login Form</h2>
			<form name="form" action="Login_Servlet" method="post"  onsubmit="return validateLogin(form);">

				<table border="0" cellspacing="15">
					<tbody>
					
						<tr>
							<td><b>UserName:</b></td>
							<td><input type="text" name="username" id="username" /></td>
						</tr>

						<tr>
							<td><b>Password:</b></td>
							<td><input type="password" name="password" id="password" /></td>
						</tr>
					</tbody>

					<tfoot align="center">
						<tr>
							<td></td>
							<td><input type="submit" value="Login" name="submit" />&nbsp;&nbsp;&nbsp;
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