function pswFind() { 
	 	var pswFind = document.pswFindscreen;

	 	if (pswFind.userId.value.length < 1) {
		  alert("아이디를 입력해주세요");
		  return;
		 }
		 if (pswFind.userEmail.value.length < 1) {
			  alert("이메일을 정확하게 입력해주세요");
			  return;
		 }
		 if(confirm_code == "")
		{
			alert("인증코드 발송을 클릭해주세요");
			return;
		}
		confirmCheck();
}

$(document).ready(function() {
    $('button[id=Btn2]').prop('disabled', true);
    $('input[id=userEmail]').on('input', function() {
    	$('button[id=Btn2]').prop('disabled', !$(this).val());
    });
  
});

/*function sendConfirm()  {
  	const target = document.getElementById('confirm');
  	target.disabled = false;
  
	var timer;
	var isRunning = false; 
							// 인증번호 발송하고 타이머 함수 실행
	var leftSec = 300, //남은 시간 5분
	display = document.querySelector('#timer');
	// 이미 타이머가 작동중이면 중지
	if (isRunning){
	   clearInterval(timer);
	} else {
    	isRunning = true;
    }
     startTimer(leftSec, display);
}		 

 
function startTimer(count, display) {
        var minutes, seconds;
        timer = setInterval(function () {
        minutes = parseInt(count / 60, 10);
        seconds = parseInt(count % 60, 10);
 
        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;
 
        display.textContent = minutes + ":" + seconds;
 
        // 타이머 끝
        if (--count < 0) {
	     clearInterval(timer);
	     display.textContent = "";
	     isRunning = false;
        }
    }, 1000);
}*/