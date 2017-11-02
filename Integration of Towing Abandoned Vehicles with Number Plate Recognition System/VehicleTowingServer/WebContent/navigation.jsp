<ul id="menu">
		<li>
			<a class="current" href="index.jsp">Home</a>
		</li>
		
		<li>
			<a href="aboutus.jsp">About Us</a>
		</li>
		
		<li>
			<a href="contact.jsp">Contact Us</a>
		</li>
		
		<%
		try{
	
		String user = (String) session.getAttribute("role");
		if(user != null)
		{%>
	    <li>
	    	<a href="logout.jsp">Logout</a>
	    </li>
	    
	    <%	}
		else
	   	{%>
	    <li>
	    	<a href="recent_incident_view.jsp">Recent Incidents</a>
	    </li>
	    <li>
	    	<a href="login.jsp">Login</a>
	    </li>
	    <%}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		%>
</ul>