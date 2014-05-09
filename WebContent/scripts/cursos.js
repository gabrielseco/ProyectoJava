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
			var fechaI=fechaInicio.split("-");
			var fechaF=fechaFinal.split("-");
			inscritos=parseInt(inscritos);//PASAMOS TANTO LOS INSCRITOS COMO LAS PLAZAS A INTEGER PARA PODER COMPARARLAS
			plazas=parseInt(plazas);
			var html="<div class='alert alert-danger alert-block fade in' id='dangerAlert'><h4>Errores:</h4></div>";
			$("#dangerAlert").remove();
			$("#principio").append(html);
			if(inscritos===''){ 
				inscritos=0;
			}
			if(plazas <= 0 || duracion <=0){
				$("p").remove();
				$("#dangerAlert").append("<p>Las plazas o la duracion introducidas son 0.</p>");
				$("#dangerAlert").append("<p>Introduce un número mayor que 0</p>");
				if(fechaI[0]>fechaF[0]){
					fecha=false;
					$("#dangerAlert").append("<p>Introduce una fecha de inicio menor</p>");
					return false;
				}
				else if(fechaI[1]>fechaF[1]){
					fecha=false;
					$("#dangerAlert").append("<p>Introduce una fecha de inicio menor</p>");
					return false;
				}
				else if(fechaI[2]>=fechaF[2]){
					fecha=false;
					$("#dangerAlert").append("<p>Introduce una fecha de inicio menor</p>");
					return false;
				}
				return false;
			}
			if(inscritos > plazas){
				$("p").remove();
				$("#dangerAlert").append("<p>No puede haber más inscritos que plazas</p>");
				return false;
			}
			if(fechaI[0]>fechaF[0]){
				fecha=false;
				$("p").remove();
				$("#dangerAlert").append("<p>Introduce una fecha de inicio menor</p>");
				return false;
			}
			else if(fechaI[0]<fechaF[0]){
				$("p").remove();
				$("#dangerAlert").remove();
				return true;
				
			}
			 if(fechaI[1]>fechaF[1]){
				fecha=false;
				$("p").remove();
				$("#dangerAlert").append("<p>Introduce una fecha de inicio menor</p>");
				return false;
			}
			else if(fechaI[2]>=fechaF[2]){
				fecha=false;
				$("p").remove();
				$("#dangerAlert").append("<p>Introduce una fecha de inicio menor</p>");
				return false;
			}
			if(inscritos < plazas && plazas > 0 && duracion > 0 && fecha){
				$("p").remove();
				$("#dangerAlert").remove();
			}
		});
	});