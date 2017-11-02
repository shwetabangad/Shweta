package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection implements AutoCloseable {

	protected static Connection connection;
    private static String url = "jdbc:mysql://www.papademas.net:3306/fp";
    private static String username = "dbfp";
    private static String password = "510";
    
  
    
    public dbConnection() {
    	try {
    	  connection = DriverManager.getConnection(url,username,password);
        } catch(SQLException e) {
            System.out.println("Error creating connection to database: " + e);
            System.exit(-1);
        }
    }
    
	@Override
	public void close() {
    	try {
            connection.close();
            connection = null;
        } catch(SQLException e) {
            System.out.println("Error closing connection: " + e);
        }
	}
}
