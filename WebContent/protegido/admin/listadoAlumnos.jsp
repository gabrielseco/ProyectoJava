<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Alumnos</title>
<meta name="viewport" content="width=500, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="../../bootstrap3/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<link rel="stylesheet" type="text/css"
	href="../../css/jquery.dataTables.css">
<script src="../../scripts/confirmarEliminar.js" type="text/javascript"></script>
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type="text/javascript" src="../../scripts/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../scripts/ordenarTabla.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<br />
			<table id="tabla"
				class="table-striped  table-condensed table-responsive ">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Apellidos</th>
						<th>Telefono</th>
						<th>Email</th>
						<th>Direccion</th>
						<th>Usuario</th>
						<th>Contraseņa</th>
						<th>Accion</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listadoAlumnos}" var="listadoA">
						<tr>
							<td><c:out value="${listadoA.getNombre()}"></c:out></td>
							<td><c:out value="${listadoA.getApellidos()}"></c:out></td>
							<td><c:out value="${listadoA.getTelefono()}"></c:out></td>
							<td><c:out value="${listadoA.getEmail()}"></c:out></td>
							<td><c:out value="${listadoA.getDireccion()}"></c:out></td>
							<td><c:out value="${listadoA.getUsuario()}"></c:out></td>
							<td><c:out value="${listadoA.getContrasenya()}"></c:out></td>
							<td>
								<a title="Modificar Alumnos"
								href='../../Servlet?submit=ModificarAlumnos&codigo=${listadoA.getSecAlumno()}'><span class="glyphicon glyphicon-pencil"></span></a>
								<a title="Eliminar Alumnos" class="" onclick='return confirmar()'
								href='../../Servlet?submit=EliminarAlumnos&codigo=${ listadoA.getSecAlumno()}&nombre=${listadoA.getNombre()}'><span class='glyphicon glyphicon-remove'></span></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<br />
			<hr />
			<input type="button" value="Insertar"
				class="btn btn-primary col-md-5 pull-left"
				onclick="self.location.href='../../Servlet?submit=RegistroAlumnos'">
			<input type="button" name="volver" value="Volver al Inicio"
				class="btn btn-primary col-md-5 pull-right"
				onclick="self.location.href='admin.html'">
		</div>
	</div>
</body>
</html>