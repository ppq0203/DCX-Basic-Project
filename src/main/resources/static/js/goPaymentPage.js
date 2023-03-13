function goPaymentPage(salesNo) {	//salesNo 가 -1인경우 장바구니, 0보다 큰경우 해당상품 즉시구매
    var form = document.createElement("form");

	var obj;
    obj = document.createElement('input');
    obj.setAttribute('type', 'hidden');
    obj.setAttribute('name', 'salesNo');
    console.log(salesNo);
    obj.setAttribute('value', parseInt(salesNo));
    
    form.appendChild(obj);
    form.setAttribute('method', 'post');
    form.setAttribute('action', 'goPaymentPage');
    document.body.appendChild(form);
    form.submit();

}
/*
    var parm = new Array();
    var input = new Array();
    parm.push( ['salesNo', 'some_value1'] );
    parm.push( ['some_key2', 'some_value2'] );
    parm.push( ['some_data', some_data] );


    for (var i = 0; i < parm.length; i++) {
        input[i] = document.createElement("input");
        input[i].setAttribute("type", "hidden");
        input[i].setAttribute('name', parm[i][0]);
        input[i].setAttribute("value", parm[i][1]);
        form.appendChild(input[i]);
    }
    document.body.appendChild(form);
    form.submit();
<a href="#" onclick="javascript:page_move('http://httpbin.org/post', 'foobar');">click</a>
*/