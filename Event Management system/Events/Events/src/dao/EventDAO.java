package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Event;

public class EventDAO extends dbConnection {
	public static List<Event> events;
	
	public static int i=0;
	public EventDAO() throws ClassNotFoundException, SQLException {

		try {
			String query = "SELECT * FROM townevents_EVENT_BOOKINGS";
			try(PreparedStatement statement = connection.prepareStatement(query)){
				events = new ArrayList<Event>();
				ResultSet results = statement.executeQuery();
				
				while (results.next()) {
					
					events.add(new Event(results.getInt("Event_ID"), results.getString("EVENT_TITLE"), results.getString("EVENT_TYPE_CODE"),
							results.getString("EVENT_DESCRIPTION"), results.getString("ADDRESS")));
				}

			}

		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		
		
	}


	
	public ArrayList<Event> EventUserDAO(int userid) throws ClassNotFoundException, SQLException {

		try {
			
			String query = "SELECT * FROM townevents_EVENT_BOOKINGS where USER_ID = ?";
			try(PreparedStatement statement = connection.prepareStatement(query)){
				statement.setInt(1,userid);
				ArrayList<Event> Userevents = new ArrayList<Event>();
				ResultSet results = statement.executeQuery();
				
				while (results.next()) {
					System.out.println(query);
					System.out.println(results.getInt("Event_ID"));
					Userevents.add(new Event(results.getInt("Event_ID"), results.getString("EVENT_TITLE"), results.getString("EVENT_TYPE_CODE"),
							results.getString("EVENT_DESCRIPTION"), results.getString("ADDRESS")));
				}
				return Userevents;

			}

		} catch (SQLException e) {
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}

		return null;
		
	}

	
	// find event details by using event title
	public Event findByEventTitle(String eventtitle) {
		System.out.println("PPP");
		Event event = null;
		String query = "SELECT * FROM townevents_Event_Bookings WHERE event_title = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, eventtitle);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	event = new Event(   
            			resultSet.getInt("EVENT_ID"),
            			resultSet.getString("EVENT_TITLE"),
            			resultSet.getString("EVENT_TYPE_CODE"),
            			resultSet.getString("EVENT_DESCRIPTION"),
            			resultSet.getString("ADDRESS"),
            			resultSet.getDate("EVENT_START_DATE"),
            			resultSet.getDate("EVENT_END_DATE")            			
            			);
            	
            }
        } catch(SQLException e){
            System.out.println("Error Finding User by Login Name: " + e);
        }
        return event;
    }
	
	
	
	// create a new event
	public int createevent( String eventtitle, String eventtypecode, String eventdesc, String add, LocalDate startdate,
			LocalTime starttime, LocalDate  enddate, LocalTime  endtime, int userid)
	
	{
		System.out.println("Create");
		
		String query = "Insert into townevents_event_bookings values(?,?,?,?,?,?,?,?,?,?);";
		try(PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
            statement.setLong(1,i);
			statement.setString(2, eventtitle);
			statement.setString(3, eventtypecode);
            statement.setString(4, eventdesc);
            statement.setString(5, add);
            statement.setObject( 6, startdate );
            statement.setObject( 7, enddate );
            statement.setObject( 8, starttime );
            statement.setObject( 9, endtime );
            statement.setInt(10, userid);
            
            int resultSet = statement.executeUpdate();
            
            if (resultSet==1){
            	System.out.println("Created");
            ResultSet rs = statement.getGeneratedKeys();
        	rs.next();
        	return rs.getInt(1);
            }          
            		
        } catch(SQLException e)
		{
            System.out.println("Error Creating event: " + e);
        
		return 0;
 

	}
		return 0;
		
	}

	
	// update an existing event
	public int updateevent( String preveventname, String eventtitle, String eventtypecode, String eventdesc, String add, Date startdate,
			LocalTime starttime, Date  enddate, LocalTime  endtime, int userid)
	
	{
		
		
		String query = "update townevents_event_bookings set EVENT_TITLE=?,EVENT_TYPE_CODE=?,"
				+ "EVENT_DESCRIPTION=?,ADDRESS=?,EVENT_START_DATE=?,"
				+ "EVENT_END_DATE=?,EVENT_START_TIME=?,EVENT_END_TIME=?, USER_ID=? where EVENT_TITLE=?";
		try(PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
            statement.setString(10,preveventname);
			statement.setString(1, eventtitle);
			statement.setString(2, eventtypecode);
            statement.setString(3, eventdesc);
            statement.setString(4, add);
            statement.setObject( 5, startdate );
            statement.setObject( 6, enddate );
            statement.setObject( 7, starttime );
            statement.setObject( 8, endtime );
            statement.setInt(9, userid);
            System.out.println(eventtypecode);
            System.out.println(endtime);
            int resultSet = statement.executeUpdate();
            
            if (resultSet==1){
            return 1;
            }          
            		
        } catch(SQLException e)
		{
            System.out.println("Error Updating event: " + e);
        
		return 0;
 

	}
		return 0;
		
	}

	
	
	
	
	// delete an event using event title
	public int deleteByEventTitle(String eventtitle, int eventid){
		// first delete from child table Event_Tickets
		String query1 = "Delete from townevents_Event_Tickets where event_id = ?";
		
		
		try(PreparedStatement statement1 = connection.prepareStatement(query1)){
            statement1.setInt(1,eventid);
           
			int resultSet1 = statement1.executeUpdate();
            if(resultSet1 == 0) 
            {
            	return 0;
            	
            }
            		
            else{	
		
		
		String query = "Delete from townevents_event_bookings where event_title = ?";
		try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1,eventtitle);
            
			int resultSet = statement.executeUpdate();
            if(resultSet == 1) 
            {
            	return 1;
            	
            }
            		
        } catch(SQLException e)
		{
            System.out.println("Error deleting event: " + e);		
		return 0;
		}
            }
		}catch(SQLException e)
		{
            System.out.println("Error deleting event tickets: " + e);		
		return 0;
		}
		return 0;
	}
		
	
	
	// insert into event_tickets
	public int eventtickets( int eventticketid, int eventid, String tickettypecode,
			int tickettypecapacity, float ticketfare)
	
	{
		
		
		String query = "Insert into townevents_event_tickets values(?,?,?,?,?);";
		try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1,i);
			statement.setInt(2, eventid);
			statement.setString(3, tickettypecode);
            statement.setInt(4, tickettypecapacity);
            statement.setFloat(5, ticketfare);
            System.out.println(statement);
            int resultSet = statement.executeUpdate();
            if(resultSet == 1) 
            {
            	return 1;
            	
            }
            		
        } catch(SQLException e)
		{
            System.out.println("Error Creating tickets capacity: " + e);
        
		return 0;
 

	}
		return 0;
		
	}
	
	
	// find event details by using event ID
	public Event findByEventID(int eventId) {
		System.out.println("MMM");
		Event event = null;
		String query = "SELECT * FROM townevents_Event_Bookings WHERE event_id = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, eventId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	event = new Event(   
            			resultSet.getInt("EVENT_ID"),
            			resultSet.getString("EVENT_TITLE"),
            			resultSet.getString("EVENT_TYPE_CODE"),
            			resultSet.getString("EVENT_DESCRIPTION"),
            			resultSet.getString("ADDRESS"),
            			resultSet.getDate("EVENT_START_DATE"),
            			resultSet.getDate("EVENT_END_DATE")            			
            			);
            	return event;
            }
        } catch(SQLException e){
            System.out.println("Error Finding User by Login Name: " + e);
        }
        return event;
    }

	
	
	public Event findByEventTitleforedit(String eventTitle) {
		
		System.out.println(eventTitle);
		Event event = null;
		String query = "SELECT * FROM townevents_Event_Bookings WHERE event_title = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, eventTitle);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	
            	event = new Event(    
            			resultSet.getInt("EVENT_ID"),
            			resultSet.getString("EVENT_TITLE"),
            			resultSet.getString("EVENT_TYPE_CODE"),
            			resultSet.getString("EVENT_DESCRIPTION"),
            			resultSet.getString("ADDRESS"),
            			resultSet.getDate("EVENT_START_DATE"),
            			resultSet.getDate("EVENT_END_DATE")            			
            			);
            	
            }
        } catch(SQLException e){
            System.out.println("Error Finding event by Event Title: " + e);
        }
        return event;
   
	}

	//find details about event tickets when eventID is known
	public ArrayList<Event> findTicketsByEventIDforEdit(Integer event_id) {

		ArrayList<Event> t1 = new ArrayList<Event>();
		
		String query = "SELECT * FROM townevents_Event_tickets WHERE event_id = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, event_id);
           try( ResultSet resultSet = statement.executeQuery()){
        	                  System.out.println(event_id);	
            	while(resultSet.next())
            	{
            		Event tic = new Event();
            		tic.setEvent_id(resultSet.getInt("EVENT_ID"));
            		tic.setEvent_title(resultSet.getString("EVENT_TITLE"));
            		tic.setAddress(resultSet.getString("ADDRESS"));
            		tic.setEvent_description(resultSet.getString("EVENT_DESCRIPTION"));
            		tic.setEvent_type_code(resultSet.getString("EVENT_TYPE_CODE"));
            		tic.setEvent_start_date(resultSet.getDate("event_start_date"));
            		tic.setEvent_end_date(resultSet.getDate("event_end_date"));
            		tic.setEvent_start_time(resultSet.getTime("event_start_time"));
            		tic.setEvent_start_time(resultSet.getTime("event_end_time"));
            	t1.add(tic);
            	
            	
            	}
            	return t1;
            	
            }
        } catch(SQLException e){
            System.out.println("Error Finding User by Ticket details: " + e);
        }
        return t1;
        
	}



}

	
	


	