function idFind() { 
	 	var idFind = document.idFindscreen;

	 	if (idFind.userName.value.length < 1) {
		  alert("이름을 입력해주세요");
		  return;
		 }

		 if (idFind.userEmail.value.length != 30) {
			  alert("이메일을 정확하게 입력해주세요");
			  return;
		 }
		 if (idFind.userEmail.value.length != 30) {
			  alert("핸드폰번호를 정확하게 입력해주세요");
			  return;
		 }

	 idFind.method = "post";
	 idFind.submit();  
	 }