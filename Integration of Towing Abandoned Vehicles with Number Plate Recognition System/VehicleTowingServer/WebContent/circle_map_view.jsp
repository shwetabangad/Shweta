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
 
<!-- Container -->
  <div class="third last">
    <h2>Home</h2>
    <p>&nbsp;</p>
  </div>
  
  
  
  <script>
      // This example creates circles on the map, representing populations in North
      // America.

      // First, create an object containing LatLng and population for each city.
      var citymap = {
        chicago: {
          center: {lat: 41.878, lng: -87.629},
          population: 2714856
        },
        newyork: {
          center: {lat: 40.714, lng: -74.005},
          population: 8405837
        },
        losangeles: {
          center: {lat: 34.052, lng: -118.243},
          population: 3857799
        },
        vancouver: {
          center: {lat: 49.25, lng: -123.1},
          population: 603502
        }
      };

      function initMap() {
        // Create the map.
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 4,
          center: {lat: 37.090, lng: -95.712},
          mapTypeId: google.maps.MapTypeId.TERRAIN
        });

        // Construct the circle for each value in citymap.
        // Note: We scale the area of the circle based on the population.
        for (var city in citymap) {
          // Add the circle for this city to the map.
          var cityCircle = new google.maps.Circle({
            strokeColor: '#FF0000',
            strokeOpacity: 0.8,
            strokeWeight: 2,
            fillColor: '#FF0000',
            fillOpacity: 0.35,
            map: map,
            center: citymap[city].center,
            radius: Math.sqrt(citymap[city].population) * 100
          });
        }
      }
    </script>
    
    <div id="map"></div>
    <img src="PieChartServlet" alt="msg"> </img>
    
    <script 
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWHyTWOxhkIe-CkkpFIfR0HCB0JTagVEQ&callback=initMap">
    </script>
<!-- End Container -->
 
<div style="clear: both;"></div>
</div>

<!-- Footer -->
<%@ include file ="footer.jsp" %> 
<!-- End Footer -->

<div align=center></div>
</body>
</html>