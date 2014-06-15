/**
 * 
 */
if($(".navbar").attr('data-security','user')){
	if(localStorage.getItem("clave")==null && sessionStorage.getItem("clave")==null){
		location.href='../login.html';
	}
	else if(localStorage.getItem("clave")!=null){
		var clave=localStorage.getItem("clave");
		localStorage.removeItem("clave");
		sessionStorage.setItem("clave", clave);
	}
	else if(sessionStorage.getItem("clave")== null){
		location.href='../index.html';
	}
	
}
var idUsuario = getParameterByName("idUs");
if(idUsuario !== ""){
	sessionStorage.setItem("idUsuario",getParameterByName("idUs"));
}

$(".cerrarSesion").on('click',function(){
	sessionStorage.clear();
	location.href='../index.html';
});

var url = location.href;

url = url.split("/");


function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}