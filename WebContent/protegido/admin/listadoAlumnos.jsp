<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Alumnos</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css"
	href="../../bootstrap3/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<link rel="stylesheet" type="text/css"
	href="../../css/jquery.dataTables.css">
<link rel="icon" type="image/png" href="../../img/favicon.png" />

</head>
<body>
	<div class="container">
		<div class="row">
			<br />
			<table id="tabla" 
				class="table-striped  table-condensed table-responsive ">
				<thead>
					<tr>
						<th>Usuario</th>
						<th>Contraseña</th>
						<th>Nombre</th>
						<th>Apellidos</th>
						<th>Telefono</th>
						<th>Email</th>
						<th>Direccion</th>
						<th>Accion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listadoAlumnos}" var="listadoA" varStatus='count'>
						<tr>
							<td><c:out value="${listadoA.getUsuario()}"></c:out></td>
							<td><c:out value="${listadoA.getContrasenya()}"></c:out></td>
							<td><c:out value="${listadoA.getNombre()}"></c:out></td>
							<td><c:out value="${listadoA.getApellidos()}"></c:out></td>
							<td><c:out value="${listadoA.getTelefono()}"></c:out></td>
							<td><c:out value="${listadoA.getEmail()}"></c:out></td>
							<td><c:out value="${listadoA.getDireccion()}"></c:out></td>
							<td>
								<a title='Ver Cursos' href='../../Servlet?submit=VerCursosDeAlumnos&codigo=${listadoA.getSecAlumno()}'><span class='glyphicon glyphicon-info-sign'></span></a>
								<a title='Ver Productos' href="../../Servlet?submit=VerProductosDeAlumnos&codigo=${listadoA.getSecAlumno()}"><span class="glyphicon glyphicon-glass"></span></a>
								<a title="Modificar Alumnos"
								href='../../Servlet?submit=ModificarAlumnos&codigo=${listadoA.getSecAlumno()}'><span class="glyphicon glyphicon-pencil"></span></a>
								<a title="Eliminar Alumnos" data-toggle='modal' data-target='#eliminarAlumno${count.count}'
								><span class='glyphicon glyphicon-remove'></span></a>
								<div class="modal fade" id="eliminarAlumno${count.count}">
								    <div class="modal-dialog">
								        <div class="modal-content">
								            <div class="modal-header">
								            <button class='close' data-dismiss='modal'>&times;</button>
								            <h4 class="modal-title text-center" id="myModalLabel">Eliminar Alumno</h4>
								            </div>
								            <div class="modal-body">
								                <h3>¿Estás seguro de eliminar el alumno y los alumnos asociados a cursos o a productos?</h3>
								            </div>
								            <div class="modal-footer">
								                <a href="../../Servlet?submit=EliminarAlumnos&codigo=${listadoA.getSecAlumno()}&usuario=${listadoA.getUsuario()}" class="btn btn-danger">Eliminar</a>
								        </div>
								    </div>
								  </div>
								</div>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br />
			<hr />
			<input type="button" name="volver" value="Volver al Inicio"
				class="btn btn-primary col-md-5 pull-right"
				onclick="self.location.href='admin.html'">
			<input type="button" value="Insertar"
				class="btn btn-primary col-md-5 pull-left"
				onclick="self.location.href='../../Servlet?submit=RegistroAlumnos'">
			
		</div>
	</div>
	<script type="text/javascript" src="../../scripts/jquery.js"></script>
	<script type='text/javascript' src='../../bootstrap3/js/bootstrap.js'></script>
	<script type="text/javascript" src="../../scripts/jquery.dataTables.js"></script>
	<script type="text/javascript" src="../../scripts/ordenarTabla.js"></script>
</body>
</html>