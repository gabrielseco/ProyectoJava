/**
 * Si el cursor no está sobre la ultima fila de la tabla no se desbloqueara el select
 * Desplega alerts cuando es conveniente
 */
$(function(){
	var codigoProducto=$("#codigoProducto").val();
	var importe=$("#importe").val();
	var codigoAlumno="";
	
	$("tr:last").hover(function(){
		$("#alumnoInsertar").removeAttr("disabled");
	});
	
	$("select").change(function(){
		 codigoAlumno=$(this).val();
	});
	$("#insertar").on('click',function(){
		var alumno=$("#alumnoInsertar option:selected").text();
		if(alumno==='Selecciona Alumno'){
			var html="<div class='alert alert-danger alert-block fade in' id='dangerAlert'><button type='button' class='close' data-dismiss='alert'>&times;</button><h4>Errores:</h4></div>";
			$("#principio").append(html);
			$(".alert").append("<p>No has seleccionado a ningún alumno</p>");
			return false;
		}else{
			$(".alert").remove();
			window.location.href="../../Servlet?submit=Insertar Alumnos Productos&codigoProducto="+codigoProducto+"&codigoAlumno="+codigoAlumno+"&importe="+importe;
			return false;
		}
	});
	$("a[title='Modificar Alumno']").on('click',function(){
		var numero = $(this).find("input").val();
		var codigoAntiguo=$("#codigoAntiguo_"+numero).val();
		window.location.href='../../Servlet?submit=Modificar Productos&codigoProducto='+codigoProducto+"&codigoAlumno="+codigoAlumno+"&importe="+importe+"&codigoAntiguo="+codigoAntiguo;
		return false;
	});
});