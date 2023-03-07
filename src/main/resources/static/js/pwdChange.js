function pwdChangeSubmit()
{
	if($('#userPw').val() != $('#userPwCheck').val())
	{
		alert("비밀번호를 확인해주세요");
		return;
	}
	$('.submitForm').submit();
}