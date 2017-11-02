package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import dao.CustomerDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CustomerController implements Initializable {

	public float totalfare;
	public int totaltickets;
	
	@FXML
	private TextField txtcname;
	
	@FXML
	private TextField txtcemail;
	
	@FXML
	private TextField txtcphn;
	
	@FXML
	private TextField txtticketcount;
	
	@FXML
	private TextField txttotalamt;
	
	@FXML
	private TextField txterror;
	
	
	public void performtotal(int chldcount,float chldfare, int adultcount, float adultfare, int tckcount){
		
		this.totalfare = ((chldcount * chldfare) + (adultcount * adultfare));
		this.totaltickets = tckcount;
						
	}	
	
	
	public void reset(){
		txtcname.setText("");
		txtcemail.setText("");
		txtcphn.setText("");
		txtticketcount.setText("");
		txttotalamt.setText("");
	}
	
		
	public void proceed(){
		AnchorPane root;
		//creating user
		String name = this.txtcname.getText();
		String email = this.txtcemail.getText();
		String phn = this.txtcphn.getText();
				
		//Validations
		if(name == null || name.trim().equals("")) {
				txterror.setText("Name Cannot be empty or spaces");
				return;
			}
		
		if(email == null || email.trim().equals("")) {
			txterror.setText("Email Cannot be empty or spaces");
			return;
		}
		
		
		int i;
		CustomerDAO l = new CustomerDAO();
		i = l.createcustomer(name,email,phn);
		l.close();
		//If user not found
		if(i == 0) {
			txterror.setText("Customer cannot be created");
			return;
		}
		else {
			txterror.setText("Customer successfully created");
			
		
				
		/// passing parameters to next page
		try{
		
		FXMLLoader loader = new FXMLLoader(); // Creating a the FXMLLoader
        loader.setLocation(getClass().getResource("/Views/MakePayment.fxml")); // Setting the proper FXML file.
        loader.load(); // Loading said FXML.
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("/Views/MakePayment.fxml"));
        
        Scene scene = new Scene(root);
		Main.stage.setScene(scene);
		Main.stage.setTitle("Payment");
		Main.stage.show();
		 

    	}
	    catch(Exception e) {		
		System.out.println("Error occured while inflating view: " + e);
	     }
		}
		
		
	}

	//@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		txttotalamt.setPromptText(Float.toString(this.totalfare));
		txtticketcount.setPromptText(Float.toString(this.totaltickets));
	}
	
	
}
