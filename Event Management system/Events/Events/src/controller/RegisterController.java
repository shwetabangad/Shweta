package controller;

import application.Main;
import dao.RegisterDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class RegisterController {

	@FXML
	private Hyperlink hyphome;
	
	//private static User user;
	@FXML
	private TextField txtname;
	
	@FXML
	private TextField txtemail;
	
	@FXML
	private TextField txtloginname;
	
	@FXML
	private TextField txtloginpass;
	
	@FXML
	private TextField txterror;
	
	@FXML
	private TextField txtrole;
	
	@FXML
	private RadioButton adminRadio;
	
	@FXML 
	private RadioButton eventManagerRadio;
		

	public void signup(){
		txterror.setText("");
		String name = this.txtname.getText();
		String email = this.txtemail.getText();
		String loginname = this.txtloginname.getText();
		String loginpass = this .txtloginpass.getText();
		//String role = this.txtrole.getText();
		
		//Validations
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
		
		 String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      Boolean b = email.matches(EMAIL_REGEX);
	      
	      if (b==false)
	      {txterror.setStyle("-fx-text-inner-color: red;");
	    	  txterror.setText("enter valid email");
				return;
	      }
		
		if(loginname == null || loginname.trim().equals("")) {
			txterror.setStyle("-fx-text-inner-color: red;");
			txterror.setText("Login Name Cannot be empty or spaces");
			return;
		}
		
		if(loginpass == null || loginpass.trim().equals("")) {
			txterror.setStyle("-fx-text-inner-color: red;");
			txterror.setText("Login Password Cannot be empty or spaces");
			return;
		}
		
		int i;
		String roleA = "AD";
		String roleE = "EM";
		RegisterDAO l = new RegisterDAO();
		if (adminRadio.isSelected()) {
		i = l.createuser(name,loginname,loginpass,roleA,email);
		}
		else
		{
			i = l.createuser(name,loginname,loginpass,roleE,email);
		}
		l.close();
		//If user not found
		if(i == 0) {
			txterror.setText("Username cannot be created");
			return;
		}
		else {
			txterror.setText("User successfully created");
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
		
}
