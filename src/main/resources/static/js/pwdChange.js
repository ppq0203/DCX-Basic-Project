function pwdChangeSubmit()
{	var frmFind = document.cpwd;
	if(frmFind.userPw.value.length < 1 )
	{
		alert("비밀번호를 입력해주세요");
		return;
	}	
	if($('#userPw').val() != $('#userPwCheck').val())
	{
		alert("비밀번호를 확인해주세요");
		return;
	}
	$('.submitForm').submit();
}