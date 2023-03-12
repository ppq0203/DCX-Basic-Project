function totalPriceFix()
{
	var sum = 0;
	console.log(baskets.length);
	for(var i = 0; i < baskets.length; ++i)
	{
		sum += baskets[i].salesDto.salesPrice * baskets[i].amount;
	}
	
	$('.totalPrice').text(sum);
}

function amounChange(my)
{
	var before = my.previousElementSibling;
	console.log(my.value);
	console.log(before.value);
	for(var i = 0; i < baskets.length; ++i)
		if(baskets[i].salesDto.salesNo == before.value)
			baskets[i].amount = my.value;
	$.ajax({
	    url:'./changeBasket', //Controller에서 요청 받을 주소
	    type:'post', //POST 방식으로 전달
	    data:{salesNo:before.value, amount:my.value},
	    success:function(){ //컨트롤러에서 넘어온 cnt값을 받는다 
			console.log("success");
	    },
	    error:function(){
	        alert("에러입니다");
	    }
	});
	totalPriceFix();
}

$('button[type="submit"]').keydown(function() {
  if (event.keyCode === 13) {
    event.preventDefault();
  };
});
/*
document.addEventListener('keydown', function(event) {
  if (event.keyCode === 13) {
    event.preventDefault();
  };
}, true);
*/