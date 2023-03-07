
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