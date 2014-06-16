<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<title>Usuario Incorrecto</title>
<meta name="viewport" content="width=500, initial-scale=1">
<link rel="stylesheet" type="text/css" href="../../bootstrap3/css/bootstrap.css"> 
<link rel="stylesheet" type="text/css" href="../../css/admin.css">
<link rel="icon" type="image/png" href="../../img/favicon.png" /> 
<style>
	body{
	background-color:#ccc;
	}
</style>
</head>
<body>
<div class="container">
  <div class="row">
    <div class="center-form panel">
      <div class="panel-body">
      <div class='alert alert-danger alert-block fade in' id='dangerAlert'>
		<button type='button' class='close' data-dismiss='alert'>&times;</button>
		<h4>Error al introducir el usuario</h4>
	</div>
        <h2 class="text-center">Acceso a Admin</h2>
		<br/>
        <form action="j_security_check" method="post">
          <div class="form-group">
            <input class="form-control input-lg" type="text" name="j_username"
                    placeholder="Usuario" required autofocus>
          </div>

          <div class="form-group">
            <input class="form-control input-lg" type="password" name="j_password"
                    placeholder="Contraseña" required>
          </div>

          <button type="submit" id='iniciarSesion'
                  class="btn btn-lg btn-block btn-success">Iniciar Sesión
          </button>
        </form>
      </div>
    </div>
  </div>
</div>																										
<script type="text/javascript" src='../../scripts/jquery.js'></script>
<script type="text/javascript" src="../../bootstrap3/js/bootstrap.js"></script>
</body>
</html>