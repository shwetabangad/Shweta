<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="com.java.towing.db.VehicleNoDBWrapper"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<%
	String vehicle_Id = request.getParameter("id");
		try {
	VehicleNoDBWrapper dbWrapper = new VehicleNoDBWrapper();
	dbWrapper.deleteVehicle(vehicle_Id);

	if (vehicle_Id != null) {
		response.sendRedirect("vehicle_no_view.jsp");

	} else {
		response.sendRedirect("error.jsp");
	}
		}

		catch (Exception e)

		{
	e.printStackTrace();
		}
%>
</body>
</html>