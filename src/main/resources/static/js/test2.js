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
	}		
}


function idFind() { 
	 	var idFind = document.idFindscreen;
		 if (idFind.userEmail.value.length != 30) {
			  alert("이메일을 정확하게 입력해주세요");
			  return;
		 }

	 idFind.method = "post";
	 idFind.submit();  
	 }
	 
function pswFind() { 
	 	var pswFind = document.pswFindscreen;

	 	if (pswFind.userID.value.length < 1) {
		  alert("아이디를 입력해주세요");
		  return;
		 }

		 if (pswFind.userEmail.value.length != 30) {
			  alert("이메일을 정확하게 입력해주세요");
			  return;
		 }
		 if (pswFind.userEmail.value.length != 30) {
			  alert("핸드폰번호를 정확하게 입력해주세요");
			  return;
		 }
	 pswFind.method = "post";
	 pswFind.submit();  
	 }