var timer;
var isRunning = false;
 
// 인증번호 발송하고 타이머 함수 실행
$("button").on("click", function() {
  var display = $('#timer');
  // 유효시간 설정
  var leftSec = 120;

  // 버튼 클릭 시 시간 연장
  if (isRunning){
    clearInterval(timer);
    display.html("");
    startTimer(leftSec, display);
  }else{
  	startTimer(leftSec, display);
  }
});

/*function sendAuthNum(){
    	// 남은 시간
	var leftSec = 60,
	display = document.querySelector('#timer');
	// 이미 타이머가 작동중이면 중지
	if (isRunning){
	   clearInterval(timer);
	} else {
    	isRunning = true;
    }
     startTimer(leftSec, display);
}
 */
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