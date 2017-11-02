package controller;

import java.awt.Label;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import model.Event;


public class EMController implements Initializable {
	public static String selectedEvent;
	public static String operationType;
	
	@FXML
	private Hyperlink hypemlogout;
	
	@FXML
	private Label lbluser;
	
	@FXML
	private TextArea txtwelcomemsg;
	
	@FXML
	private ListView<String> listView;
	
	@FXML
	private List<String> list;
	
	
	@FXML	
	private Button createEvent;
	
	@FXML
	private Button editevent;
	
	public void setUser(String username) {
		
		// TODO Auto-generated method stub
		lbluser.setText(username);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try{
			
			ArrayList<Event> usereventlist;
			EventDAO e = new EventDAO();
			int userid = LoginController.userID;
			
			usereventlist = e.EventUserDAO(userid);

			//list = usereventlist.stream().map(usereventlist.getEvent_title()).collect(Collectors.toList());						
			ObservableList<String> items = listView.getItems();
			for (int i=0;i<usereventlist.size();i++)
			{
			items.addAll(usereventlist.get(i).getEvent_title());
			
			}
			
			createEvent.setDisable(false);
			//editevent.setDisable(true);
			editevent.setDisable(true);
			e.close();
			

		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		}

	
	

	public void enableview(){ 
		 if(listView.getSelectionModel().getSelectedItem().isEmpty()){
	    	   editevent.setDisable(true);
	    	  // editevent.setDisable(true);
	    	   createEvent.setDisable(true);
			}
	       else {
	    	   editevent.setDisable(false);
	    	   //editevent.setDisable(false);
	    	   createEvent.setDisable(false);
	       }
			
		}
	
	
    @FXML
	public void EditEvent() {
    	
		selectedEvent = listView.getSelectionModel().getSelectedItem().toString();
		
		AnchorPane root;
		
		operationType="edit";
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/EventManager.fxml"));
		  		
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.setTitle("Edit Event");
		Main.stage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
			
	}
    
    
    @FXML
    public void CreateEvent(){
    	
    	
    	//selectedEvent = listView.getSelectionModel().getSelectedItem().toString();
    	//System.out.println(selectedEvent);
		AnchorPane root;
		//selectedEvent = "";
		operationType="create";
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/EventManager.fxml"));
		  		
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.setTitle("Create Event");
		Main.stage.show();
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
		
    }
    
    public void EMLogout(){
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
	

