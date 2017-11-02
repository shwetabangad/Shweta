package controller;



import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.Main;
import dao.CustomerDAO;
import dao.EventDAO;
import dao.TicketBookingDAO;
import dao.TicketDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.BookedTickets;
import model.Event;
import model.Ticket;

	


public class TicketController implements Initializable{

	@FXML
	private Label lblTitle;
	
	@FXML
	private Label lblDescription;
	
	@FXML
	private Label lblAddress;
	
	@FXML
	private Label lblStartDate;
	
	@FXML
	private Label lblEndDate;
	
	@FXML
	private Label lblchild;
	
	@FXML
	private Label lbladult;
	
	@FXML
	private Label lblchildcost;
	
	@FXML
	private Label lbladultcost;
	
	@FXML
	private TextField txtchild;
	
	@FXML
	private TextField txtadult;
	
	@FXML
	private TextField txterror;
	
	
	@FXML
	private TextField txtcphn;
	
	@FXML
	private TextField txtcemail;
	
	@FXML
	private TextField txtcname;
	
	
	
	SimpleDateFormat d = new SimpleDateFormat ("dd/MM/yyyy");
	public int Passnum,ticketqty;
	public int eventid;
	public int bookingId;
	public float totalamt;
	public int tckchildA;
	public int tckadultA;
	
	
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		
		Event e;
		ArrayList<Ticket> t1;
		String eventTitle = (LoginController.selectedEvent);		
		txtadult.setText("0");
		txtchild.setText("0");
		
		
		//Get Event
		try{
		EventDAO e1 = new EventDAO();
		e = e1.findByEventTitle(eventTitle);
		e1.close();
		lblTitle.setText(eventTitle);
		lblDescription.setText(e.getEvent_description());
		lblAddress.setText(e.getAddress());
		lblStartDate.setText(d.format(e.getEvent_start_date()));
		lblEndDate.setText(d.format(e.getEvent_end_date()));
		
		eventid = e.getEvent_id();
		TicketDAO t2 = new TicketDAO();
		t1 = t2.findTicketsByEventID(e.getEvent_id());
		t2.close();
	   		
		if (t1.get(0).getTicket_type_code().equals("ADL"))
		{
			lbladult.setText(t1.get(0).getTicket_type_capacity());
			tckadultA = Integer.parseInt(t1.get(0).getTicket_type_capacity());
			lbladultcost.setText(t1.get(0).getTicket_fare().toString());
			
		}
		else
		{
			lblchild.setText(t1.get(0).getTicket_type_capacity());
			tckchildA = Integer.parseInt(t1.get(0).getTicket_type_capacity());
			lblchildcost.setText(t1.get(0).getTicket_fare().toString());
			
		}
		
		if (t1.get(1).getTicket_type_code().equals("CHL"))
		{
		lblchild.setText(t1.get(1).getTicket_type_capacity());
		tckchildA = Integer.parseInt(t1.get(1).getTicket_type_capacity());
		lblchildcost.setText(t1.get(1).getTicket_fare().toString());
		
		}
		else
		{
			lbladult.setText(t1.get(1).getTicket_type_capacity());
			tckadultA = Integer.parseInt(t1.get(1).getTicket_type_capacity());
			lbladultcost.setText(t1.get(1).getTicket_fare().toString());
			
		}
		
		
		
		}catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
		
