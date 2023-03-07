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



