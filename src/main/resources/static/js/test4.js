
function login() {
	var id = document.getElementsByName('#userID');
	var pw = document.getElementsByName('#userPw');
	
	if(id.value == "" || pw.value == "") {
		alert("로그인을 할 수 없습니다.")
	}
	else {
		location.href = 'mainPage.html';
	}
}
	