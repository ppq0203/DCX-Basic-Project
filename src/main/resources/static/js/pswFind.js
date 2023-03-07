function pswFind() { 
	 	var pswFind = document.pswFindscreen;

	 	if (pswFind.userId.value.length < 1) {
		  alert("아이디를 입력해주세요");
		  return;
		 }
		 if (pswFind.userEmail.value.length < 1) {
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

function sendConfirm()  {
  const target = document.getElementById('confirm');
  target.disabled = false;
}		 