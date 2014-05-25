/**
 * Verificar que la fecha final sea mayor que la fecha inicial.
 * LA DURACION NO PUEDE SER 0 Y LAS PLAZAS TAMPOCO
 * LOS INSCRITOS NO PUEDEN SUPERAR A LAS PLAZAS
 */
	$(function(){
		
		$("input[type='submit']").on('click',function(){
			var plazas=$("#plazas").val();
			var duracion=$("#duracion").val();
			var inscritos=$("#inscritos").val();
			var fechaInicio=$("#fechaInicio").val();
			var fechaFinal=$("#fechaFinal").val();
			var fecha=true;
			inscritos=parseInt(inscritos);//PASAMOS TANTO LOS INSCRITOS COMO LAS PLAZAS A INTEGER PARA PODER COMPARARLAS
			plazas=parseInt(plazas);
			if(inscritos===''){ 
				inscritos=0;
			}
			if(plazas <= 0 || duracion <=0){
				var html="<div class='alert alert-danger alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4></div>";
				$("#dangerAlert").remove();
				$("#principio").append(html);
				$("p").remove();
				$("#dangerAlert").append("<p>Las plazas o la duracion introducidas son 0.</p>");
				$("#dangerAlert").append("<p>Introduce un número mayor que 0</p>");
				if(Date.parse(fechaInicio)>=Date.parse(fechaFinal)){
					fecha=false;
					$("#dangerAlert").append("<p>Introduce una fecha de inicio menor</p>");
				}
				return false;
			}
			if(Date.parse(fechaInicio)>=Date.parse(fechaFinal)){
				var html="<div class='alert alert-danger alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4></div>";
				$("#dangerAlert").remove();
				$("#principio").append(html);
				fecha=false;
				$("#dangerAlert").append("<p>Introduce una fecha de inicio menor</p>");
				return false;
			}
			if(inscritos < plazas && plazas > 0 && duracion > 0 && fecha){
				$("p").remove();
				$("#dangerAlert").remove();
			}
		});
	});