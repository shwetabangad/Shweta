package model;


public class User {

	private Integer user_id;
	private String user_name;
	private String login_name;
	private String login_password;
	private String role_code;
	private String email;
	
	public User() {
		//Empty Default constructor
	}
	
	public User(Integer user_id, String user_name, String login_name, String login_password, String role_code, String email) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.login_name = login_name;
		this.login_password = login_password;
		this.role_code = role_code;
		this.setEmail(email);
	}

	public Integer getId() {
		return user_id;
	}

	public void setId(Integer id) {
		this.user_id = id;
	}

	public String getUsername() {
		return user_name;
	}

	public void setUsername(String username) {
		this.user_name = username;
	}

	public String getLoginname() {
		return login_name;
	}
	
	public void setLoginname(String loginname) {
		this.login_name = loginname;
	}
	
	public String getloginPassword() {
		return login_password;
	}

	public void setLoginPassword(String loginpassword) {
		this.login_password = loginpassword;
	}

	public String getrole() {
		return role_code;
	}

	public void setrole(String role) {
		this.role_code = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}