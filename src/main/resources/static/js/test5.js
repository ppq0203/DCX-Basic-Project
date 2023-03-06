
function joinUser() {
	
    if($.trim($('#userId').val()) == '') {
        alert("아이디를 입력해주세요."); 
        return false;
    }
    if($.trim($('#userPw').val()) == '') {
        alert("비밀번호를 입력해주세요.");
        return false;
    }
    if($.trim($('#userName').val()) == '') {
        alert("이름을 입력해주세요.");
        return false;
    }
    if($.trim($('#userEmail').val()) == '') {
        alert("이메일을 입력해주세요.");
        return false;
    }
    if($.trim($('#userPhone').val()) == '') {
        alert("휴대폰 번호를 입력해주세요.");
        return false;
    }
    if($.trim($('#userAddress').val()) == '') {
        alert("주소를 입력해주세요.");
        return false;
    }
     
    if(confirm("회원가입을 하시겠습니까?")){
        alert("회원가입이 완료되었습니다.");
         $("joinUSer_btn").submit();    
    }
}
