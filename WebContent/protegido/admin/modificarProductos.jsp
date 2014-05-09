<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*;" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=500, initial-scale=1">
<link rel='stylesheet' type='text/css' href='../../bootstrap3/css/bootstrap.css' />
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<title>Modificar Productos</title>
</head>
<body>
<div class="container">
	<form action="../../Servlet" method="post" class='form-inline' onsubmit='return enviar();'>
		<fieldset>
		<legend>Insertar Productos</legend>
		<a  id="principio"></a>
			<div class='form-group'>
				<label for='codigo'>Codigo:</label>
				<input type="number" class='form-control derechaInput' id='codigo' name="codigo" required="required" placeholder="Introduce código" value="${sessionScope.modificarProductos.codigo}" />
			</div>
			<div class='form-group'>
				<label for="numUnidades">Unidades:</label>
				<input type="number" class='form-control derechaInput' id='numUnidades' name="numUnidades" required="required" placeholder="Introduce unidades" value="${sessionScope.modificarProductos.numUnidades}"/>
			</div>
			<div class='form-group'>
				<label for="precio">Precio:</label>
				<input type="text"  class='form-control derechaInput' id='precio' name="precio" required="required" placeholder="Introduce precio" value="${sessionScope.modificarProductos.precio}"/>
			</div>
			<input type="hidden" name="codigoModificar" value="${sessionScope.modificarProductos.secProducto}"/>
			<input type="submit" name="submit" class="btn btn-primary" value="Actualizar Productos"/>
			<input type="button" value="Cancelar" class="btn btn-primary" onclick="self.location.href='listadoProductos.jsp'"/>
		</fieldset>
	</form>
</div>
<script src="../../scripts/jquery.js" type="text/javascript"></script>
<script src="../../bootstrap3/js/bootstrap.js"></script>
<script src='../../scripts/productos.js'></script>
</body>
</html>