			e1.printStackTrace();
		}
					
	}
	
	@FXML
	public void reset(){
		txtcname.setText("");
		txtcemail.setText("");
		txtcphn.setText("");
	}
	
	
	@FXML
	public void backtomain(){
		
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
	public void proceed(){
		
		AnchorPane root;
		/// tickets fare total
		int tckchild = Integer.parseInt(this.txtchild.getText());
		int tckadult = Integer.parseInt(this.txtadult.getText());
		float farechld = Float.parseFloat(this.lblchildcost.getText());
		float fareadult = Float.parseFloat(this.lbladultcost.getText());
		float bookingamt = (tckchild * farechld) + (tckadult * fareadult);
		int ticktqty = tckchild + tckadult;
		
		if(ticktqty==0)
		{txterror.setStyle("-fx-text-inner-color: red;");
			txterror.setText("You should buy atleast one ticket");
			return;
		}
		/// validation for ticket count
		if(Integer.parseInt(txtchild.getText()) > tckchildA)
		{txterror.setStyle("-fx-text-inner-color: red;");
			txterror.setText("Child Ticket Count cannot exceed availability");
			return;
		}
		if(Integer.parseInt(txtadult.getText()) > tckadultA)
		{txterror.setStyle("-fx-text-inner-color: red;");
			txterror.setText("Adult Ticket Count cannot exceed availability");
			return;
		}
		
				
		//creating user
		String name = this.txtcname.getText();
		
		String phn = this.txtcphn.getText();
		
		      String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		      String email = this.txtcemail.getText();
		      Boolean b = email.matches(EMAIL_REGEX);
		      
		      if (b==false)
		      {txterror.setStyle("-fx-text-inner-color: red;");
		    	  txterror.setText("enter valid email");
					return;
		      }
		
				
		//Validations for user details
		if(name == null || name.trim().equals("")) {
			txterror.setStyle("-fx-text-inner-color: red;");
				txterror.setText("Name Cannot be empty or spaces");
				return;
			}
		
		if(email == null || email.trim().equals("")) {
			txterror.setStyle("-fx-text-inner-color: red;");
			txterror.setText("Email Cannot be empty or spaces");
			return;
		}
	
		
		if(phn == null || phn.trim().equals("")) {
			txterror.setStyle("-fx-text-inner-color: red;");
			txterror.setText("Phone Cannot be empty or spaces");
			return;
		}
		
		if (!phn.matches("[1-9][0-9]{9}"))
		{txterror.setStyle("-fx-text-inner-color: red;");
			txterror.setText("Phone number can only be 10 digits number");
			return;
		}
		
		int i;
		CustomerDAO l = new CustomerDAO();
		i = l.createcustomer(name,email,phn);
		l.close();
		//If user not found
		if(i == 0) {
			txterror.setStyle("-fx-text-inner-color: red;");
			txterror.setText("Customer cannot be created");
			return;
		}
		else {
			txterror.setStyle("-fx-text-inner-color: red;");
			txterror.setText("Customer successfully created");
				
	
			
		//create ticket
			int customerid = i;
			int j;
			int currentpass = customerid;
						
			TicketBookingDAO t = new TicketBookingDAO();
			j = t.createticketbooking(eventid,customerid,currentpass,bookingamt,ticktqty);
			t.close();
			//If user not found
			if(j == 0) {
				txterror.setStyle("-fx-text-inner-color: red;");
				txterror.setText("Ticket cannot be booked");				
				return;
			}
			else {
				
				bookingId = j;
				BookedTickets currentbooking;
				
				TicketBookingDAO t1 = new TicketBookingDAO();
				currentbooking = t1.findBybookingID(bookingId);
				totalamt = 	currentbooking.getBooking_amt();			
				Passnum = currentbooking.getPass_num();
				ticketqty = currentbooking.getTicket_qty();
				t1.close();
			}
				
					
			
			
		/// passing parameters to next page
		try{		
		FXMLLoader loader = new FXMLLoader(); // Creating a the FXMLLoader
        loader.setLocation(getClass().getResource("/Views/MakePaymentScreen.fxml")); // Telling it to get the proper FXML file.
        loader.load(); // Loading said FXML.
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/MakePaymentScreen.fxml"));
        
        Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.setTitle("Payment");
		Main.stage.show();
		 
		PaymentController ccontroller = loader.getController();		
		ccontroller.ticketdetails(bookingId,eventid,Passnum,ticketqty,totalamt);
				
    	}
	    catch(Exception e) {		
		System.out.println("Error occured while inflating view: " + e);
	     }
		}
		
		
	}

	
}
