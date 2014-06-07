/**
 * 
 */
if($(".container").attr('data-security','user')){
	if(localStorage.getItem("clave")!=null){
		var clave=localStorage.getItem("clave");
		localStorage.removeItem("clave");
		sessionStorage.setItem("clave", clave);
	}
}