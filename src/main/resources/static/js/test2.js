function checklogin(){        
	var login = {
    bind: function() {
        $("#login_btn").on('click', function(){
           login.signin();
        });
    },
    signin: function() {
        $.ajax({
           url: '/login',
           type: 'POST',
           data: {
               userId: $("#userId").val(),
               userPw: $("#userPw").val()
            },
           success: function(cnt){
               if(cnt == 0){
				   $('.id_ok').css("display","inline-block"); 
                   $('.id_already').css("display", "none");
                   alert("로그인 되었습니다.");
               } else {
				   $('.id_already').css("display","inline-block");
                   $('.id_ok').css("display", "none");
                   alert("다시 입력해주세요");
                   $("#userId").val();
                   $("#userPw").val();                                    
               }
            },
            error:function(){
            alert("에러입니다"); 
      	  }
   	 });
  }
}
};

function checklogin() {

    if($.trim($('#userId').val()) == '') {
        alert("아이디를 입력해주세요."); 
        return false;
    }
    if($.trim($('#userPw').val()) == '') {
        alert("비밀번호를 입력해주세요.");
        return false;
    }
}



function checkjoinUser() {

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

function checkidFind() {
		if (idFind.userName.value == "") {
			alert("이름을 입력해 주세요.");
			idFind.userName.focus();
			return;
		}
		
		if (idFind.userEmail.value == "") {
			alert("이메일을 입력해 주세요.");
			idFind.userEmail.focus();
			return;
		}			
	}
	
	