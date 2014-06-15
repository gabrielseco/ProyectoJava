/**
 * 
 */
function random_character() {
	    var chars = "0123456789abcdefghijklmnopqurstuvwxyzABCDEFGHIJKLMNOPQURSTUVWXYZ";
	    return chars.substr( Math.floor(Math.random() * 62), 1);
	}
$("#codigoPostal").focus(function(){
	$(this).attr("type","text");
	$(this).attr("maxlength","5");
});
$("#enviar").click(function(){

	var str = "";
    for (var i = 0; i < 32; i++){
        str += random_character();
    }
   
	localStorage.setItem("clave", str);
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
	var password1=$("#password1").val();
	var password2=$("#password2").val();
	if(select==="Selecciona"){
		$("#provinciasIncorrectas").remove();
		var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4>";
		html+="<h4 id='provinciasIncorrectas'>Selecciona una provincia</h4>";
		if(password1.length==0||password2.length==0){
			html+="<h4 id='passVacias'>Las passwords están vacías</h4>";
		}
		if(password1!==password2){
			$("#passVacias").remove();
			$("#passIncorrectas").remove();
			html+="<h4 id='passIncorrectas'>Las passwords son incorrectas</h4>";
			$("#password1").val("");
			$("#password2").val("");
		}
		html+="</div>";
		localStorage.setItem("erroresFormulario", html);
		location.href='errorFormulario.html';
		return false;
	}
	else if(password1.length==0||password2.length==0){
		$("#dangerAlert").remove();
		var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4>";
		html+="<h4 id='passVacias'>Las passwords están vacías</h4>";
		html+="</div>";
		localStorage.setItem("erroresFormulario", html);
		location.href="errorFormulario.html";
		$("#password1").val("");
		$("#password2").val("");
		return false;
	}
	else if(password1!==password2){
		$("#dangerAlert").remove();
		var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4>";
		html+="<h4 id='contraseñasIncorrectas'>Las passwords son incorrectas</h4>";
		html+="</div>";
		localStorage.setItem("erroresFormulario", html);
		location.href="errorFormulario.html";
		$("#password1").val("");
		$("#password2").val("");
		return false;
	}
});