$('#phoneCerti').click(function(){
    $('#timer').show();
      //timer 타이머      
	var time = 179;       
	var min = " ";      
	var sec = " ";          
	var x = setInterval
	(function(){       
 		min = parseInt(time/60);      
   		sec = time%60;          
   	if (min < 10) {
		min = "0"+ min;
	} 
    if (sec < 10) {
	    sec = "0"+ sec;
	  } // min,sec 10미만 시 앞에 0 붙이기            
	  document.getElementById("timer").innerHTML = min + ":" + sec + ""; // min : sec       
	   time--; // -1초씩 차감
	if(time < 0 ){          
		clearInterval(x);          
		 $('#resend').show();       
  	}
  	      },1000);  
  	
 });










var intervalVar = undefined;
var timeSecond = undefined;
var authenticationCode = undefined;

function doTimer() {
	$('#btn').prop('value', '인증번호 재전송');
				
				authenticationCode = '123456';
				$('#authenticationcode').prop('value', authenticationCode);
			
				timeSecond = 30;
				$('#timer').prop('value', getTimeString(timeSecond));

				if (intervalVar != undefined) {
					clearInterval(intervalVar);
				}

				intervalVar = setInterval(function() {
				   if (timeSecond != 0) {
					   timeSecond = timeSecond - 1;
					   $('#timer').prop('value', getTimeString(timeSecond));
				   } else {
					   clearInterval(intervalVar);
					   intervalVar = false;
				   }
			   }, 1000);
			}

			function getTimeString(second) {
				var minute = '' + (Math.floor(second / 60));
				var dividedSec = second % 60;
				if (dividedSec < 10) {
					dividedSec = '0' + dividedSec;
				}
				// ex) 3:00 -> 3분
				return minute + ":" + dividedSec;
			}
			
			function doCodeCheck() {
				if (!$('#typingcode').prop('value')) {
                    alert('인증번호를 입력하세요.');
                    return;
                }
                if (timeSecond == 0 || intervalVar == false) {
                    alert('인증번호 유효시간이 초과하였습니다. 인증번호를 재전송 해주세요.');
                    return;
                }
			
				if ($('#authenticationcode').prop('value') == $('#typingcode').prop('value')) {
					alert('인증번호가 동일합니다.');
				} else {
					alert('인증번호가 동일하지 않습니다.');
				}
			}
/*	</script>
	</head>
<body>
Timer <input type="text" id="timer" name="timer" value=""/>&nbsp;&nbsp;&nbsp;<input type="button" id="btn" name="btn" value="인증번호 전송" onclick="doTimer()"/><br/>
인증번호 <input type="text" maxlength="6" id="authenticationcode" name="authenticationcode" value="" readonly/><br/>
인증번호 입력 <input type="text" id="typingcode" name="typingcode" value=""/>
<input type="button" value="확인" onclick="doCodeCheck()"/>
</body>
</html>*/