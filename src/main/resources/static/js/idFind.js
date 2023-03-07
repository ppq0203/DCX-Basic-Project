function idFind() { 
	 	var idFind = document.idFindscreen;
	 	
		 if (idFind.userEmail.value.length < 1) {
			  alert("이메일을 정확하게 입력해주세요");
			  return;
		 }
		 confirmCheck(); 
	 }