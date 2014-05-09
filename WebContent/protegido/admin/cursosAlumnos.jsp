<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*;"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalle Cursos</title>
<link rel="stylesheet" type="text/css"
	href="../../bootstrap3/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<link rel="stylesheet" type="text/css"
	href="../../css/jquery.dataTables.css">
<script type="text/javascript" src="../../scripts/jquery.js"></script>
<script type="text/javascript" src="../../scripts/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../scripts/ordenarTabla.js"></script>
<script type="text/javascript" src="../../scripts/cursosAlumnos.js"></script>
<script src="../../scripts/confirmarEliminar.js" type="text/javascript"></script>
</head>
<body>
<div class="container">
	<c:set var="codigoCurso" value="${sessionScope.codigoCurso}"></c:set>
	<c:set var="fechaActual" value="${sessionScope.fechaActual}"></c:set>
	<c:forEach items="${cursos}" var="listadoC">
		<c:set var="nombreCurso" value="${listadoC.getNombreCurso()}"></c:set>
		<c:set var="importe" value="${listadoC.getPrecio()}"></c:set>
		<c:set var="plazas" value="${listadoC.getPlazas()}"></c:set>
		<c:set var="inscritos" value="${sessionScope.inscritos}"></c:set>
	</c:forEach>
	<h1 class='text-center'>Curso de ${nombreCurso}</h1>
	<a id="principio"></a>
	 <form action="../../Servlet" method="post" onsubmit='return enviar();'>
	 <input type='hidden' name='importe' id="importe" value="${importe}"/>
	 <input type="hidden" name="codigo" id="codigoCurso" value="${codigoCurso}"/>
	 <input type="hidden" name="plazas" id="plazas" value="${plazas}"/>
	 <input type="hidden" name="inscritos" id="inscritos" value="${inscritos}"/>
		<table id="tabla" class="table-striped">
					<thead>
						<tr>
							<th>Nombre Alumno</th>
							<th>Fecha Inscripcion</th>
							<th>Importe</th>
							<th>Acción</th>
						</tr>
					</thead>
				   <tbody>
				   <c:forEach items="${cursosAlumnos}" var="listadoCurAl">
				   <input type="hidden" name="codigoAlumnoEliminar" value="${listadoCurAl.getSecCurso()}"/>
							<tr>
								<td>
									<select name='codigoAlumno' id='alumnoModificar' class='form-control'>
										<option value="${listadoCurAl.getSecCurso()}">${listadoCurAl.getNombre()}</option>
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
									<a title='' onclick='return confirmar();' href="../../Servlet?submit=Eliminar Alumnos&codigoAlumnoEliminar=${listadoCurAl.getSecCurso()}&codigo=${codigoCurso}" ><span class='glyphicon glyphicon-remove'></span></a>
								</td>
							</tr>
					</c:forEach>
						<tr>
							<td>
								<select name='codigoAlumno' id='alumnoInsertar' class='form-control' disabled >
									<option id='default' value="0">Selecciona Alumno</option>
									<c:forEach items="${alumnos}" var="listadoA">
										<option value="${listadoA.getSecAlumno()}">${listadoA.getNombre()}</option>
									</c:forEach>
								</select>
							</td>
							<td><fmt:formatDate value="${fechaActual}"
										type="date" dateStyle="full"/>
							</td>
							<td>${importe}</td>
							<td>
								<a title='Insertar Alumno'  id='insertar'><span class='glyphicon glyphicon-plus'></span></a>
							</td>
						</tr>
				</tbody>
		</table>
	</form>
	<hr>
	<a href="listadoCursos.jsp" class="btn btn-primary col-sm-12">Volver a Cursos</a>
</div>
</body>
</html>