<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>Detalle Productos</title>
<link rel="stylesheet" type="text/css"
	href="../../bootstrap3/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<link rel="stylesheet" type="text/css"
	href="../../css/jquery.dataTables.css">
<link rel="icon" type="image/png" href="../../img/favicon.png" />
</head>
<body>
<c:set var="codigoProducto" value="${sessionScope.codigoProducto}"></c:set>
<c:set var="fechaActual" value="${sessionScope.fechaActual}"></c:set>
<c:forEach items="${productosAl}" var="prodA">
	<c:set var="nombre" value="${prodA.getNombre()}"></c:set>
	<c:set var="precio" value="${prodA.getImporte()}"></c:set>
</c:forEach>
<input type="hidden" name="codigoProducto" id="codigoProducto" value="${codigoProducto}"/>
<input type="hidden" name="importe" id="importe" value="${precio}"/>
<div class="container">
	<h1 class='text-center subrayar'>Producto ${nombre}</h1>
		<a id="principio"></a>
		 <form action="../../Servlet" method="post" onsubmit='return enviar();'>
		 	<table id="tabla" class="table-striped">
						<thead>
							<tr>
								<th>Nombre Alumno</th>
								<th>Fecha Inscripcion</th>
								<th>Importe</th>
								<th>Acci�n</th>
							</tr>
						</thead>
					   <tbody>
					   	<c:forEach items="${productosAlumnos}" var="listadoCurAl" varStatus="count">
				   		<input type="hidden" name="codigoAlumnoEliminar" value="${listadoCurAl.getSecProducto()}"/>
							<tr>
								<td>
									<select name='codigoAlumno' id='alumnoModificar' class='form-control'>
										<option value="${listadoCurAl.getSecProducto()}">${listadoCurAl.getNombre()}</option>
										<c:forEach items="${alumnos}" var="listadoA">
											<option value="${listadoA.getSecAlumno()}">${listadoA.getNombre()}</option>
										</c:forEach>
									</select>
								</td>

								
								<td><fmt:formatDate value="${listadoCurAl.getFechaInscripcion()}"
										type="date" dateStyle="full"/></td>
								<td>${listadoCurAl.getImporte()}</td>
								<td>
									<a title='Modificar Alumno'  id='actualizar'><span class='glyphicon glyphicon-pencil'></span></a>
									<a title='Eliminar Alumno' data-toggle='modal' data-target='#eliminarAlumno${count.count}'><span class='glyphicon glyphicon-remove'></span></a>
									<div class="modal fade" id="eliminarAlumno${count.count}" tabindex="-1" >
								    <div class="modal-dialog">
								        <div class="modal-content">
								            <div class="modal-header">
								            <button class='close' data-dismiss='modal'>&times;</button>
								            <h4 class="modal-title text-center" id="myModalLabel">Eliminar Alumno</h4>
								            </div>
								            <div class="modal-body">
								                <h3>�Est�s seguro de eliminar el alumno del curso?</h3>
								            </div>
								            <div class="modal-footer">
								                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
								                <a href="../../Servlet?submit=Eliminar Productos&codigoAlumno=${listadoCurAl.getSecAlumno()}&codigoProducto=${codigoProducto}" class="btn btn-danger">Eliminar</a>
								        </div>
								    </div>
								  </div>
								</div>
								</td>
							</tr>
					</c:forEach>
					   	<tr>
					   		<td>
					   			<select name='codigoAlumno' id='alumnoInsertar' class='form-control' disabled>
					   				<option id='default' value="0">Selecciona Alumno</option>
					   				<c:forEach items="${alumnos}" var="listadoA">
											<option value="${listadoA.getSecAlumno()}">${listadoA.getNombre()}</option>
										</c:forEach>
					   			</select>
					   		</td>
					   		<td><fmt:formatDate value="${fechaActual}"
											type="date" dateStyle="full"/>
							</td>
					   		<td>${precio}</td>
					   		<td>
					   			<a title='Insertar Alumno'  id='insertar'><span class='glyphicon glyphicon-plus'></span></a>
					   		</td>
					   	</tr>
					   </tbody>
			</table>
		 </form>
	<hr>
		<a  class="btn btn-primary col-sm-5 pull-right" onclick="self.location.href='listadoProductos.jsp'">Volver a Productos</a>
		<a  class='btn btn-primary col-sm-5 pull-left' onclick="self.location.href='../../Servlet?submit=ListadoAlumnos'">Ver Alumnos</a>
</div>
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type='text/javascript' src='../../bootstrap3/js/bootstrap.js'></script>
<script type="text/javascript" src="../../scripts/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../scripts/ordenarTabla.js"></script>
<script type="text/javascript" src="../../scripts/productosAlumnos.js"></script>
</body>
</html>