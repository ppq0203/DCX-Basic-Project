function confirmTimer()  {
  	const target = document.getElementById('confirm');
  	target.disabled = false;
  
	var timer;
	var isRunning = false; 
							// 인증번호 발송하고 타이머 함수 실행
	var leftSec = 300, //남은 시간 5분
	display = document.querySelector('#timer');
	// 이미 타이머가 작동중이면 중지
/*	if (isRunning){
	   clearInterval(timer);
	   alert("인증시간이 초과하였습니다. 다시 인증해주시기 바랍니다.");
	   window.close();
       window.opener.location = "/index.do"
	} else {
    	isRunning = true;
    }*/
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
}