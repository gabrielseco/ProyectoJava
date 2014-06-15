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

var error = getParameterByName("confirmacion");

if(error === "YES"){
	var html="<div class='alert alert-warning alert-block fade in container' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Se ha inscrito correctamente en el curso.Le hemos enviado un correo</h4>";
	$("#dangerAlert").remove();
	$(".article").prepend(html);
}
else if(error ==="NO"){
	var html="<div class='alert alert-warning alert-block fade in container' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Ya está inscrito en este curso.</h4>";
	$("#dangerAlert").remove();
	$(".article").prepend(html);

}

function getParameterByName(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}