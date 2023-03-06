	var userPw = document.getElementById("userPw")
	, confirm_userPW = document.getElementById("confirm_userPw");
	
function passwordChange() {	 
	if(userPw.value != confirm_userPw.value) {
		confirm_userPw.setCustomValidity("비밀번호 불일치");
	}
	else {
		confirm_userPw.setCustomValidity('');
	}
}

userPW.onchange = validatePassword;
confirm_userPW.onkeyup = validatePassword;
	
