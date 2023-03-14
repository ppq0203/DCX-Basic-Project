/**
 * 
 */
function productClick(salesNo)
{
	var form = document.createElement("form");
	var obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'salesNo');
    obj.setAttribute('value', parseInt(salesNo));
    console.log(salesNo);
    
    form.appendChild(obj);
    form.setAttribute('method', 'get');
    form.setAttribute('action', 'showprod');
    document.body.appendChild(form);
    form.submit();
}