<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=500, initial-scale=1">
<title>Listado de Cursos</title>
<link rel="stylesheet" type="text/css"
	href="../../bootstrap3/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<link rel="stylesheet" type="text/css"
	href="../../css/jquery.dataTables.css">
</head>
<body>
	<div class="container">
			<table id="tabla" class="table-striped ">
				<thead>
					<tr>
						<th>Nombre Curso</th>
						<th>Fecha Inicio</th>
						<th>Fecha Final</th>
						<th>Horario</th>
						<th>Duracion</th>
						<th>Precio</th>
						<th>Plazas</th>
						<th>Inscritos</th>
						<th>Accion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listadoCursos}" var="listadoC">
						<tr>
							<td><c:out value="${listadoC.getNombreCurso()}"></c:out></td>
							<td><fmt:formatDate value="${listadoC.getFechaInicio()}"
									type="date" dateStyle="full" /></td>
							<td><fmt:formatDate value="${listadoC.getFechaFinal()}"
									type="date" dateStyle="full" /></td>
							<td><c:out value="${listadoC.getHorario()}"></c:out></td>
							<td><c:out value="${listadoC.getDuracion()}"></c:out></td>
							<td><c:out value="${listadoC.getPrecio()}"></c:out></td>
							<td><c:out value="${listadoC.getPlazas()}"></c:out></td>
							<td><c:out value="${listadoC.getInscritos()}"></c:out></td>
							<td>
								<a title="Añadir Alumnos" href="../../Servlet?submit=CursosAlumnos&codigo=${listadoC.getSecCurso()}"><span  class='glyphicon glyphicon-user'></span></a>
								<a title="Modificar Cursos"
								href='../../Servlet?submit=ModificarCursos&codigo=${listadoC.getSecCurso()}'><span class='glyphicon glyphicon-pencil'></span></a>
								<a title="Eliminar Cursos" 
								 data-toggle='modal' data-target='#eliminarCursos'><span class='glyphicon glyphicon-remove'></span></a>
								<div class="modal fade" id="eliminarCursos" tabindex="-1" >
								    <div class="modal-dialog">
								        <div class="modal-content">
								            <div class="modal-header">
								            <button class='close' data-dismiss='modal'>&times;</button>
								            <h4 class="modal-title text-center" id="myModalLabel">Eliminar Curso</h4>
								            </div>
								            <div class="modal-body">
								                <h3>¿Estás seguro de eliminar el curso y sus alumnos asociados?</h3>
								            </div>
								            <div class="modal-footer">
								                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
								                <a href="../../Servlet?submit=EliminarCursos&codigo=${listadoC.getSecCurso()}" class="btn btn-danger">Eliminar</a>
								        </div>
								    </div>
								  </div>
								</div>
							</td>
					</c:forEach>
				</tbody>
			</table>
			<hr>
			<br> 
			<input class="btn btn-primary col-md-5 pull-left " type="submit"
				value="Insertar" name="submit"
				onclick="self.location.href='../../Servlet?submit=RegistroCursos'" />
			<input type="button" name="volver" value="Volver al Inicio"
				class="btn btn-primary col-md-5 pull-right"
				onclick="self.location.href='admin.html'">
		</div>
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type="text/javascript" src="../../scripts/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../scripts/ordenarTabla.js"></script>
<script type="text/javascript" src="../../bootstrap3/js/bootstrap.js"></script>
</body>
</html>