/*function submitCheck() { 
	
	var frmFind = document.frm;
	if(frmFind.userPw.value.length < 1 )
	{
		alert("비밀번호를 입력해주세요");
		return;
	}

	 confirmCheck();
}*/

//버튼 활성화
$(document).ready(function() {
    $('button[id=change-btn]').prop('disabled', true);
    $('input[id=userPw]').on('input', function() {
    	$('button[id=change-btn]').prop('disabled', !$(this).val());
    });
  
});


function submitCheck() {
	var frmFind = document.cpwd;
	if(frmFind.userPw.value.length < 1 )
	{
		alert("비밀번호를 입력해주세요");
		return;
	}
	
	
var result = confirm("회원 탈퇴를 하시겠습니까?");
        if(result) {
            alert("탈퇴 처리되었습니다.");
        } else {
            alert("탈퇴 취소되었습니다.");
        }
       	

$('.submitForm').submit();

}        


