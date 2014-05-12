<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalle Alumnos</title>
<link rel="stylesheet" type="text/css"
	href="../../bootstrap3/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<link rel="stylesheet" type="text/css"
	href="../../css/jquery.dataTables.css">
</head>
<body>
<div class="container">
		<div class="row">
			<h1 class='text-center subrayar'>Alumno ${sessionScope.nombre} </h1>
			<br />
			<table id="tabla"
				class="table-striped  table-condensed table-responsive ">
				<thead>
					<tr>
						<th>Nombre Curso</th>
						<th>Fecha Inscripcion</th>
						<th>Importe</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${alumnosApuntados}" var='listado'>
						<tr>
							
							<td>${listado.getNombre()}</td>
							<td>${listado.getFechaInscripcion()}</td>
							<td>${listado.getImporte()}</td>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<hr>
		<a  class="btn btn-primary col-sm-12" onclick="self.location.href='listadoAlumnos.jsp'">Volver a Alumnos</a>
		
</div>
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type='text/javascript' src='../../bootstrap3/js/bootstrap.js'></script>
<script type="text/javascript" src="../../scripts/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../scripts/ordenarTabla.js"></script>
<script type="text/javascript" src="../../scripts/cursosAlumnos.js"></script>
</body>
</html>