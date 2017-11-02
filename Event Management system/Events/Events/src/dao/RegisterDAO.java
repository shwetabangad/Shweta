package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegisterDAO extends dbConnection{
	public int i=0;
	
	// create new users during registration
	public int createuser(String name,String LoginName,String LoginPassword, String role, String email){
		
		
		String query = "Insert into townevents_user_table values(?,?,?,?,?,?);";
		try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1,i);
			statement.setString(2, name);
            statement.setString(3, LoginName);
            statement.setString(4, LoginPassword);
            statement.setString(5, role);
            statement.setString(6, email);
            int resultSet = statement.executeUpdate();
            if(resultSet == 1) 
            {
            	return 1;
            	
            }
            		
        } catch(SQLException e){
            System.out.println("Error Creating User: " + e);
        }
		return 0;
     
	}
	
}
