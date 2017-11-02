package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import application.Main;
import dao.EventDAO;
import dao.LoginDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.User;

public class LoginController implements Initializable{
	
	public static User user;
	public static String selectedEvent;
	public static int userID;
	

	@FXML
	private TextField username;
	
			
	@FXML
	private TextField password;
	
	@FXML
	private TextField error;
	
	@FXML
	private ListView<String> listView;
	
	@FXML
	private List<String> list;
	
	@FXML
	private Button buyloginticket;
	


	public void login() {
		User u;
		
		String username = this.username.getText();
		String password = this.password.getText();
		
		//Validations
		if(username == null || username.trim().equals("")) {
			error.setStyle("-fx-text-inner-color: red;");
			error.setText("Username Cannot be empty or spaces");
			return;
		}
		if(password == null || password.trim().equals("")) {
			error.setStyle("-fx-text-inner-color: red;");
			error.setText("Password Cannot be empty or spaces");
			return;
		}
		//Get user
		LoginDAO l = new LoginDAO();
		u = l.findByUsername(username);
		
		l.close();
		//If user not found
		if(u == null) {
			error.setStyle("-fx-text-inner-color: red;");
			error.setText("Username cannot be found");
			return;
		}
		//If passwords dont match
		if(!(u.getloginPassword().equals(password.trim()))) {
			error.setText("Username and Password dont match");
			return;
		}
		//Set user as Logged in user
		user = u;
		
		try {
			AnchorPane root;
			if(user.getrole().equals("AD")) {
				//If user is admin, inflate admin view
				
				root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/Admin.fxml"));
		

			} else {
				 userID = user.getId();
				 
				//If user is customer, inflate customer view
				root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/EMWelcome.fxml"));
							}
			         
			
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Home Page");
			Main.stage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}
	
	public void reset() {
		this.username.setText("");
		this.password.setText("");
		
	}
	
	public void register() {
		try {
			AnchorPane root;
		    root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/Register.fxml"));
		    Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Sign Up");
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
	}

	public static User getUser() {
		return user;
	}
	
	public static void logout() {
		user = null;
	}
	
	public void enablebuying(){
		
       if(listView.getSelectionModel().getSelectedItem().isEmpty()){
    	   buyloginticket.setDisable(true);
		}
       else {
    	   buyloginticket.setDisable(false);
       }
		
	}
	
	public void buyticket()throws IOException, ClassNotFoundException, SQLException {
		selectedEvent = listView.getSelectionModel().getSelectedItem().toString();
				
		AnchorPane root;
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/BuyTicket.fxml"));
		  		
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.setTitle("Buy Tickets");
		Main.stage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
			
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try{
			EventDAO e = new EventDAO();
			list = EventDAO.events.stream().map(d -> d.getEvent_title()).collect(Collectors.toList());
			
			ObservableList<String> items = listView.getItems();
			items.addAll(list);
			buyloginticket.setDisable(true);	
			e.close();
			

		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		}
	}
	