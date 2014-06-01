<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*;" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel='stylesheet' type='text/css' href='../../bootstrap3/css/bootstrap.css' />
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<title>Modificar Cursos</title>
</head>
<body>
<div class="container">
<form action="../../Servlet" method="post" class='form-horizontal' onsubmit='return enviar()' enctype="multipart/form-data">
<fieldset>
<legend>Formulario Cursos</legend>
	<a id="principio"></a>
	<div class='form-group'>
		<label class='col-sm-2 control-label' for='nombreCurso'>Nombre Curso:</label>
			<div class='col-sm-3'>
				<input type="text" class='form-control' id='nombreCurso' name="nombreCurso" required="required" placeholder="inserta el nuevo curso" value="${sessionScope.modificarCursos.nombreCurso}">		
			</div>
	</div>
	<div class="form-group">
		<label class='col-sm-2 control-label' for='fechaInicio'>Fecha Inicio:</label>
		<div class='col-sm-3'>
			<input type="date" class='form-control' id='fechaInicio' name="fechaInicio"required="required" placeholder="Inserte su fecha de inicio" value="${sessionScope.modificarCursos.fechaInicio}"/>
		</div>
	</div>
	<div class="form-group">
		<label class='col-sm-2 control-label' for='fechaFinal'>Fecha Final:</label>
		<div class='col-sm-3'>
			<input type="date" class='form-control' id='fechaFinal' name="fechaFinal"required="required" placeholder="Inserte su fecha final"  value="${sessionScope.modificarCursos.fechaFinal }"/>
		</div>
	</div>
	<div class="form-group">
		<label class='col-sm-2 control-label' for='horario'>Horario:</label>
		<div class='col-sm-3'>
			<input type="text" class='form-control' id='horario' name="horario"required="required" placeholder="inserte un horario" value="${sessionScope.modificarCursos.horario }"/>
		</div>
	</div>
	<div class="form-group">
		<label class='col-sm-2 control-label' for='precio'>Precio:</label>
		<div class='col-sm-2'>
			<input type="text" class='form-control' id='precio' name="precio"required="required" placeholder="inserte el precio" value="${sessionScope.modificarCursos.precio}"/>
		</div>
		<label class='col-sm-1 control-label' for='duracion'>Duracion:</label>
		<div class='col-sm-1'>
			<input type="text"  class='form-control derechaInput' id='duracion' name="duracion" required="required" value="${sessionScope.modificarCursos.duracion}"/>
		</div>
		<label class='col-sm-1 control-label' for='plazas'>Plazas:</label>
		<div class='col-sm-1'>
			<input type="number" class='form-control derechaInput' id='plazas' name="plazas"required="required" value="${sessionScope.modificarCursos.plazas}"/>
		</div>
		<label class="col-sm-1 control-label" for='imagen'>Imagen:</label>
			<div class="col-sm-3">
				<input type='file' class='form-control fileImagen' name='imagen' id='imagen' size="60"/>
			</div>
	</div>
	<div class="form-group">
				<label class='col-sm-2 control-label' for='muestra'>Muestra:</label>
				<div class='col-sm-6 pull-left'>
					<input type='hidden' name='nombreImagen' id='nombreImagen' value='${sessionScope.modificarCursos.imagen}'/>
					<img src="../../cursos/${sessionScope.modificarCursos.imagen}" class='img-thumbnail'/>
				</div>
				<div class='botonesFloat'>
					<input type="hidden" value="${sessionScope.modificarCursos.secCurso}" name="codigo"/>
					<input type="button" value="Cancelar" class="btn btn-primary" onclick="self.location.href='listadoCursos.jsp'"/>
					<input type="submit" name="submit" value="Actualizar Cursos" class="btn btn-primary"/>
				</div>
	</div>
</fieldset>
</form>
	</div>
<script src="../../scripts/jquery.js" type="text/javascript"></script>
<script src="../../bootstrap3/js/bootstrap.js"></script>
<script src='../../scripts/cursos.js'></script>
</body>
</html>