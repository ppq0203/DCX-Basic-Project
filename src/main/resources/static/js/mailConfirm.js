var mail_overlap = 2;	//이메일 중복체크를 진행하였는지 확인하기 위한 변수 0이 되면 진행가능
var oldValMail = "";	//기존 이메일 저장해둘 변수
var confirm_code = "";	//인증 코드를 저장해둘 변수

// userEmail의 input내용이 변경되면 실행하는 함수
$(document).ready(function() {
	$("#userEmail").on("propertychange change keyup paste input", function() {
	    var currentVal = $(this).val();	// 변경된 아이디 변수
	    if(currentVal == oldValMail) {	//	변경하기전과 동일하면
	        return;	//아래코드를 실행하지 않음
	    }
	    oldValMail = currentVal;	// 변경된 값을 기존값으로 저장
	    mail_overlap = 2;	// id가 변경되었으므로 아이디 중복체크 다시해야함.
	});
});


function sendConfirm(){
	var mail = $('#userEmail').val(); //id값이 "id"인 입력란의 값을 저장
	$.ajax({
	    url:'./sendConfirm', //Controller에서 요청 받을 주소
	    type:'post', //POST 방식으로 전달
	    data:{mail:mail},
	    success:function(code){ //컨트롤러에서 넘어온 cnt값을 받는다 
	        confirm_code = code;
			console.log(confirm_code);
			confirmTimer();
	    },
	    error:function(){
	        alert("에러입니다");
	    }
	});
};

function checkMail()
{
	var mail = $('#userEmail').val(); //id값이 "id"인 입력란의 값을 저장
	console.log(mail);
	$.ajax({
	    url:'./mailCheck', //Controller에서 요청 받을 주소
	    type:'post', //POST 방식으로 전달
	    data:{mail:mail},
	    success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
	        if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
	            $('.mail_ok').css("display","inline-block"); 
	            $('.mail_already').css("display", "none");
	            $('#confirm').attr("disabled", false);	//confirm에 값을 입력할수 있도록 변경
	            sendConfirm();
	        } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
	            $('.mail_already').css("display","inline-block");
	            $('.mail_ok').css("display", "none");
	            alert("이메일을 다시 입력해주세요");
	            $('#userEmail').val('');
	        }
	        mail_overlap = cnt;
	    },
	    error:function(){
	        alert("에러입니다");
	    }
	});
}

function confirmCheck()
{
	if (confirm_code == $('#confirm').val())
	{
		$('.confirm').submit();		
	}
	else
	{
		alert("인증번호가 잘못되었습니다.");
		$('#confirm').val('');
	}
}