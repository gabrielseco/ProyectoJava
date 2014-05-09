/**
 * Errores de formulario en el registro de alumnos
 * CUANDO HACEMOS CLICK EN ENVIAR
 * COMPROBAMOS QUE EL SELECT SEA DISTINTO DE SELECIONA COMPROBAMOS SI
 * LAS CONTRASEÑAS ESTAN VACIAS O SI SON DISTINTAS.
 * SI TODO VA BIEN SE LIMPIAN LOS NODOS HTML'S AÑADIDOS
 */
	$("#enviar").click(function(){
		var password1=$("#contraseña1").val();
		var password2=$("#contraseña2").val();
		$("#dangerAlert").remove();
		var html="<div class='alert alert-danger alert-block fade in' id='dangerAlert'><h4>Errores:</h4></div>";
		$("#principio").append(html);
		var select = $("#myselect option:selected").text();
		if(select==="Selecciona"){
			$("#provinciasIncorrectas").remove();
			$("#dangerAlert").append("<p id='provinciasIncorrectas'>Selecciona una provincia</p>");
			if(password1.length==0||password2.length==0){
				$("#contraseñasVacias").remove();
				$("#dangerAlert").append("<p id='contraseñasVacias'>Las contraseñas están vacías</p>");
			}
			if(password1!==password2){
				$("#contraseñasVacias").remove();
				$("#contraseñasIncorrectas").remove();
				$("#dangerAlert").append("<p id='contraseñasIncorrectas'>Las contraseñas son incorrectas</p>");
			}
			return false;
		}
		else if(password1.length==0||password2.length==0){
			$("#provinciasIncorrectas").remove();
			$("#contraseñasVacias").remove();
			$("#dangerAlert").append("<p id='contraseñasVacias'>Las contraseñas están vacías</p>");
			return false;
		}
		else if(password1!==password2){
			$("#contraseñasVacias").remove();
			$("#contraseñasIncorrectas").remove();
			$("#dangerAlert").append("<p id='contraseñasIncorrectas'>Las contraseñas son incorrectas</p>");
			return false;
		}
		if(select!=="Selecciona"&& password1.length!=0||password2.length!=0 && password1!==password2 ){
			$("#provinciasIncorrectas").remove();
			$("#contraseñasVacias").remove();
			$("#dangerAlert").remove();
		}
		
	});
