var login_btn = document.getElementById("login_btn")
    login_btn.addEventListener('click', loginFunc)

function loginFunc() {
	var Id = document.getElementById("userId").value;
	var Pw = document.getElementById("userPw").value;
	
	if(!Id) {
		alert("아이디를 입력해주세요.");
		return;
	}
	if(!Pw) {
		alert("비밀번호를 입력해주세요")
		return;
	}		
	$('.submitForm').submit();
}

function userInfoChange() { 
	 	var pswFind = document.pswFindscreen;

	 	if (pswFind.userID.value.length < 1) {
		  alert("아이디를 입력해주세요");
		  return;
		 }
		 if (pswFind.userEmail.value.length < 1) {
			  alert("이메일을 정확하게 입력해주세요");
			  return;
		 }
		confirmCheck();
	 }