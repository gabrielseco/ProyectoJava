/**
 * 
 */
if($(".navbar").attr('data-security','user')){
	if(localStorage.getItem("clave")==null && sessionStorage.getItem("clave")==null){
		location.href='login.html';
	}
	
	if(localStorage.getItem("clave")!=null){
		var clave=localStorage.getItem("clave");
		localStorage.removeItem("clave");
		sessionStorage.setItem("clave", clave);
	}
	else if(sessionStorage.getItem("clave")== null){
		location.href='../index.html';
	}
		
}


$(".cerrarSesion").on('click',function(){
	sessionStorage.clear();
	location.href='../index.html';
});