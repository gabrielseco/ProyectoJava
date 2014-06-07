/**
 * Errores de formulario en el registro de alumnos
 * CUANDO HACEMOS CLICK EN ENVIAR
 * COMPROBAMOS QUE EL SELECT SEA DISTINTO DE SELECIONA COMPROBAMOS SI
 * LAS CONTRASEÑAS ESTAN VACIAS O SI SON DISTINTAS.
 * SI TODO VA BIEN SE LIMPIAN LOS NODOS HTML'S AÑADIDOS
 * AÑADO UN DIV ALERT PARA CUANDO NO HAY USUARIOS
 * LOS DATOS DEL FORMULARIO AL ENVIAR LOS GUARDO EN EL NAVEGADOR POR SI AL DAR ERROR EN EL USUARIO QUE APAREZCAN POBLADOS LOS CAMPOS
 */
	
	var url=location.href.split("/");
	if(url[url.length-1] === 'registroAlumnos.html'){
		localStorage.removeItem("almacenUsuario");
		localStorage.removeItem("erroresFormulario");
	}
	if(localStorage.getItem("erroresFormulario")!==null){
		var errores=localStorage.getItem("erroresFormulario");
		$("#principio").append(errores);
	}
	if(localStorage.getItem("almacenUsuario")!==null){
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

	$("#enviar").click(function(){
		var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	    for( var i=0; i < 5; i++ ){
	        text += possible.charAt(Math.floor(Math.random() * possible.length));
	    }
		localStorage.setItem("clave", text);
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
		var password1=$("#contraseña1").val();
		var password2=$("#contraseña2").val();
		$("#dangerAlert").remove();
		var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4>";
		var select = $("#myselect option:selected").text();
		if(select==="Selecciona"){
			$("#provinciasIncorrectas").remove();
			html+="<h4 id='provinciasIncorrectas'>Selecciona una provincia</h4>";
			if(password1.length==0||password2.length==0){
				$("#contraseñasVacias").remove();
				html+="<h4 id='contraseñasVacias'>Las contraseñas están vacías</h4>";
			}
			if(password1!==password2){
				$("#contraseñasVacias").remove();
				$("#contraseñasIncorrectas").remove();
				html+="<h4 id='contraseñasIncorrectas'>Las contraseñas son incorrectas</h4>";
				$("#contraseña1").val("");
				$("#contraseña2").val("");
			}
			html+="</div>";
			$("#principio").append(html);
			return false;
		}
		else if(password1.length==0||password2.length==0){
			$("#dangerAlert").remove();
			var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4>";
			html+="<h4 id='contraseñasVacias'>Las contraseñas están vacías</h4>";
			html+="</div>";
			$("#principio").append(html);
			$("#contraseña1").val("");
			$("#contraseña2").val("");
			return false;
		}
		else if(password1!==password2){
			$("#dangerAlert").remove();
			var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4>";
			html+="<h4 id='contraseñasIncorrectas'>Las contraseñas son incorrectas</h4>";
			html+="</div>";
			$("#principio").append(html);
			$("#contraseña1").val("");
			$("#contraseña2").val("");
			return false;
		}
		if(select!=="Selecciona"&& password1.length!=0||password2.length!=0 && password1!==password2 ){
			$("#dangerAlert").remove();
		}
		
	});
	//Si hay un error despliego el formulario con un danger alert informando al usuario
	var errorUser=getParameterByName('error');
	if(errorUser > ""){
		var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores: El usuario ya está introducido</h4><h4>Introduce las contraseñas de nuevo</h4></div>";
		$(".alert").remove();
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
	
	
