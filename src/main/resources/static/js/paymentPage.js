/**
 * 
 */
function showCardForm()
{
	$('.cardForm').css('display', 'block');
}

function paymentCancle()
{
	location.replace("/showBasket");	
}

function cardCancle()
{
	$('.cardForm').css('display', 'none');
}

function paymentSubmit(size, salesNo, amount)
{
	var form = document.createElement("form");

	var obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'salesNo');
    console.log(salesNo);
    if(size > 1)
    	salesNo = -1;
    obj.setAttribute('value', salesNo);
    form.appendChild(obj);
    
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'amount');
    console.log(amount);
    obj.setAttribute('value', amount);
    form.appendChild(obj);
    
    form.setAttribute('method', 'post');
    form.setAttribute('action', 'postPaymentPage');
    document.body.appendChild(form);
    form.submit();
}