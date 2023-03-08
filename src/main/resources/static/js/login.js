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


