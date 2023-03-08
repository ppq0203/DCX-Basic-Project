var start = null;
function checkCompletion() {
	start = 300;
	timer();
}

function $ComTimer(){
    //prototype extend
}

$ComTimer.prototype = {
    comSecond : ""
    , fnCallback : function(){}
    , timer : ""
    , domId : ""
    , fnTimer : function(){
        var m = Math.floor(this.comSecond / 60) + "분 " + (this.comSecond % 60) + "초";	// 남은 시간 계산
        this.comSecond--;					// 1초씩 감소
        console.log(m);
        this.domId.innerText = m;
        if (this.comSecond < 0) {			// 시간이 종료 되었으면..
            clearInterval(this.timer);		// 타이머 해제
            alert("인증시간이 초과하였습니다. 다시 인증해주시기 바랍니다.");
            window.close();
            window.opener.location = "/index.do"
        }
    }
    ,fnStop : function(){
        clearInterval(this.timer);
    }
}

var AuthTimer = new $ComTimer()

AuthTimer.comSecond = 300; // 제한 시간

AuthTimer.fnCallback = function(){alert("다시인증을 시도해주세요.")}; // 제한 시간 만료 메세지

AuthTimer.timer =  setInterval(function(){AuthTimer.fnTimer()},1000); 

AuthTimer.domId = document.getElementById("timer"); 

// 버튼에 온클릭 하고 펑션 넣고 그 펑션에 인터벌 스타트 하고 이럼 
