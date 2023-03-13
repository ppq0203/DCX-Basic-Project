function isLoginCheck()
{
	$.ajax({
	    url:'./isLoginCheck', //Controller에서 요청 받을 주소
	    type:'post', //POST 방식으로 전달
	    success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
	        if(cnt == 1)
	        	isLogin();
	        else
	        	isLogout();
	    },
	    error:function(){
	        alert("에러입니다");
	    }
	});
}

function isLogin() {
	$(".loginLink").css("display", "none");
	$(".logoutLink").css("display", "inline-block");
}

function isLogout() {
	$(".loginLink").css("display", "inline=block");
	$(".logoutLink").css("display", "none");
}

