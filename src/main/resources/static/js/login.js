var login_btn = document.getElementById("login_btn")
    login_btn.addEventListener('click', loginFunc)

function loginFunc() {
	var Id = document.getElementById("userId").value;
	var Pw = document.getElementById("userPw").value;
	
	if(!Id) {
		alert("아이디를 입력해주세요.");
		return;
	}
	if(!Pw) {
		alert("비밀번호를 입력해주세요")
		return;
	}
	$('.submitForm').submit();
}

$('.submitForm').keypress(function(e){
	if(e.keyCode == 13)
	{
		loginFunc();
	}
});

/*
$(document).on('mouseenter', '.button2', function(e){
		if(voiceState == 0)
		{
			voiceState = 1;
			console.log("hover");
	        var t = e.target;	//포커스 이벤트가 발생한 해당태그 저장
	        var input = t.previousElementSibling;	//포커스 잡힌 앞의 태그 저장
	        speech(input.innerHTML, finishVoice);
		}
});*/
/*
$(document).on('mouseenter', '.button3', function(e){
		if(voiceState == 0)
		{
			voiceState = 1;
			console.log("hover");
	        var t = e.target;	//포커스 이벤트가 발생한 해당태그 저장
	        var input = t.previousElementSibling;	//포커스 잡힌 앞의 태그 저장
	        speech(input.innerHTML, finishVoice);
		}
});

$(document).on('mouseenter', '.button4', function(e){
		if(voiceState == 0)
		{
			voiceState = 1;
			console.log("hover");
	        var t = e.target;	//포커스 이벤트가 발생한 해당태그 저장
	        var input = t.previousElementSibling;	//포커스 잡힌 앞의 태그 저장
	        speech(input.innerHTML, finishVoice);
		}
});

*/
/*const span = document.getElementById('voiceExplain');
const result = document.getElementById('result');

div.addEventListener('mousemove', (event) => {
  result.innerHTML+= '<div>mousemove</div>';
});*/










