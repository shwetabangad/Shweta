package controller;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import application.Main;
import dao.EventDAO;
import dao.TicketDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import model.Event;
import model.Ticket;


		public class EventController implements Initializable{
			
			
			@FXML
			private TextField txteventtitle;
			
			@FXML
			private TextField txteventdesc;
			
			@FXML
			private TextField txtadd;
			
			@FXML
			private JFXDatePicker startdate;
			
			@FXML
			private JFXDatePicker enddate;
			
			@FXML
			private JFXTimePicker starttime;
			
			@FXML
			private JFXTimePicker endtime;
			
			@FXML
			private TextField txterror;
			
			@FXML
			private RadioButton chrRadio;
			
			@FXML 
			private RadioButton entRadio;
				
			@FXML
			private RadioButton netRadio;
			
			@FXML 
			private RadioButton traRadio;
			
			@FXML
			private TextField childquty;
			
			@FXML
			private TextField childprice;
			
			@FXML
			private TextField adultquty;
			
			@FXML
			private TextField adultprice;
			
			@FXML 
			private Label labeladult;
			
			@FXML 
			private Label labelchild;
			
			@FXML
			private ToggleGroup eventtype;
			
			@FXML
			private ListView<String> listView;
			
			@FXML
			private List<String> list;
			
			@FXML
			private Button viewevent;
			
			@FXML
			private Button editevent;
			
			@FXML
			private Button btncreate;
			
			@FXML
			private Button btndelete;
			
			@FXML
			private Button deleteevent;
			
			@FXML
			private Button btnupdate;
			
			SimpleDateFormat d = new SimpleDateFormat ("dd/MM/yyyy");
			
			public int eventid;
			public String eventprevname;
			public String eventTitleInitialize = "";
			
			@Override
			public void initialize(URL arg0, ResourceBundle arg1){
				
				eventprevname = EMController.selectedEvent;
				
				// for View Mode - Set all fields to read only mode 
				if(AdminController.operationType == "view")
				{
					btncreate.setVisible(false);
					btndelete.setVisible(false);
					btnupdate.setVisible(false);
					editableallfields();
				}
				else if (AdminController.operationType == "delete")
				{
					btncreate.setVisible(false);
					btndelete.setVisible(true);
					btnupdate.setVisible(false);
				}
				else if (EMController.operationType == "edit")
				{
					
					btncreate.setVisible(false);
					btndelete.setVisible(false);
					btnupdate.setVisible(true);
				}
				else
				{ // create event
					btncreate.setVisible(true);
					btndelete.setVisible(false);
					btnupdate.setVisible(false);
				}
				
				
							
				Event e;
				
				
				
				
				if (!(EMController.operationType == null))
				{
				if (EMController.operationType == "edit")
				{
					eventTitleInitialize = (EMController.selectedEvent);
					
				}
				} else if (!(AdminController.operationType == null)){
					eventTitleInitialize = (AdminController.selectedEvent);
					
					
				}
				
				// When operation type is view/delete/edit - pre-populate with the event details
				if ((AdminController.operationType == "delete") || (AdminController.operationType == "view") || (EMController.operationType == "edit"))
						{
				//Get Event
				try{
				// Retrieve Event Details	
				EventDAO e1 = new EventDAO();
				
				
				e = e1.findByEventTitleforedit(eventTitleInitialize);
				e1.close();
				
				txteventtitle.setText(e.getEvent_title());
				txteventdesc.setText(e.getEvent_description());
				txtadd.setText(e.getAddress());
				
				
				startdate.setPromptText(d.format(e.getEvent_start_date()));
				enddate.setPromptText(d.format(e.getEvent_end_date()));
				
				LocalDate startdateprompt = LocalDate.parse(e.getEvent_start_date().toString());
				startdate.setValue(startdateprompt);
				
				LocalDate enddateprompt = LocalDate.parse(e.getEvent_end_date().toString());
				enddate.setValue(enddateprompt);
				
				
				
				
				if (e.getEvent_type_code().equals("CHR"))
				{	chrRadio.setSelected(true);
				}				
				if (e.getEvent_type_code().equals("NTW"))
				{
					netRadio.setSelected(true);
				}				
				if (e.getEvent_type_code().equals("ENT"))
				{
				entRadio.setSelected(true);
				}				
				if (e.getEvent_type_code().equals("TRVL"))
				{ 
					traRadio.setSelected(true);
				}			
						
				
				// Retrieve Ticket and fare details
				ArrayList<Ticket> teventtickets;
				eventid = e.getEvent_id();
				TicketDAO t2 = new TicketDAO();							   	
				teventtickets = t2.findTicketsByEventID(eventid);
				t2.close();
	            
				adultquty.setText(teventtickets.get(0).getTicket_type_capacity());
				adultprice.setText(teventtickets.get(0).getTicket_fare().toString());
				
				childquty.setText(teventtickets.get(1).getTicket_type_capacity());
				childprice.setText(teventtickets.get(1).getTicket_fare().toString());
				
				}catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}
						}	
			}
					
			
			
			public void eventickets(int eventid)
			{
				int CHL = Integer.parseInt(this.childquty.getText());
				float CHL1 = Float.parseFloat(this.childprice.getText());
				int ADL = Integer.parseInt(this.adultquty.getText());
				float ADL1 = Float.parseFloat(this.adultprice.getText());
				
			    int j=0;
			    int k=0;
			    
			    EventDAO et;
				try {
					et = new EventDAO();
					j = et.eventtickets(j, eventid, "ADL", ADL, ADL1);
					k = et.eventtickets(k, eventid, "CHL", CHL, CHL1);
				    et.close();
				    					    
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   
			}
				
			
			// Method to create a new Event
			public void createeventB(){
				
				String eventtitle = this.txteventtitle.getText();
				String eventdesc = this.txteventdesc.getText();
				String add = this.txtadd.getText();
				
				int eid =0 ;
				int userid= LoginController.userID;
				String evchr = "CHR";
				String evntw = "NTW";
				String evtra = "TRVL";
				String event = "ENT";
				
				
				LocalTime Sstarttime = LocalTime.now();
				LocalTime Eendtime = LocalTime.now();
				
				
				LocalDate Sstartdate = this.startdate.getValue();
				LocalDate Eenddate = this.enddate.getValue();
				
				
				
				try{
				EventDAO l = new EventDAO();
				
				
				
				if (chrRadio.isSelected()) {
					
				eid = l.createevent(eventtitle,evchr, eventdesc, add, Sstartdate ,Sstarttime,Eenddate,Eendtime,userid);
				}
				if (entRadio.isSelected())
				{
					eid = l.createevent(eventtitle,event, eventdesc, add, Sstartdate ,Sstarttime,Eenddate,Eendtime,userid);
				}
				if (netRadio.isSelected())
				{
					eid = l.createevent(eventtitle,evntw, eventdesc, add, Sstartdate ,Sstarttime,Eenddate,Eendtime,userid);
				}
				if (traRadio.isSelected())
				{
					eid = l.createevent(eventtitle,evtra, eventdesc, add, Sstartdate ,Sstarttime,Eenddate,Eendtime,userid);
				}
				
				l.close();
				{
				//If event not found
				if(eid == 0) {
					txterror.setStyle("-fx-text-inner-color: red;");
					txterror.setText("Event cannot be created");
					return;
				}
				else {
					txterror.setStyle("-fx-text-inner-color: green;");
					txterror.setText("Event successfully booked");
					
					eventickets(eid);
					// make an insert into table Event_tickets.
				}
				}
				
			}
				catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
	
			
			
			// Update Event
               public void updateevent(){
            	  
            	   
				String eventtitle = this.txteventtitle.getText();
				String eventdesc = this.txteventdesc.getText();
				String add = this.txtadd.getText();
				
				int eid =0 ;
				int userid= LoginController.userID;
				String evchr = "CHR";
				String evntw = "NTW";
				String evtra = "TRVL";
				String event = "ENT";
				LocalTime Sstarttime = LocalTime.now();
				LocalTime Eendtime = LocalTime.now();
				
				
				java.sql.Date Sstartdate = java.sql.Date.valueOf(this.startdate.getValue());
				java.sql.Date Eenddate = java.sql.Date.valueOf(this.enddate.getValue());
				
				
				try{
				EventDAO l = new EventDAO();
				
				
				if (chrRadio.isSelected()) {
					
				eid = l.updateevent(eventprevname,eventtitle,evchr, eventdesc, add, Sstartdate ,Sstarttime,Eenddate,Eendtime,userid);
				}
				if (entRadio.isSelected())
				{
					eid = l.updateevent(eventprevname,eventtitle,event, eventdesc, add, Sstartdate ,Sstarttime,Eenddate,Eendtime,userid);
				}
				if (netRadio.isSelected())
				{
					eid = l.updateevent(eventprevname,eventtitle,evntw, eventdesc, add,Sstartdate ,Sstarttime,Eenddate,Eendtime,userid);
				}
				if (traRadio.isSelected())
				{
					eid = l.updateevent(eventprevname,eventtitle,evtra, eventdesc, add, Sstartdate ,Sstarttime,Eenddate,Eendtime,userid);
				}
				
				l.close();
				{
				//If event not found
				if(eid == 0) {
					txterror.setStyle("-fx-text-inner-color: red;");
					txterror.setText("Event cannot be updated");
					return;
				}
				else {
					txterror.setStyle("-fx-text-inner-color: green;");
					txterror.setText("Event successfully updated");
					
					eventickets(eid);
					// make an insert into table Event_tickets.
				}
				}
				
			}
				catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
	
			
			// delete the event
		public void deleteevent(){
			int i,eventid = 0;
			try{
				
				Event e;
				try{
					// Retrieve Event Details	
					EventDAO e2 = new EventDAO();
					e = e2.findByEventTitleforedit(AdminController.selectedEvent);
					e2.close();
					eventid = e.getEvent_id();
					
					
				}catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					
					e1.printStackTrace();
				}
			
					
				// Retrieve Event Details	
				EventDAO e1 = new EventDAO();
				i = e1.deleteByEventTitle(AdminController.selectedEvent,eventid);
				
				e1.close();
				
				if (i==0)
				{
					System.out.println("Error in deleting the Event");
				}
				else
				{
					txterror.setText("Event Deleted Successfully.");
					disableallfields();
				}
			    }catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				
				e1.printStackTrace();
			}
		}
		
		
		// make all fields disabled
		@FXML
		public void disableallfields() {
			txteventtitle.setDisable(true);
			txteventdesc.setDisable(true);
			txtadd.setDisable(true);
			startdate.setDisable(true);
			enddate.setDisable(true);
			starttime.setDisable(true);
			endtime.setDisable(true);
			chrRadio.setDisable(true);
			entRadio.setDisable(true);
			netRadio.setDisable(true);
			traRadio.setDisable(true);
			childquty.setDisable(true);
			childprice.setDisable(true);
			adultquty.setDisable(true);
			adultprice.setDisable(true);
			
			
		}
		
		// make all fields uneditable
		@FXML
		public void editableallfields() {
			txteventtitle.setEditable(false);
			txteventdesc.setEditable(false);
			txtadd.setEditable(false);
			startdate.setEditable(false);
			enddate.setEditable(false);
			starttime.setEditable(false);
			endtime.setEditable(false);
			chrRadio.setDisable(true);
			entRadio.setDisable(true);
			netRadio.setDisable(true);
			traRadio.setDisable(true);
			childquty.setEditable(false);
			childprice.setEditable(false);
			adultquty.setEditable(false);
			adultprice.setEditable(false);
		
			
			
		}



		// for Admin go back to Admin Welcome Page , for EventMAnager go back to EM Welcome page
		public void back(){
		AnchorPane root;
				
		try {
		if (AdminController.operationType == "delete" || AdminController.operationType == "view")	
		{
		root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/Admin.fxml"));
		  		
		Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.setTitle("Admin Welcome Page");
		Main.stage.show();
		}
		else if (EMController.operationType == "edit" || EMController.operationType == "create")
		{
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/EMWelcome.fxml"));
	  		
			Scene scene = new Scene(root);
			Main.stage.setScene(scene);
			Main.stage.setTitle("Event Manager Welcome Page");
			Main.stage.show();
			
		}
		} catch(Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}
		
		}
		}














			












	