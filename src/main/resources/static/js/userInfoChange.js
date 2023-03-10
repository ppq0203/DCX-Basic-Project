
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
		 var result = confirm("회원정보를 변경 하시겠습니까?");
        if(result) {
            alert("회원정보 변경 되었습니다.");
        } else {
            alert("회원정보 변경 취소되었습니다.");
        }
		confirmCheck();
	 }