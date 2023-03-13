function addBasket(){
	console.log(salesNo);
	var amount = 1;
	$.ajax({
	    url:'./addBasket', //Controller에서 요청 받을 주소
	    type:'post', //POST 방식으로 전달
	    data:{salesNo:salesNo, amount:amount},
	    success:function(code){ //컨트롤러에서 넘어온 cnt값을 받는다 
			console.log(code);
	    },
	    error:function(){
	        alert("에러입니다");
	    }
	});
};