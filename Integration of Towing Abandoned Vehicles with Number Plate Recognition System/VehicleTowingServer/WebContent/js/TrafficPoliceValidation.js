function validateRegistration(form) {

	//alert("Welcome Traffic Police Registration Validation");

	if (form.firstname.value == "") {
		alert("Error: Firstname cannot be blank!");
		form.firstname.focus();
		return false;
	}
	re = /[0-9]/;
	if (re.test(form.firstname.value)) {
		alert("Error: Firstname should not contain numbers!");
		form.firstname.focus();
		return false;
	}
	re = /[A-Za-z]/;
	if (!re.test(form.firstname.value)) {
		alert("Error: Firstname should not contain other character!");
		form.firstname.focus();
		return false;
	}
	if (form.lastname.value == "") {
		alert("Error: Lastname cannot be blank!");
		form.lastname.focus();
		return false;
	}
	re = /[0-9]/;
	if (re.test(form.lastname.value)) {
		alert("Error: Lastname should not contain numbers!");
		form.lastname.focus();
		return false;
	}
	re = /[A-Za-z]/;
	if (!re.test(form.lastname.value)) {
		alert("Error: Lastname should not contain other character!");
		form.lastname.focus();
		return false;
	}

	if (form.address.value == "") {
		alert("Error: Address cannot be blank!");
		form.address.focus();
		return false;
	}
	re = /[A-Za-z0-9]/;
	if (!re.test(form.address.value)) {
		alert("Error: Address should contain character/numbers!");
		form.address.focus();
		return false;
	}

	if (form.emailid.value == "") {
		alert("Error: Email Id cannot be blank!");
		form.emailid.focus();
		return false;
	}
	re = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
	if (!re.test(form.emailid.value)) {
		alert("Error: Invalid Email Id!");
		form.emailid.focus();
		return false;
	}
	if (form.mobileno.value == "") {
		alert("Error: Contact Number cannot be blank!");
		form.mobileno.focus();
		return false;
	}
	re = /[0-9]/;
	if (!re.test(form.mobileno.value)) {
		alert("Error: Contact Number contain digit!");
		form.mobileno.focus();
		return false;
	}

	re = /^[0-9]{10,12}$/;
	if (!re.test(form.mobileno.value)) {
		alert("Error: Enter 10 or 12 digit Contact Number!");
		form.mobileno.focus();
		return false;
	}
	if (form.username.value == "") {
		alert("Error: Username cannot be blank!");
		form.username.focus();
		return false;
	}
	re = /[0-9]/;
	if (re.test(form.username.value)) {
		alert("Error: Username should not contain numbers!");
		form.username.focus();
		return false;
	}
	re = /[A-Za-z]/;
	if (!re.test(form.username.value)) {
		alert("Error: Username should not contain other character!");
		form.username.focus();
		return false;
	}

	if (form.password.value != "" && form.password.value == form.repassword.value) {

		if (form.password.value.length < 6) {
			alert("Error: Password must contain at least six characters!");
			form.password.focus();
			return false;
		}
		if (form.password.value == form.username.value) {
			alert("Error: Password must be different from Username!");
			form.password.focus();
			return false;
		}
		re = /[0-9]/;
		if (!re.test(form.password.value)) {
			alert("Error: Password must contain at least one number (0-9)!");
			form.password.focus();
			return false;
		}
		re = /[a-z]/;
		if (!re.test(form.password.value)) {
			alert("Error: Password must contain at least one lowercase letter (a-z)!");
			form.password.focus();
			return false;
		}
		re = /[A-Z]/;
		if (!re.test(form.password.value)) {
			alert("Error: Password must contain at least one uppercase letter (A-Z)!");
			form.password.focus();
			return false;
		}
	} else {
		alert("Error: Please check that you've entered and confirmed your Password!");
		form.password.focus();
		return false;
	}
	
	if (form.register_lat.value == "") {
		alert("Error: Register Latitude cannot be blank!");
		form.register_lat.focus();
		return false;
	}
	if (form.register_long.value == "") {
		alert("Error: Register Longitude cannot be blank!");
		form.register_long.focus();
		return false;
	}

	return true;
}
