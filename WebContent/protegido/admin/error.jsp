<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=500, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pagina de Error</title>
</head>
<body>
<% response.sendRedirect("registroAlumnos.html?error='userInDb'"); %>

</body>
</html>