<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.io.File"%>
<%@page import="com.java.towing.utilty.NumberRecognation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" />
<title>traffic police</title>
</head>
<body>
<div id="content">

<% 

	//NumberRecognation.extract(new File("kjkj"));
%>
<!-- Header-->
 <%@ include file ="header.jsp" %>
<!-- End Header--> 

<!-- Navigation --> 
  <%@ include file ="navigation.jsp" %>
<!-- End Navigation -->
 
<div style="clear: both;"></div>

<!-- Menu -->  
<%@ include file ="menu.jsp" %> 
<!-- End Menu --> 
<table>
			<tr>
    <td><img src="BarChartServletMonthlyFineAmount" alt="msg"> </img></td>
    </tr>
    <tr>
     <td><img src="BarChartServletMonthlyCount" alt="msg"> </img></td>
     </tr>
    </table>
<!-- End Container -->
 
<div style="clear: both;"></div>
</div>

<!-- Footer -->
<%@ include file ="footer.jsp" %> 
<!-- End Footer -->

<div align=center></div>
</body>
</html>