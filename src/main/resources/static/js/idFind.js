
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
	 
$(document).ready(function() {
    $('button[id=Btn2]').prop('disabled', true);
    $('input[id=userEmail]').on('input', function() {
    	$('button[id=Btn2]').prop('disabled', !$(this).val());
    });
  
});