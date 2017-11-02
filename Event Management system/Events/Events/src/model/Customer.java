package model;


public class Customer {
	
	private Integer customer_id;
	private String customer_name;
	private String phn;
	private String email;
	
	
	public Customer(){
		
	}
	
	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getPhn() {
		return phn;
	}

	public void setPhn(String phn) {
		this.phn = phn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer(String name, String phn, String email){
		this.customer_name = name;
		this.phn = phn;
		this.email = email;
		
	}

}
