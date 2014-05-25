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
	<form action="../../Servlet" method="post" class='form-horizontal' onsubmit='return enviar();'>
		<fieldset>
		<legend>Insertar Productos</legend>
		<a  id="principio"></a>
		<div class='form-group'>
				<label  class="col-sm-2 control-label" for='codigo'>Codigo:</label>
				<div class="col-sm-1">
					<input type="number" class='form-control derechaInput' id='codigo' name="codigo" required="required" value="${sessionScope.modificarProductos.nombre}" />
				</div>
				<label class="col-sm-2 control-label" for="nombre">Nombre:</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="nombre" name="nombre" required="required" placeholder="Introduzca nombre" value="${sessionScope.modificarProductos.codigo}"/>
				</div>
				<label class="col-sm-2 control-label" for='descripcion'>Descripcion:</label>
				<div class="col-sm-3">
					<textarea name='descripcion' id='descripcion' rows="4" cols="50" required='required' placeholder='Introduce una descripci�n' class='form-control' >${sessionScope.modificarProductos.descripcion}</textarea>
				</div>
			</div>
			<div class='form-group'>
				<label class="col-sm-2 control-label"  for="numUnidades">Unidades:</label>
				<div class="col-sm-1">
					<input type="number" class='form-control derechaInput' id='numUnidades' name="numUnidades" required="required" value="${sessionScope.modificarProductos.numUnidades}"/>
				</div>
				<label class="col-sm-2 control-label" for="precio">Precio:</label>
				<div class="col-sm-1">
					<input type="text"  class='form-control derechaInput' id='precio' name="precio" required="required" value="${sessionScope.modificarProductos.precio}"/>
				</div>
				<label class="col-sm-3 control-label" for='imagen'>Imagen:</label>
				<div class="col-sm-3">
					<input type='file' class='form-control' name='imagen' id='imagen' value="${sessionScope.modificarProductos.imagen}"/>
				</div>
			</div>
			<input type="hidden" name="codigoModificar" value="${sessionScope.modificarProductos.secProducto}"/>
			<input type="button" value="Cancelar" class="btn btn-primary pull-right" onclick="self.location.href='listadoProductos.jsp'"/>
			<input type="submit" name="submit" class="btn btn-primary pull-right" value="Actualizar Productos"/>
		</fieldset>
	</form>
</div>
<script src="../../scripts/jquery.js" type="text/javascript"></script>
<script src="../../bootstrap3/js/bootstrap.js"></script>
<script src='../../scripts/productos.js'></script>
</body>
</html>