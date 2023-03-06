function includeHTML() {
    var z, i, elmnt, file, xhttp;
    z = document.getElementsByTagName("*");
    for (i = 0; i < z.length; i++) {
         elmnt = z[i];
    file = elmnt.getAttribute("include-html");
    if(file) {
       xhttp = new XMLHttpRequest();
       xhttp.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
          elmnt.innerHTML = this.responseText; 
//			if (this.status == 200)	{elmnt.innerHTML = this.responseText;} //200이면 성공
//			if (this.status == 404) {elmnt.innerHTML = "Page not found.";} //404면 not found 
            elmnt.removeAttribute("include-html");
            includeHTML();
             }
        }
        xhttp.open("GET", file, true);
        xhttp.send();
        return;
        }
     }
}

