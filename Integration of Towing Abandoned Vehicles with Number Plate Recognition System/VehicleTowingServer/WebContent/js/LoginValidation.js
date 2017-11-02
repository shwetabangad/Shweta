function validateLogin(form) {

	// alert(form.username);

	if (form.username.value == "") {
		alert("Error: Please Enter Username !");
		form.username.focus();
		return false;
	}

	// %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

	if (form.password.value == "") {
		alert("Error: Please Enter Password !");
		form.password.focus();
		return false;
	}

	return true;
}
