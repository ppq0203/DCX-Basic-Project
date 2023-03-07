var start = null;
var mc = null;

function ckHpView2(){
  start = 300;
  timer();
}

function timer(){

clearInterval(mc);

    if(start>0){
        var mim = parseInt( start / 60);
        var sec = start % 60;
var times = document.getElementById("timer");
        times.innerHTML= mim + " : " + sec;
mc = setInterval("timer()", 1000);
    }
else {
        alert ("입력값 입력시간이 경과되었습니다.");
        self.reload();
    }

    start--;

}