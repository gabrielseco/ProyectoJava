package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CursosAlumnosDAO {

	Connection miConexion;
	PreparedStatement sentencia;
	ResultSet resultados;
	//FUNCION QUE NOS CONECTA CON LA BASE DE DATOS
	public CursosAlumnosDAO(Connection miConexion, PreparedStatement sentencia) {
		// TODO Auto-generated constructor stub
		this.miConexion=miConexion;
		this.sentencia=sentencia;
	}
//CONSULTAMOS EL NOMBRE Y EL PRECIO DE UN CURSO Y LO GUARDAMOS EN UN ARRAYLIST
//GUARDAMOS EL NOMBRE Y EL CODIGO DE TODOS LOS ALUMNOS EN UN ARRAYLIST QUE LUEGO UTILIZAREMOS EN UN SELECT
//ES UNA CONSULTA PARA LA FILA DE INSERTAR
//SE HACE TAMBIEN UNA CONSULTA PARA LA TABLA CURSOS ALUMNOS
	public void consultar(HttpSession sesion, Properties comandos,
			HttpServletRequest request) {
		CursosAlumnos cursosA=new CursosAlumnos();
		Cursos c=new Cursos();
		ArrayList<Cursos>cursos=new ArrayList<Cursos>();
		ArrayList<CursosAlumnos>cursosAlumnos=new ArrayList<CursosAlumnos>();
		String codigoCurso=request.getParameter("codigo");
		GregorianCalendar fechaHoy=new GregorianCalendar();
		String nombre;
		ResultSet resultadosAlumnos;
		String inscritos="";
		//SACAMOS DE LA TABLA LOS ID'S DE CADA TABLA EL IMPORTE Y LA FECHA
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("consultarTablaCursosAlumnos"));
			sentencia.setString(1, codigoCurso);
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				sentencia=miConexion.prepareStatement(comandos.getProperty("selectOpcionPrincipal"));
				sentencia.setString(1, resultados.getString(2));
				resultadosAlumnos=sentencia.executeQuery();
				while(resultadosAlumnos.next()){
					 nombre=resultadosAlumnos.getString(1);
					 cursosA=new CursosAlumnos(resultados.getString(1),resultados.getString(2),resultados.getDate(3),resultados.getDouble(4),nombre);
					 cursosAlumnos.add(cursosA);
				}
			}
		} catch (SQLException e2) {
			System.out.println("ERROR AL CONSULTAR LA TABLA CURSOS ALUMNOS "+e2.getErrorCode()+e2.getMessage());
		}
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("consultarCursoMaestros"));//precio,plazas,inscritos y codigo de alumno
			sentencia.setString(1, codigoCurso);
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				c=new Cursos(resultados.getString(1),resultados.getDouble(2),resultados.getString(3),resultados.getString(4));
				cursos.add(c);
			}
		} catch (SQLException e) {
			System.out.println("Error al consultar en CURSOSALUMNOSDAO METODO CONSULTAR "+e.getErrorCode()+e.getMessage());
		}
		
		//LLAMAMOS A LA FUNCION QUE NOS SACA LOS SELECT
		pintarSelectAlumnosDeUnCurso(comandos, request,sesion);
		
		
		sesion.setAttribute("cursos", cursos);
		sesion.setAttribute("codigoCurso", codigoCurso);
		sesion.setAttribute("fechaActual", fechaHoy.getTime());
		sesion.setAttribute("cursosAlumnos", cursosAlumnos);
		sesion.setAttribute("inscritos", inscritos);
	}
	
	
	//FUNCION QUE DEVUELVA EL CODIGO DE TODOS LOS ALUMNOS QUE ESTEN EN UN CURSO
	
	public String devuelveCodigos(Properties comandos,HttpServletRequest request){
		String codigoCurso=request.getParameter("codigo");
		String cadena="";
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("devuelveCodigoAlumnos"));
			sentencia.setString(1, codigoCurso);
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				cadena+=resultados.getString(1)+",";
			}
		} catch (SQLException e) {
			System.out.println("ERROR AL DEVOLVER LOS CODIGOS ALUMNOS DE LA TABLA ALUMNOS "+e.getErrorCode()+e.getMessage());
		}
		if(cadena.length()==0){
			return "";
		}
		String codigos=cadena.substring(0,cadena.length()-1);
		
		return codigos;
	}
	
	//METODO QUE SACAMOS EL SELECT DE INSERTAR Y MODIFICAR CON LOS CODIGOS OBTENIDOS DE LA FUNCION OBTENER CÓDIGOS
	
	public void pintarSelectAlumnosDeUnCurso(Properties comandos, HttpServletRequest request, HttpSession sesion){
		Alumnos a=new Alumnos();
		ArrayList<Alumnos>alumnos=new ArrayList<Alumnos>();
		String codigoAlumno=devuelveCodigos(comandos,request);
		String sentenciaPreparada="select  secAlumno,nombre from alumnos where secAlumno not in (";
		String []cantidadInterrogantes=codigoAlumno.split(",");
		for(int i=0;i<cantidadInterrogantes.length;i++){
			sentenciaPreparada+="?,";
		}
		sentenciaPreparada=sentenciaPreparada.substring(0,sentenciaPreparada.length()-1);
		sentenciaPreparada+=")";
		//OPCIONES DEL SELECT RESTANTES
		try {
			sentencia=miConexion.prepareStatement(sentenciaPreparada);
			for(int i=0;i<cantidadInterrogantes.length;i++){
				sentencia.setString(i+1, cantidadInterrogantes[i]);
			}
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				a=new Alumnos(resultados.getString(1),resultados.getString(2));
				alumnos.add(a);
			}
		} catch (SQLException e) {
			System.out.println("Error al tratar el select de alumnos de un curso "+e.getErrorCode()+e.getMessage());
		}
		sesion.setAttribute("alumnos", alumnos);
	}
	
	
	
	//METODO QUE SIRVE TANTO PARA INSERTAR Y ACTUALIZAR EN CURSOSALUMNOS.JSP

	public void insertar(HttpSession sesion,
			Properties comandos, HttpServletRequest request) {
		String codigoCurso=request.getParameter("codigo");
		String codigoAlumno=request.getParameter("codigoA");
		String importe2=request.getParameter(("importe"));
		double importe=Double.parseDouble(importe2);
		Date fechaJava=new Date();
		java.sql.Date fecha=new java.sql.Date(fechaJava.getTime());
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("insertarCursosAlumnos"));
			sentencia.setString(1, codigoCurso);
			sentencia.setString(2, codigoAlumno);
			sentencia.setDate(3, fecha);
			sentencia.setDouble(4, importe);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar los alumnos en insertarAlumnosEnCursos "+e.getMessage()+e.getErrorCode());
		}
	}
	public void modificar(HttpSession sesion, Properties comandos,
			HttpServletRequest request) {
		String codigoCurso=request.getParameter("codigo");
		String codigoAlumno=request.getParameter("codigoA");
		String importe2=request.getParameter(("importe"));
		double importe=Double.parseDouble(importe2);
		Date fechaJava=new Date();
		java.sql.Date fecha=new java.sql.Date(fechaJava.getTime());
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarCursosAlumnos"));
			sentencia.setString(1, codigoCurso);
			sentencia.setString(2, codigoAlumno);
			sentencia.setDate(3, fecha);
			sentencia.setDouble(4, importe);
			sentencia.setString(5, codigoCurso);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar los alumnos en insertarAlumnosEnCursos "+e.getMessage()+e.getErrorCode());
		}
		
	}
	//ELIMINAR UN ALUMNO DE UN CURSO EN CONCRETO
	public void eliminar(HttpSession sesion, Properties comandos,
			HttpServletRequest request) {
		String codigoCurso=request.getParameter("codigo");
		String codigoAlumno=request.getParameter("codigoAlumnoEliminar");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarCursosAlumnos"));
			sentencia.setString(1, codigoCurso);
			sentencia.setString(2, codigoAlumno);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al eliminar el alumno asociado al curso "+e.getMessage()+e.getErrorCode());
		}
	}
	
	//CONSULTAMOS LOS CURSOS A LOS CUALES ESTÁ APUNTADO EL ALUMNO
	public void consultarAlumnosApuntadosCurso(HttpSession sesion,
			Properties comandos, HttpServletRequest request) {
		String codigo=request.getParameter("codigo");
		ArrayList<CursosAlumnos>cursos=new ArrayList<CursosAlumnos>();
		String nombre="";
		CursosAlumnos c=new CursosAlumnos();
		ResultSet nombreCurso;
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("listarCursosAlumno"));
			sentencia.setString(1, codigo);
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				sentencia=miConexion.prepareStatement(comandos.getProperty("nombreCurso"));
				sentencia.setString(1, resultados.getString(1));
				nombreCurso=sentencia.executeQuery();
				nombreCurso.next();
				nombre=nombreCurso.getString(1);
				c=new CursosAlumnos(resultados.getString(1),resultados.getString(2),resultados.getDate(3),resultados.getDouble(4),nombre);
				cursos.add(c);
			}
		} catch (SQLException e) {
			System.out.println("Error al listar los cursos de un alumno "+e.getMessage()+e.getErrorCode());
		}
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("nombreAlumno"));
			sentencia.setString(1, codigo);
			resultados=sentencia.executeQuery();
			resultados.next();
			nombre=resultados.getString(1);
			nombre=nombre.toUpperCase();
		} catch (SQLException e) {
			System.out.println("ERROR AL SACAR EL NOMBRE DEL ALUMNO "+e.getErrorCode()+e.getMessage());
		}
			
		
		sesion.setAttribute("alumnosApuntados", cursos);
		sesion.setAttribute("nombre", nombre);
	}
	public void actualizarInscritos(HttpSession sesion, Properties comandos,
			HttpServletRequest request) {//ACTUALIZAMOS LOS INSCRITOS
		String codigo=request.getParameter("codigo");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("contarAlumnosDetalle"));
			sentencia.setString(1, codigo);
			resultados=sentencia.executeQuery();
			resultados.next();
			sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarInscritos"));
			sentencia.setString(1, resultados.getString(1));
			sentencia.setString(2, codigo);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ERROR AL ACTUALIZAR INSCRITOS "+e.getMessage()+e.getErrorCode());
		}
	
	}
	
	
	//METODO QUE INSCRIBE EN USUARIOS DESDE usuarioRegistrado/cursos.html
	public int inscripcion(HttpServletRequest request, Properties comandos,
			HttpSession sesion) {
		// TODO Auto-generated method stub
		String idCurso = request.getParameter("id");
		String idUsuario = request.getParameter("idUs");
		String precio = request.getParameter("precio");
		Double importe  = Double.parseDouble(precio);
		Date fechaJava=new Date();
		java.sql.Date fecha=new java.sql.Date(fechaJava.getTime());
		String inscritos1 = "";
		int inscritos = 0;
		
		try {
			sentencia = miConexion.prepareStatement(comandos.getProperty("insertarCursosAlumnos"));
			sentencia.setString(1, idCurso);
			sentencia.setString(2, idUsuario);
			sentencia.setDate(3, fecha);
			sentencia.setDouble(4, importe);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al inscribirse en el curso "+e.getMessage()+e.getErrorCode());
			return -1;
		}
		
			
			try {
				sentencia = miConexion.prepareStatement(comandos.getProperty("inscritosCurso"));
				sentencia.setString(1, idCurso);
				resultados = sentencia.executeQuery();
				resultados.next();
				inscritos1 = resultados.getString(1);
				inscritos = Integer.parseInt(inscritos1);
				inscritos +=1;
			} catch (SQLException e1) {
				System.out.println("Error al devolver inscritos inscritos "+e1.getMessage()+e1.getErrorCode());
			}
			
			
		try {
			sentencia = miConexion.prepareStatement(comandos.getProperty("actualizarInscritos"));
			sentencia.setInt(1, inscritos);
			sentencia.setString(2, idCurso);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("ERROR AL ACTUALIZAR INSCRITOS "+e.getMessage()+e.getErrorCode());
		}
		return 0;
	}
	
}
