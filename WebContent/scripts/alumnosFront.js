/**
 * 
 */
$("#enviar").click(function(){
	var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < 5; i++ ){
        text += possible.charAt(Math.floor(Math.random() * possible.length));
    }
	localStorage.setItem("clave", text);
	var almacenar="";
	almacenar+=$("#usuario").val();
	almacenar+=","+$("#Nombre").val();
	almacenar+=","+$("#apellidos").val();
	almacenar+=","+$("#telefono").val();
	almacenar+=","+$("#Email").val();
	almacenar+=","+$("#calle").val();
	almacenar+=","+$("#numero").val();
	almacenar+=","+$("#piso").val();
	almacenar+=","+$("#codigoPostal").val();
	almacenar+=","+$("#localidad").val();
	almacenar+=","+$("#myselect").val();
	localStorage.setItem("almacenUsuario", almacenar);
	var select = $("#myselect option:selected").text();
	if(select==="Selecciona"){
		$("#provinciasIncorrectas").remove();
		var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4>";
		html+="<h4 id='provinciasIncorrectas'>Selecciona una provincia</h4>";
		if(password1.length==0||password2.length==0){
			html+="<h4 id='contrase�asVacias'>Las contrase�as est�n vac�as</h4>";
		}
		if(password1!==password2){
			$("#contrase�asVacias").remove();
			$("#contrase�asIncorrectas").remove();
			html+="<h4 id='contrase�asIncorrectas'>Las contrase�as son incorrectas</h4>";
			$("#contrase�a1").val("");
			$("#contrase�a2").val("");
		}
		html+="</div>";
		localStorage.setItem("erroresFormulario", html);
		location.href='errorFormulario.html';
		return false;
	}
	else if(password1.length==0||password2.length==0){
		$("#dangerAlert").remove();
		var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4>";
		html+="<h4 id='contrase�asVacias'>Las contrase�as est�n vac�as</h4>";
		html+="</div>";
		localStorage.setItem("erroresFormulario", html);
		location.href="errorFormulario.html";
		$("#contrase�a1").val("");
		$("#contrase�a2").val("");
		return false;
	}
	else if(password1!==password2){
		$("#dangerAlert").remove();
		var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4>";
		html+="<h4 id='contrase�asIncorrectas'>Las contrase�as son incorrectas</h4>";
		html+="</div>";
		localStorage.setItem("erroresFormulario", html);
		location.href="errorFormulario.html";
		$("#contrase�a1").val("");
		$("#contrase�a2").val("");
		return false;
	}
});