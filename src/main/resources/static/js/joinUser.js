function submitCheck() { 
	
	var frmFind = document.frm;
	if(frmFind.userId.value.length < 1 )
	{
		alert("아이디를 입력해주세요");
		return;
	}
	if(frmFind.userId.value.length > 20 )
	{
		alert("아이디를 20자 이내 입력해주세요");
		return;
	}	
	/*if(frmFind.userId.value.length>1) {
		button.disabled = false; // 활성화
	}
	else {
		button.disabled = true; // 비활성화
	}*/

	if(id_overlap!=0)
	{
		alert("아이디 중복확인을 해주세요");
		return;
	}
	if(frmFind.userPw.value.length < 1 )
	{
		alert("비밀번호를 입력해주세요");
		return;
	}
	if(frmFind.userPw.value.length > 20 )
	{
		alert("비밀번호를 20자 이내 입력해주세요");
		return;
	} 

	if($('#userPw').val() != $('#userPwCheck').val())
	{
		alert("비밀번호를 확인해주세요");
		return;
	}
	if(frmFind.userName.value.length < 1 )
	{
		alert("이름을 입력해주세요");
		return;
	}
	if(frmFind.userEmail.value.length < 1 )
	{
		alert("이메일을 입력해주세요");
		return;
	}	
	if(mail_overlap!=0)
	{
		alert("이메일 인증을 진행해주세요");
		return;
	}
	
	if(frmFind.userPhone.value.length > 12 )
	{
		alert("휴대폰 번호를 입력해주세요");
		return;
	}
	if(frmFind.userAddress.value.length < 1 )
	{
		alert("주소를 입력해주세요");
		return;
	}
	 confirmCheck();
}

$(document).ready(function() {
    $('button').prop('disabled', true);
    $('input[type=text]').on('input', function() {
        $('button').prop('disabled', !$(this).val());
    });
});

/*document.getElementById('userId').addEventListener('input', function(event) {
    document.getElementById('Btn').disabled = !this.value;
}, false);*/



/*function Btn() {
	if($('#userID') = "") {
		button.disabled = false; // 활성화
	}
	else {
		button.disabled = true; // 비활성화
	}
}*/

/*$(function() {
	$("#userId").on('input', function(){
		if($("#userId").val() =='')
			$("#Btn").arr("disabled", true);
		else
			$("#Btn").arr("disabled", false);
		});
	
})*/

	
