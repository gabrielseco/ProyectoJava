<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,paquete.*;" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel='stylesheet' type='text/css' href='../../bootstrap3/css/bootstrap.css' />
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<title>Modificar Alumnos</title>
</head>
<body>
<div class="container">
<form action="../../Servlet" method="post" class='form-horizontal'>
<fieldset>
   <legend>Formulario de Alta</legend>
      <a id="principio"></a>
      <input type="hidden" name="nombreUsuario" value="${sessionScope.modificarAlumnos.usuario}"></input>
      <div class='form-group'>
					<label class='col-sm-2 control-label' for='usuario'>Usuario:</label>
					<div class='col-sm-3'>
						<input type="text" class="form-control" id='usuario'
							name="usuario" required
							placeholder="Inserte su nombre de usuario" value="${sessionScope.modificarAlumnos.usuario}"/>
					</div>
				</div>
				<div id="passwords">
					<div class='form-group'>
						<label class='col-sm-2 control-label' for='constraseña1'>Contraseña:</label>
						<div class='col-sm-3'>
							<input type="text" class="form-control" name="password1"
								required placeholder="Inserte su contraseña" id="contraseña1" value="${sessionScope.modificarAlumnos.contrasenya}"  />
						</div>
					</div>
				</div>
				<div class='form-group'>
					<label class='col-sm-2 control-label' for='nombre'>Nombre:</label>
					<div class='col-sm-3'>
						<input type="text" class="form-control" id='nombre' name="nombre"
							required="required" placeholder="Inserte su nombre" value="${sessionScope.modificarAlumnos.nombre}" />
					</div>
					<label class='col-sm-2 control-label' for='apellidos'>Apellidos:</label>
					<div class='col-sm-3'>
						<input type="text" class="form-control" id='apellidos'
							name="apellidos" required="required"
							placeholder="Inserte sus apellidos" value="${sessionScope.modificarAlumnos.apellidos} " />
					</div>
				</div>
				<div class='form-group'>
					<label class='col-sm-2 control-label' for='telefono'>Telefono:</label>
					<div class='col-sm-3'>
						<input type="tel" class="form-control" id='telefono'
							name="telefono" required="required"
							placeholder="Inserte su telefono" pattern="|^\d{9}$|"
							title="Se deben introducir 9 números" value="${sessionScope.modificarAlumnos.telefono}" />
					</div>
					<label class='col-sm-2 control-label' for='email'>Email:</label>
					<div class='col-sm-3'>
						<input type="email" class="form-control" id='email' name="email"
							required="required" placeholder="Inserte su email" value="${sessionScope.modificarAlumnos.email}" />
					</div>
				</div>
				<hr />
				<h3>Datos Domiciliarios</h3>
				<div class='form-group'>
					<label class='col-sm-2 control-label' for='calle'>Calle:</label>
					<div class='col-sm-4'>
						<input type="text" id='calle' name="calle" class='form-control'
							required="required" placeholder="Inserte su calle" value="${sessionScope.modificarAlumnos.calle}" />
					</div>
					<label class='col-sm-2 control-label numeroPortal' for='numero'>Número
						Portal:</label>
					<div class='col-sm-1'>
						<input type="number" class='form-control' id='numero'
							required="required" name="numero" value="${sessionScope.modificarAlumnos.numero}" />
					</div>
					<label class='col-sm-1 control-label' for='piso'>Piso:</label>
					<div class='col-sm-1'>
						<input type="text" class='form-control derechaInput' required="required"
							id='piso' name="piso" value="${sessionScope.modificarAlumnos.piso}" />
					</div>
				</div>
				<div class='form-group'>
					<label class='col-sm-2 control-label' for='codigoP'>CP:</label>
					<div class='col-sm-1'>
						<input type="text" class='form-control derechaInput' name="codigoPostal"
							id="codigoP" required="required" pattern="^[0-9]{5}$"
							title="Se deben introducir 5 números" value="${sessionScope.modificarAlumnos.codigoPostal}" />
					</div>
					<label class='col-sm-1 control-label' for='localidad'>Localidad:</label>
					<div class='col-sm-2'>
						<input type="text" class="form-control" id='localidad'
							name="localidad" required="required"
							placeholder="Inserte su localidad" value="${sessionScope.modificarAlumnos.localidad}" />
					</div>
					<label id='provinciaLabel' class='col-sm-2 control-label'
						for='myselect'>Provincia:</label>
					<div class='col-sm-3'>
						<select id="myselect" class="form-control text-center"
							name="provincia" required="required">
							<c:if test="${sessionScope.modificarAlumnos.provincia =='Andalucia'}">
						<option selected>Andalucia</option>
					
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Andalucia'}">
							<option>Andalucia</option>
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Aragon'}">
							<option selected>Aragon</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Aragon'}">
							<option>Aragon</option>
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Asturias'}">
							<option selected>Asturias</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Asturias'}">
							<option>Asturias</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Baleares'}">
							<option selected>Baleares</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Baleares'}">
							<option>Baleares</option>
					</c:if>      
						<c:if test="${sessionScope.modificarAlumnos.provincia =='Canarias'}">
							<option selected>Canarias</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Canarias'}">
							<option>Canarias</option>
					</c:if>    
						<c:if test="${sessionScope.modificarAlumnos.provincia =='Cantabria'}">
							<option selected>Cantabria</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Cantabria'}">
							<option>Cantabria</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Castilla-La Mancha'}">
							<option selected>Castilla-La Mancha</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Castilla-La Mancha'}">
							<option>Castilla-La Mancha</option>
					</c:if>        
		            <c:if test="${sessionScope.modificarAlumnos.provincia =='Castilla y Leon'}">
							<option selected>Castilla y Leon</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Castilla y Leon'}">
							<option>Castilla y Leon</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Cataluña'}">
							<option selected>Cataluña</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Cataluña'}">
							<option>Cataluña</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Ceuta'}">
							<option selected>Ceuta</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Ceuta'}">
							<option>Ceuta</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Extremadura'}">
							<option selected>Extremadura</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Extremadura'}">
							<option>Extremadura</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Galicia'}">
							<option selected>Galicia</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Galicia'}">
							<option>Galicia</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Galicia'}">
							<option selected>Galicia</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Galicia'}">
							<option>Galicia</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='La Rioja'}">
							<option selected>La Rioja</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='La Rioja'}">
							<option>La Rioja</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Madrid'}">
							<option selected>Madrid</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Madrid'}">
							<option>Madrid</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Melilla'}">
							<option selected>Melilla</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Melilla'}">
							<option>Melilla</option>
					</c:if>
					<c:if test="${sessionScope.modificarAlumnos.provincia =='Murcia'}">
							<option selected>Murcia</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Murcia'}">
							<option>Murcia</option>
					</c:if>
		             <c:if test="${sessionScope.modificarAlumnos.provincia =='Navarra'}">
							<option selected>Navarra</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Navarra'}">
							<option>Navarra</option>
					</c:if>
					 <c:if test="${sessionScope.modificarAlumnos.provincia =='Pais Vasco'}">
							<option selected>Pais Vasco</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Pais Vasco'}">
							<option>Pais Vasco</option>
					</c:if>
		            <c:if test="${sessionScope.modificarAlumnos.provincia =='Valencia'}">
							<option selected>Valencia</option>
						
					</c:if>	
					<c:if test="${sessionScope.modificarAlumnos.provincia !='Valencia'}">
							<option>Valencia</option>
					</c:if>  
							</select>
					</div>
				</div>      
    <input type="hidden" name="codigo" value="${sessionScope.modificarAlumnos.secAlumno}"/>
    <div class='pull-right'>
    	<input type="button" value="Cancelar" onclick="self.location.href='listadoAlumnos.jsp'" class="btn btn-primary" />
    	<input type="submit" value="Actualizar Alumnos" name="submit" class="btn btn-primary" />
    </div> 
</fieldset>
</form>
</div>
<script src="../../scripts/jquery.js" type="text/javascript"></script>
<script src="../../bootstrap3/js/bootstrap.js"></script>
</body>
</html>