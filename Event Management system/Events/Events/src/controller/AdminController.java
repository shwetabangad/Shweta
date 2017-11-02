package controller;

import java.awt.Label;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import application.Main;
import dao.EventDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;


public class AdminController implements Initializable {
	public static String selectedEvent;
	public static String operationType;
	
	@FXML
	private Hyperlink hyplogout;
	
	@FXML
	private TextArea txtwelcomemsg;
	
	
	
	@FXML
	private Label lbluser;
	@FXML
	private ListView<String> listView;
	
	@FXML
	private List<String> list;
	@FXML
	
	//private Button editEvent;
	private Button deleteEvent;
	
	@FXML
	private Button viewevent;
	
	@FXML
	private Button editevent;
	
	@FXML
	private Button deleteevent;
	
	
	public void setUser(String username) {
		
		// TODO Auto-generated method stub
		lbluser.setText(username);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try{
			
			EventDAO e = new EventDAO();
			list = EventDAO.events.stream().map(d -> d.getEvent_title()).collect(Collectors.toList());
			ObservableList<String> items = listView.getItems();
			items.addAll(list);
			
			viewevent.setDisable(true);
			//editevent.setDisable(true);
			deleteevent.setDisable(true);
			e.close();
			

		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		}

	
	public void enableview(){
		 if(listView.getSelectionModel().getSelectedItem().isEmpty()){
	    	   viewevent.setDisable(true);
	    	  // editevent.setDisable(true);
	    	   deleteevent.setDisable(true);
			}
	       else {
	    	   viewevent.setDisable(false);
	    	   //editevent.setDisable(false);
	    	   deleteevent.setDisable(false);
	       }
			
		}
	
	
    @FXML
	public void ViewEvent() {
		selectedEvent = listView.getSelectionModel().getSelectedItem().toString();
		
		AnchorPane root;
		//selectedEvent = "";
		operationType="view";
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/EventManager.fxml"));
		  		
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.setTitle("View Event");
		Main.stage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
			
	}
    
    
    @FXML
    public void DeleteEvent(){
    	
    	selectedEvent = listView.getSelectionModel().getSelectedItem().toString();
		
		AnchorPane root;
		//selectedEvent = "";
		operationType="delete";
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/EventManager.fxml"));
		  		
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.setTitle("Delete Event");
		Main.stage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
		
    }
    
    public void Logout(){
		try{
		AnchorPane root;
		FXMLLoader loader = new FXMLLoader(); // Creating a the FXMLLoader
        loader.setLocation(getClass().getResource("/Views/Login.fxml")); // Telling it to get the proper FXML file.
       
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
        
        Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.setTitle("Home");
		Main.stage.show();
		}
	    catch(Exception e) {		
		System.out.println("Error occured while inflating view: " + e);
	     }
		
	}
	
	
	
	
}
	
