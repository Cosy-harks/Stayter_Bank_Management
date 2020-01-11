/**
 * Test login credentials for requirements
 */

function credentials(){
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var b = true;
	if(!username.match(/.{4,}/)){
		document.getElementById("userError").innerText="username must be 4+ characters";
		b = false;
	}
	if(!password.match(/.{6,}/)){
		document.getElementById("passError").innerText="password must be 6+ characters";
		b = false;
	}
	if(b){
		var xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				var data = xhr.responseText;
				alert(data);
			}
		}
		xhr.open('POST', 'loginServlet', true);
		xhr.send();
	}
	
	return b;
}
