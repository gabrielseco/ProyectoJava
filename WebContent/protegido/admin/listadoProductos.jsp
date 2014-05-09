<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*;" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

<title>Listado de Productos</title>
</head>
<body>
<div class="container">
<table id="tabla" class="table-striped  table-condensed ">
<thead>
<tr>
	<th>Codigo</th>
	<th>Unidades</th>
	<th>Precio</th>
	<th>Accion</th>
</tr>
</thead>
<tbody>
	<c:forEach items="${listadoProductos}" var="listadoP">
		<tr>
		<td>
			<c:out value="${listadoP.getCodigo()}"></c:out>
		</td>
		<td>
			<c:out value="${listadoP.getNumUnidades()}"></c:out>
		</td>
		<td>
			<c:out value="${listadoP.getPrecio()}"></c:out>
		</td>
		<td>
			<a title="Modificar Productos"  href='../../Servlet?submit=ModificarProductos&codigo=${listadoP.getSecProducto()}'><span class="glyphicon glyphicon-pencil"></span></a>
			<a title="Eliminar Productos"  onclick='return confirmar()' href='../../Servlet?submit=EliminarProductos&codigo=${listadoP.getSecProducto()}'><span class='glyphicon glyphicon-remove'></span></a>
		</td>
		</tr>
	</c:forEach>
</tbody>
</table>
<hr>
<br>
<input type="submit" name="submit" value="Insertar" class="btn btn-primary col-md-5 pull-left " onclick="self.location.href='../../Servlet?submit=RegistroProductos'"> 
<input type="button" name="volver" value="Volver al Inicio" class="btn btn-primary col-md-5 pull-right" onclick="self.location.href='admin.html'">
	</div>
</body>
</html>