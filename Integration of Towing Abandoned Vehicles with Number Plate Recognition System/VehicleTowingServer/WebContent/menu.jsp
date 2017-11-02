<div class="third" id="third">
	<ul>
		<%
		try{
			String user_admin = (String) session.getAttribute("role");
			if (user_admin != null && user_admin.equals("ADMIN")) {
		%>
		<li>
			<a style="text-decoration: none;">POLICE STATION</a>
			<ul>
				<li>
					<a href="police_station_add.jsp">Add</a>&nbsp; |&nbsp;<a href="police_station_view.jsp">View</a>
				</li>
			</ul>
		</li>
		<li>
			<a style="text-decoration: none;">TOWING AGENT</a>
			<ul>
				<li>
					<a href="towing_agent_add.jsp">Add</a>&nbsp; |&nbsp;<a href="towing_agent_view.jsp">View</a>
				</li>
			</ul>
		</li>
		
		<li>
			<a style="text-decoration: none;">USER</a>
			<ul>
				<li>
					<a href="user_add.jsp">Add</a>&nbsp; |&nbsp;<a href="user_view.jsp">View</a>
				</li>
			</ul>
		</li>

		<li><a style="text-decoration: none;">VEHICLE NO</a>
			<ul>
				<li>
					<a href="vehicle_no_add.jsp">Add</a>&nbsp; |&nbsp;<a href="vehicle_no_view.jsp">View</a>
				</li>
			</ul>
		</li>
		<li><a style="text-decoration: none;">INCIDENT</a>
			<ul>
				<li>
					<a href="incident_view.jsp">View</a>&nbsp;
				</li>
			</ul>
		</li>
		<li><a a href="circle_map_view.jsp" style="text-decoration: none;">Reports</a>
			<ul>
				<li>
				<a href="daily_report_view.jsp">Daily</a>&nbsp; |&nbsp;<a href="monthly_report_view.jsp">Monthly</a>&nbsp;
					<br>|&nbsp;<a href="yearly_report_view.jsp">Yearly</a>&nbsp;
				</li>
			</ul>
		</li>
		<%
			} 
		}
		catch(Exception exception)
		{
			System.out.println(exception);
		}
		%>
	</ul>
</div>