
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.dbConnection;
import model.User;

public class LoginDAO extends dbConnection {
	
	// find the existing user for login authentication
	public User findByUsername(String username) {
		User user = null;
		String query = "SELECT * FROM townevents_user_table WHERE Login_name = ?;";
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
            	user = new User(
            			resultSet.getInt("USER_ID"),
            			resultSet.getString("USER_NAME"),
            			resultSet.getString("LOGIN_NAME"),
            			resultSet.getString("LOGIN_PASSWORD"),
            			resultSet.getString("ROLE_CODE"),
            			resultSet.getString("EMAIL")
            			);
            }
        } catch(SQLException e){
            System.out.println("Error Finding User by Login Name: " + e);
        }
        return user;
    }
}
