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

function plusBtn(my)
{
	var before = my.previousElementSibling;
	console.log(before.value);
	if(before.value<99)
		before.value++;
	amounChange(before);
}

function minusBtn(my)
{
	var before = my.previousElementSibling.previousElementSibling;
	console.log(before.value);
	if(before.value>1)
		before.value--;
	amounChange(before);
}

function amounChange(my)
{
	var before = my.previousElementSibling;
	var price;
	console.log(my.value);
	console.log(before.value);
	for(var i = 0; i < baskets.length; ++i)
		if(baskets[i].salesDto.salesNo == before.value)
		{
			baskets[i].amount = my.value;
			price = baskets[i].salesDto.salesPrice;
		}
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
	console.log(my.parentElement.parentElement.nextElementSibling);
	my.parentElement.parentElement.nextElementSibling.innerText = price*my.value+'원';
	totalPriceFix();
}

function deleteButton(salesNo)
{
	var form = document.createElement("form");

	var obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'salesNo');
    console.log(salesNo);
    obj.setAttribute('value', parseInt(salesNo));
    
    form.appendChild(obj);
    form.setAttribute('method', 'post');
    form.setAttribute('action', 'basketUpdate');
    document.body.appendChild(form);
    form.submit();
}
/*
document.addEventListener('keydown', function(event) {
  if (event.keyCode === 13) {
    event.preventDefault();
  };
}, true);
*/