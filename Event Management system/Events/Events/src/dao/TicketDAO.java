package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Ticket;

public class TicketDAO extends dbConnection {
	
	// find the ticket details for the given EventID
	public ArrayList<Ticket> findTicketsByEventID(int eventID) {
		
		
		ArrayList<Ticket> t1 = new ArrayList<Ticket>();
		
		String query = "SELECT * FROM townevents_Event_tickets WHERE event_id = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, eventID);
           try( ResultSet resultSet = statement.executeQuery()){
        	               
            	while(resultSet.next())
            	{   	
            		Ticket tic = new Ticket();
            		tic.setTicket_type_code(resultSet.getString("TICKET_TYPE_CODE"));
            		tic.setTicket_type_capacity(resultSet.getString("TICKET_TYPE_CAPACITY"));
            		tic.setTicket_fare(resultSet.getFloat("TICKET_FARE"));
            	
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
