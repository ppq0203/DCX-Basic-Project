function submitCheck()
{
	if(id_overlap!=0)
	{
		alert("아이디 중복확인을 해주세요");
		return;
	}
	if(mail_overlap!=0)
	{
		alert("이메일 인증을 진행해주세요");
		return;
	}
	if($('#userPw').val() != $('#userPwCheck').val())
	{
		alert("비밀번호를 확인해주세요");
		return;
	}
	confirmCheck();
}