<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina de login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" type="text/css" href="../../bootstrap3/css/bootstrap.css"> 
<link rel="stylesheet" type="text/css" href="../../css/admin.css"> 
</head>
<body>
<div class="container">
	<form class='form-horizontal' action="j_security_check" method="post">
		<fieldset>
		<legend>Iniciar Sesion</legend>
		  <div class="form-group">
		  	<label for='nombre' class="col-sm-2 control-label">Usuario:</label>
		  	<div class="col-sm-10 left-inner-addon">
		  		<i class='glyphicon glyphicon-user'></i>
				<input id='nombre' type="text" class="form-control" name="j_username" placeholder='Usuario' autofocus/>
			</div>
		  </div>
		  <div class='form-group'>
		  	<label id='contraseña' class='col-sm-2 control-label'>Password:</label>
		  	<div class='col-sm-10 left-inner-addon'>
		  		<i class='glyphicon glyphicon-lock'></i>
		  		<input id='contraseña' type="password" class="form-control" name="j_password" placeholder='Password'/>
		  	</div>
		  </div>
		    <input type="submit" value="Iniciar Sesion" class="btn btn-primary  pull-right"/>
		  	<input type="button" value="Cancelar" class="btn  btn-primary  pull-right"  onclick="self.location.href='admin.html'"/>			
		</fieldset>
	</form>																							
</div>
<script type="text/javascript" src='../../scripts/jquery.js'></script>
<script type="text/javascript" src="../../bootstrap3/js/bootstrap.js"></script>
</body>
</html>