package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.BookedTickets;

public class TicketBookingDAO extends dbConnection{

	
	public int i=0;
	//insert into ticket booking when new tickets are booked for the event
	public int createticketbooking(int eventid,int customerid, int passnum, float bookingamt, int ticketqty){	
		
		String query = "Insert into townevents_ticket_booking values(?,?,?,?,?,?);";
		try(PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
            statement.setLong(1,i);
			statement.setInt(2, eventid);
            statement.setInt(3, customerid);
            statement.setInt(4, passnum);
            statement.setFloat(5, bookingamt);
            statement.setInt(6, ticketqty);
            int resultSet = statement.executeUpdate();
            if(resultSet == 1) {
            	i++;
            	
            	ResultSet rs = statement.getGeneratedKeys();
            	rs.next();
            	return rs.getInt(1);
            	
            }
            		
        } catch(SQLException e){
            System.out.println("Error Creating Ticket: " + e);
        }
		return 0;
	}
	
	// find the total booking amount for the given booking.
	public BookedTickets findtotalbybookingID(int bookingID){
		BookedTickets ticket = null;
		String query = "Select Booking_Amt from townevents_ticket_booking where booking_Id = ?;";
		try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, bookingID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	ticket = new BookedTickets(
            			resultSet.getFloat("Booking_Amt"),
            			resultSet.getInt("Event_ID"),
            			resultSet.getInt("Pass_Num")            			
            			);
            	return ticket;
            }
        } catch(SQLException e){
            System.out.println("Error Finding User by Login Name: " + e);
        }
		return ticket;
	}
	
	
	// find booking details for the given booking id
	public BookedTickets findBybookingID(int bookingId){		
		BookedTickets tck = null;
		
		String query = "SELECT * FROM townevents_ticket_booking WHERE Booking_Id = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1, bookingId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	tck = new BookedTickets(            			
            			resultSet.getInt("EVENT_ID"),
            			resultSet.getInt("CUSTOMER_ID"),
            			resultSet.getInt("PASS_NUM"),
            			resultSet.getFloat("Booking_Amt"),
            			resultSet.getInt("Ticket_qty"),    
            			resultSet.getInt("Booking_ID")
            			);
            	
            }
        } catch(SQLException e){
            System.out.println("Error Finding User by Login Name: " + e);
        }
        return tck;
    }
	
		
	// delete the booking from ticket_booking table
	public void deleteBooking(){
        String query = "Delete from townevents_ticket_booking ORDER BY Booking_Id DESC LIMIT 1;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
        	
            int resultSet = statement.executeUpdate();
                    	if(resultSet == 0){
                    		
            	
            }
        } catch(SQLException e){
            System.out.println("Error Finding User by Login Name: " + e);
        }
       
    }
	
	
	// get details about the current booking
	public BookedTickets currentBooking(){		
		BookedTickets ticket = null;
		String query = "Select * from townevents_ticket_booking ORDER BY Booking_Id DESC LIMIT 1;";
		try(PreparedStatement statement = connection.prepareStatement(query)){
            
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	ticket = new BookedTickets(            			
            			resultSet.getInt("EVENT_ID"),
            			resultSet.getInt("CUSTOMER_ID"),
            			resultSet.getInt("PASS_NUM"),
            			resultSet.getFloat("Booking_Amt"),
            			resultSet.getInt("Ticket_qty"),    
            			resultSet.getInt("Booking_ID")
            			);
            	return ticket;
            }
        } catch(SQLException e){
            System.out.println("Error Finding User by Login Name: " + e);
        }
		return ticket;
	}
	
	}

