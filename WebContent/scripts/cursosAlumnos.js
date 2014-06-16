/**
 * Si el cursor no está sobre la ultima fila de la tabla no se desbloqueara el select
 * Desplega alerts cuando es conveniente
 */
$(function(){
	$("tr:last").hover(function(){
		$("#alumnoInsertar").removeAttr("disabled");
	});
	$("#insertar").on('click',function(){
		var alumno=$("#alumnoInsertar option:selected").text();
		if(alumno==='Selecciona Alumno'){
			var html="<div class='alert alert-danger alert-block fade in' id='dangerAlert'><h4>Errores:</h4></div>";
			$("#principio").append(html);
			$(".alert").append("<p>No has seleccionado a ningún alumno</p>");
			return false;
		}else{
			$(".alert").remove();
		}
	});
	var cambio=false;
	var codigoAlumno="";
	var codigoCurso=$("#codigoCurso").val();
	var importe=$("#importe").val();
	var plazas=$("#plazas").val();
	var inscritos=$("#inscritos").val();
	$("select").change(function(){
		 codigoAlumno=$(this).val();
		 cambio=true;
	});
	
	$("#insertar").on('click',function(){
		if(inscritos>=plazas){
			$("#dangerAlert").remove();
			var html="<div class='alert alert-danger alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4></div>";
			$("#principio").append(html);
			$(".alert").append("<p>Los inscritos no pueden ser mayor que las plazas");
			return false;
		}
		if(cambio===true){
			window.location.href='../../Servlet?submit=Insertar Alumnos&codigoA='+codigoAlumno+"&codigo="+codigoCurso+"&importe="+importe;
			return false;
		}
		
	});
	$("a[title='Modificar Alumno'] ").click(function(){
		var numero = $(this).find("input").val();
		var codigoAntiguo=$("#codigoAntiguo_"+numero).val();
		if(cambio===true){
			window.location.href='../../Servlet?submit=Modificar Alumnos&codigoA='+codigoAlumno+"&codigo="+codigoCurso+"&importe="+importe+"&codigoAntiguo="+codigoAntiguo;
			return false;
		}
		$("#dangerAlert").remove();
		var html="<div class='alert alert-warning alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4></div>";
		$("#principio").append(html);
		$(".alert").append("<p>No ha habido cambio");
	});
});
	