/**
 * Errores de formulario en el registro de alumnos
 * CUANDO HACEMOS CLICK EN ENVIAR
 * COMPROBAMOS QUE EL SELECT SEA DISTINTO DE SELECIONA COMPROBAMOS SI
 * LAS CONTRASE�AS ESTAN VACIAS O SI SON DISTINTAS.
 * SI TODO VA BIEN SE LIMPIAN LOS NODOS HTML'S A�ADIDOS
 */
	$("#enviar").click(function(){
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
			}
			return false;
		}
		else if(password1.length==0||password2.length==0){
			$("#provinciasIncorrectas").remove();
			$("#contrase�asVacias").remove();
			$("#dangerAlert").append("<p id='contrase�asVacias'>Las contrase�as est�n vac�as</p>");
			return false;
		}
		else if(password1!==password2){
			$("#contrase�asVacias").remove();
			$("#contrase�asIncorrectas").remove();
			$("#dangerAlert").append("<p id='contrase�asIncorrectas'>Las contrase�as son incorrectas</p>");
			return false;
		}
		if(select!=="Selecciona"&& password1.length!=0||password2.length!=0 && password1!==password2 ){
			$("#provinciasIncorrectas").remove();
			$("#contrase�asVacias").remove();
			$("#dangerAlert").remove();
		}
		
	});
