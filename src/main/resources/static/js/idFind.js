// 인증 버튼 활성화
//  document.getElementById("completion")
//  document.getElementById("completion").disabled = false;

function idFind() { 
	 	var idFind = document.idFindscreen;
	 	
		 if (idFind.userEmail.value.length < 1) {
			  alert("이메일을 정확하게 입력해주세요");
			  return;
		 }		 
		 if(confirm_code == "")
		{
			alert("인증코드 발송을 클릭해주세요");
			return;
		}		
		 confirmCheck(); 
	 }
