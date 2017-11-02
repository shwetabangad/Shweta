package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDAO extends dbConnection {
	
	
		public int i=0;
		// Insert into Customer table when they book ticket
		public int createcustomer(String name,String email,String phn){	
			
			String query = "Insert into townevents_Customer values(?,?,?,?);";
			try(PreparedStatement statement = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS)){
	            statement.setLong(1,i);
				statement.setString(2, name);
	            statement.setString(3, phn);
	            statement.setString(4, email);	           
	            int resultSet = statement.executeUpdate();
	            if(resultSet == 1) {
	            	
	            	i++;	            	
	            	
	            	ResultSet rs = statement.getGeneratedKeys();
	            	rs.next();
	            	return rs.getInt(1);
	            	
	            }
	            		
	        } catch(SQLException e){
	            System.out.println("Error Creating User: " + e);
	        }
			return 0;
	     
		}
	}


