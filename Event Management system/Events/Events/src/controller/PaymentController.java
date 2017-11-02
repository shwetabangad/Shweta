package controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Main;
import dao.EventDAO;
import dao.TicketBookingDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.BookedTickets;
import model.Event;

public class PaymentController implements Initializable{
	
	@FXML
	public TextField txtcreditcard;
	
	@FXML
	public TextField txtcvv;
	
	@FXML
	public TextField txttotal;
	
	@FXML
	public TextField txterror;
	
	@FXML
	public TextField txterror1;
	
	@FXML
	public DatePicker expdate;
	
	@FXML
	public TextArea txtticketdetails;
	
	@FXML
	public Hyperlink hypHome;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		hypHome.setDisable(true);
	}
	
	@FXML
	public void reset(){
		txtcreditcard.setText("");
		txtcvv.setText("");
		expdate = null;
	}
	
	public int bookingId;
	public int eventId;
	public int passNum;
	public int ticketQty;
	public float Fare;
	
	public void cancel(){
		try{
									
		AnchorPane root;
		FXMLLoader loader = new FXMLLoader(); // Creating a the FXMLLoader
        loader.setLocation(getClass().getResource("/Views/BuyTicket.fxml")); // Telling it to get the proper FXML file.
       
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/BuyTicket.fxml"));
        
        Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.setTitle("Buy Ticket");
		Main.stage.show();
		
		
		// delete the existing booking		
		TicketBookingDAO tD = new TicketBookingDAO();
		tD.deleteBooking();
		tD.close();
		
	    }
    catch(Exception e) {		
	System.out.println("Error occured while inflating view: " + e);
     }
	}
	
	public void Home(){
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
	
	
	public void ticketdetails(int bookingID,int eventid, int pass_num, int ticket_qty, float fare){
		bookingId = bookingID;
		eventId = eventid;
		passNum = pass_num;
		ticketQty = ticket_qty;
		Fare = fare;			
	}
	
	public void pay() throws ParseException{
		txterror1.setText("");
		//validations
		String creditcard = this.txtcreditcard.getText();
		String cvv = this.txtcvv.getText();
		String expdate = this.expdate.getValue().toString();
		LocalDate expd = this.expdate.getValue();
		//Validations
		if(creditcard == null || creditcard.trim().equals("")) {
			txterror1.setStyle("-fx-text-inner-color: red;");
				txterror1.setText("Credit Card Cannot be empty or spaces");
				return;
			}
		
		/*if(creditcard.length() > 10) {
			txterror1.setStyle("-fx-text-inner-color: red;");
			txterror1.setText("Credit Card can be only 10 digits");
			return;
		}*/
		
		// validation for numeric credit card
		
		if(!creditcard.matches("[1-9][0-9]{15}"))
		{txterror1.setStyle("-fx-text-inner-color: red;");
		txterror1.setText("Credit card must be 16 digits number");
			return;
		}
		
  
		
		if(cvv == null || cvv.trim().equals("")) {
			txterror1.setStyle("-fx-text-inner-color: red;");
			txterror1.setText("CVV Cannot be empty or spaces");
			return;
		}
		
		if(cvv.length() > 3) {
			txterror1.setStyle("-fx-text-inner-color: red;");
			txterror1.setText("CVV can be only 3 digits");
			return;
		}
		
		//DateFormat d = new SimpleDateFormat("yyyy/MM/dd");
		if (expdate == null) {
			txterror1.setStyle("-fx-text-inner-color: red;");
			txterror1.setText("Exp date cannot be null");
			return;
		}
		//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		//simpleDateFormat.setLenient(false);
		java.util.Date expiry;
		expiry = (Date.valueOf(expd));
		System.out.println(expiry.before(Date.valueOf(LocalDate.now())));
		if(expiry.before(Date.valueOf(LocalDate.now())))
		{
			txterror1.setStyle("-fx-text-inner-color: red;");
			txterror1.setText("Exp date cannot be in past.");
			return;
		}
		
		
		
		
		//find booking details
		BookedTickets bookedTicket;
		TicketBookingDAO tB = new TicketBookingDAO();
		bookedTicket = tB.currentBooking();
		tB.close();
		
		bookingId = bookedTicket.getBooking_id();
		eventId = bookedTicket.getEvent_id();
		Fare = bookedTicket.getBooking_amt();
		passNum = bookedTicket.getPass_num();
		ticketQty = bookedTicket.getTicket_qty();
        
		//find event name
		Event et;
		try{
		EventDAO etb = new EventDAO();
		et = etb.findByEventID(eventId);
		  
		etb.close();
		
		String newLine = System.getProperty("line.separator");
		
		
		StringBuilder fieldContent = new StringBuilder(""); 
		fieldContent.append("Payment Successful!" + newLine + "Here are your Ticket Details:" + newLine + "Booking ID - " + bookingId + newLine +
	                          "Event Title - " + et.getEvent_title() + newLine + "Pass Number - "+ passNum + newLine + "Tickets - " + ticketQty + newLine + "Total Fare - $" + Fare);
		txtticketdetails.setText(fieldContent.toString());
		
		hypHome.setDisable(false);
		
	}catch (ClassNotFoundException | SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
		
	}

}
