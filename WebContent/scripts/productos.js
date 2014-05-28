/**
 * VERIFICAMOS TANTO EL PRECIO COMO LAS UNIDADES SEA MAYOR QUE 0.CONFIGURAMOS UN MENSAJE PARA EL USUARIO
 */
$(function(){
	$("input[type='submit']").click(function(){
		var precio=$("#precio").val();
		var numUnidades=$("#numUnidades").val();
		$("#dangerAlert").remove();
		var html="<div class='alert alert-danger alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4></div>";
		$("#principio").append(html);
		if(precio <= 0 || numUnidades <= 0 ){
			$("p").remove();
			$("#dangerAlert").append("<p>El precio o las unidades introducidas son 0.</p>");
			$("#dangerAlert").append("<p>Introduce un n�mero mayor que 0</p>");
			return false;
		}
		if(precio >0 && numUnidades > 0){
			$("p").remove();
			$("#dangerAlert").remove();
		}
	});
	var i=0;
	$(".fileImagen").on('click',function(){
		if(i===0){
			$("img").slideUp();
			$(".alert-warning").remove();
			var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Selecciona una imagen</h4></div>";
			$("#principio").append(html);
			i++;
		}
		
	});
	
});