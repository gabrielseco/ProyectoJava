/**
 * Errores de formulario en el registro de alumnos
 * CUANDO HACEMOS CLICK EN ENVIAR
 * COMPROBAMOS QUE EL SELECT SEA DISTINTO DE SELECIONA COMPROBAMOS SI
 * LAS CONTRASE�AS ESTAN VACIAS O SI SON DISTINTAS.
 * SI TODO VA BIEN SE LIMPIAN LOS NODOS HTML'S A�ADIDOS
 * A�ADO UN DIV ALERT PARA CUANDO NO HAY USUARIOS
 * LOS DATOS DEL FORMULARIO AL ENVIAR LOS GUARDO EN EL NAVEGADOR POR SI AL DAR ERROR EN EL USUARIO QUE APAREZCAN POBLADOS LOS CAMPOS
 */
	


	$("#enviar").click(function(){
		var almacenar="";
		almacenar+=$("#usuario").val();
		almacenar+=","+$("#nombre").val();
		almacenar+=","+$("#apellidos").val();
		almacenar+=","+$("#telefono").val();
		almacenar+=","+$("#email").val();
		almacenar+=","+$("#calle").val();
		almacenar+=","+$("#numero").val();
		almacenar+=","+$("#piso").val();
		almacenar+=","+$("#codigoP").val();
		almacenar+=","+$("#localidad").val();
		almacenar+=","+$("#myselect").val();
		localStorage.setItem("almacenUsuario", almacenar);
		var password1=$("#contrase�a1").val();
		var password2=$("#contrase�a2").val();
		$("#dangerAlert").remove();
		var html="<div class='alert alert-danger alert-block fade in' id='dangerAlert'><h4>Errores:</h4></div>";
		$("#principio").append(html);
		var select = $("#myselect option:selected").text();
		if(select==="Selecciona"){
			$("#provinciasIncorrectas").remove();
			$("#dangerAlert").append("<p id='provinciasIncorrectas'>Selecciona una provincia</p>");
			if(password1.length==0||password2.length==0){
				$("#contrase�asVacias").remove();
				$("#dangerAlert").append("<p id='contrase�asVacias'>Las contrase�as est�n vac�as</p>");
			}
			if(password1!==password2){
				$("#contrase�asVacias").remove();
				$("#contrase�asIncorrectas").remove();
				$("#dangerAlert").append("<p id='contrase�asIncorrectas'>Las contrase�as son incorrectas</p>");
				$("#contrase�a1").val("");
				$("#contrase�a2").val("");
			}
			return false;
		}
		else if(password1.length==0||password2.length==0){
			$("#provinciasIncorrectas").remove();
			$("#contrase�asVacias").remove();
			$("#dangerAlert").append("<p id='contrase�asVacias'>Las contrase�as est�n vac�as</p>");
			$("#contrase�a1").val("");
			$("#contrase�a2").val("");
			return false;
		}
		else if(password1!==password2){
			$("#contrase�asVacias").remove();
			$("#contrase�asIncorrectas").remove();
			$("#dangerAlert").append("<p id='contrase�asIncorrectas'>Las contrase�as son incorrectas</p>");
			$("#contrase�a1").val("");
			$("#contrase�a2").val("");
			return false;
		}
		if(select!=="Selecciona"&& password1.length!=0||password2.length!=0 && password1!==password2 ){
			$("#provinciasIncorrectas").remove();
			$("#contrase�asVacias").remove();
			$("#dangerAlert").remove();
		}
		
	});
	//Si hay un error despliego el formulario con un danger alert informando al usuario
	var errorUser=getParameterByName('error');
	if(errorUser>""){
		var html="<div class='alert alert-danger alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Error: El usuario ya est� introducido</h4></div>";
		$("#principio").append(html);
		var recuperarAlmacen=localStorage.getItem("almacenUsuario");
		var formulario=recuperarAlmacen.split(",");
		$("#usuario").val(formulario[0]);
		$("#nombre").val(formulario[1]);
		$("#apellidos").val(formulario[2]);
		$("#telefono").val(formulario[3]);
		$("#email").val(formulario[4]);
		$("#calle").val(formulario[5]);
		$("#numero").val(formulario[6]);
		$("#piso").val(formulario[7]);
		$("#codigoP").val(formulario[8]);
		$("#localidad").val(formulario[9]);
		$("#myselect").val(formulario[10]);
	}
	
	function getParameterByName(name) {
	    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
	    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
	        results = regex.exec(location.search);
	    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
	
