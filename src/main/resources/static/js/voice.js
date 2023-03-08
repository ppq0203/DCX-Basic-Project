var voices = [];
var voiceState = 0;

function finishVoice()
{
	voiceState = 0;
}

function setVoiceList() {
  	voices = window.speechSynthesis.getVoices();
}

setVoiceList();
if (window.speechSynthesis.onvoiceschanged !== undefined) {
	window.speechSynthesis.onvoiceschanged = setVoiceList;
}

function speech(txt, callback) {
	console.log("speech");
  	if(!window.speechSynthesis) {
    	alert("음성 재생을 지원하지 않는 브라우저입니다. 크롬, 파이어폭스 등의 최신 브라우저를 이용하세요");
    	return;
  	}
	var lang = 'ko-KR';
	var utterThis = new SpeechSynthesisUtterance(txt);
	utterThis.onend = function (event) {
    	console.log('end', event);
    	callback();
	};
  	utterThis.onerror = function(event) {
    	console.log('error', event);
  	};
  	var voiceFound = false;
  	for(var i = 0; i < voices.length ; i++) {
    	if(voices[i].lang.indexOf(lang) >= 0 || voices[i].lang.indexOf(lang.replace('-', '_')) >= 0) {
      		utterThis.voice = voices[i];
      		voiceFound = true;
    	}
  	}
  	if(!voiceFound) {
    	alert('voice not found');
    	return;
  	}
  	utterThis.lang = lang;
  	utterThis.pitch = 1;
  	utterThis.rate = 1;  //속도
  	window.speechSynthesis.speak(utterThis);
  	
	console.log("speechend");
}

// document.addEventListener("click", function(e) {
//   var t = e.target;
//   var input = t.previousElementSibling;
//   speech(input.value);
// });

// 페이지가 전부 로드되고 실행될 자바스크립트
$(document).ready(function(){
	$(".voiceExplain").css('display','none');
	
	$(".focusVoice").focus(function(e){
		if(voiceState == 0)
		{
			voiceState = 1;
			console.log("focus");
	        var t = e.target;	//포커스 이벤트가 발생한 해당태그 저장
	        var input = t.previousElementSibling;	//포커스 잡힌 앞의 태그 저장
	        speech(input.innerHTML, finishVoice);
        }
    });
    
	$(".hoverVoice").hover(function(e){
		if(voiceState == 0)
		{
			voiceState = 1;
			console.log("hover");
	        var t = e.target;	//포커스 이벤트가 발생한 해당태그 저장
	        var input = t.previousElementSibling;	//포커스 잡힌 앞의 태그 저장
	        speech(input.innerHTML, finishVoice);
		}
    });
    
    
});

// 여기시부터

/*const span = document.getElementById('voiceExplain');

const result = document.getElementById('result');

div.addEventListener('mousemove', (event) => {
  result.innerHTML+= '<div>mousemove</div>';
});*/