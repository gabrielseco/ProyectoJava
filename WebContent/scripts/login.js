/**
 * 
 */

function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function random_character() {
	    var chars = "0123456789abcdefghijklmnopqurstuvwxyzABCDEFGHIJKLMNOPQURSTUVWXYZ";
	    return chars.substr( Math.floor(Math.random() * 62), 1);
	}

$("#iniciarSesion").on('click',function(){
	var str = "";
    for (var i = 0; i < 32; i++){
        str += random_character();
    }
	sessionStorage.setItem("clave", str);
});


var errorUser=getParameterByName('error');
if(errorUser > ""){
	var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>El usuario o la password son incorrectos</h4></div>";
	$(".alert").remove();
	$("h2").before(html);
}