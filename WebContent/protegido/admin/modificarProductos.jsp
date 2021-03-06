<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*;" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel='stylesheet' type='text/css' href='../../bootstrap3/css/bootstrap.css' />
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<link rel="icon" type="image/png" href="../../img/favicon.png" />
<title>Modificar Productos</title>
</head>
<body>
<div class="container">
	<form action="../../Servlet" method="post" class='form-horizontal' onsubmit='return enviar();'enctype="multipart/form-data">
		<fieldset>
		<legend>Modificar Productos</legend>
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
					<textarea name='descripcion' id='descripcion' rows="4" cols="50" required='required' placeholder='Introduce una descripción' class='form-control' >${sessionScope.modificarProductos.descripcion}</textarea>
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
					<input type='file' class='form-control fileImagen' name='imagen' id='imagen' />
				</div>
			</div>
			<div class="form-group">
				<label class='col-sm-2 control-label' for='muestra'>Muestra:</label>
				<div class='col-sm-6 pull-left'>
					<input type='hidden' name='nombreImagen' id='nombreImagen' value='${sessionScope.modificarProductos.imagen}'/>
					<img src="../../productos/${sessionScope.modificarProductos.imagen}" class='img-thumbnail'/>
				</div>
				<div class='botonesFloat'>
					<input type="hidden" name="codigoModificar" value="${sessionScope.modificarProductos.secProducto}"/>
					<input type="button" value="Cancelar" class="btn btn-primary pull-right  " onclick="self.location.href='listadoProductos.jsp'"/>
					<input type="submit" name="submit" class="btn btn-primary pull-right " value="Actualizar Productos"/>
				</div>
			</div>
		</fieldset>
	</form>
</div>
<script src="../../scripts/jquery.js" type="text/javascript"></script>
<script src="../../bootstrap3/js/bootstrap.js"></script>
<script src='../../scripts/productos.js'></script>
</body>
</html>