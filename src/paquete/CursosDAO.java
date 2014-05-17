package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CursosDAO {
	Connection miConexion;
	PreparedStatement sentencia;
	ResultSet resultados;
	public CursosDAO(Connection miConexion, PreparedStatement sentencia) {
		// TODO Auto-generated constructor stub
		this.miConexion=miConexion;
		this.sentencia=sentencia;
	}
	public int registrar(HttpServletRequest request, Properties comandos) {
		String nombreCurso=request.getParameter("nombreCurso");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String fechaInicio1=request.getParameter("fechaInicio");
		Date fechaInicio2;
		java.sql.Date fechaInicio=null;
		java.sql.Date fechaFinal=null;
		try {
			fechaInicio2 = sdf.parse(fechaInicio1);
			 fechaInicio=new java.sql.Date(fechaInicio2.getTime()); 
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String fechaFinal1=request.getParameter("fechaFinal");
		Date fechaFinal2;
		
		try {
			fechaFinal2 = sdf.parse(fechaFinal1);
			fechaFinal=new java.sql.Date(fechaFinal2.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String horario=request.getParameter("horario");
		String duracion1=request.getParameter("duracion");
		double duracion=Double.parseDouble(duracion1);
		String precio1=request.getParameter("precio");
		double precio=Double.parseDouble(precio1);
		String plazas=request.getParameter("plazas");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("insertarCursos"));
			sentencia.setString(1, nombreCurso);
			sentencia.setDate(2, fechaInicio);
			sentencia.setDate(3, fechaFinal);
			sentencia.setString(4, horario);
			sentencia.setDouble(5, duracion);
			sentencia.setDouble(6, precio);
			sentencia.setString(7, plazas);
			sentencia.setString(8, "0");
			sentencia.executeUpdate();
		} catch (SQLException e) {
            System.out.println("Error al insertar cursos "+e.getMessage());
			if(e.getErrorCode()==0001){
				return 1;
			}
		}
		return 0;
	}
	public void consultar(HttpSession sesion, Properties comandos, HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cursos c;
		ArrayList<Cursos>cursos=new ArrayList<Cursos>();
		String codigoCurso=request.getParameter("codigo");
		String inscritos="";
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("listarCursos"));
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				c=new Cursos(resultados.getString(1),resultados.getString(2),resultados.getDate(3),resultados.getDate(4),resultados.getString(5),resultados.getDouble(6),resultados.getDouble(7),resultados.getString(8),resultados.getString(9));
				cursos.add(c);
				codigoCurso=resultados.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Error al consultar cursos "+e.getMessage());
		}
		//INSCRITOS A PARTIR DEL NUMERO  EXISTENTES EN LA TABLA CURSOSALUMNOS DE ESE CURSO
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("contarAlumnos"));
			sentencia.setString(1, codigoCurso);
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				inscritos=resultados.getString(1);
				sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarInscritos"));
				sentencia.setString(1, inscritos);
				sentencia.setString(2, codigoCurso);
				sentencia.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println("ERROR AL CONTAR LOS REGISTROS DE LOS ALUMNOS : "+e.getMessage()+e.getErrorCode());
		}
		
		sesion.setAttribute("listadoCursos", cursos);
		sesion.setAttribute("inscritos", inscritos);
	}
	public void eliminar(Properties comandos, HttpServletRequest request){
		String codigoCurso=request.getParameter("codigo");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("contarAlumnosDetalle"));
			sentencia.setString(1, codigoCurso);
			resultados=sentencia.executeQuery();
			resultados.next();
			if(resultados.getString(1)!="0"){
				sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarLineaAlumnos"));
				sentencia.setString(1, codigoCurso);
				sentencia.executeUpdate();
			}
		} catch (SQLException e1) {
			System.out.println("ERROR AL CONOCER LOS ALUMNOS VINCULADOS A UN CURSO EN ELIMINAR "+e1.getErrorCode()+e1.getMessage());
		}
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarCursos"));
			sentencia.setString(1,codigoCurso);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("No ha sido posible eliminar "+e.getMessage());
		}
		
	}
	public void modificar(HttpSession sesion, Properties comandos,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Cursos c=new Cursos();
		String codigoCurso=request.getParameter("codigo");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("consultarSecuenciaCurso"));
			sentencia.setString(1, codigoCurso);
			resultados=sentencia.executeQuery();
			resultados.next();
			c.setSecCurso(resultados.getString(1));
			c.setNombreCurso(resultados.getString(2));
			c.setFechaInicio(resultados.getDate(3));
			c.setFechaFinal(resultados.getDate(4));
			c.setHorario(resultados.getString(5));
			c.setDuracion(resultados.getDouble(6));
			c.setPrecio(resultados.getDouble(7));
			c.setPlazas(resultados.getString(8));
			c.setInscritos(resultados.getString(9));
		} catch (SQLException e) {
			System.out.println("Error al modificar "+e.getMessage());
		}
		sesion.setAttribute("modificarCursos", c);
	}
	public void actualizar(HttpServletRequest request, Properties comandos) {
		// TODO Auto-generated method stub
		String codigoCurso=request.getParameter("codigo");
		String nombreCurso=request.getParameter("nombreCurso");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String fechaInicio1=request.getParameter("fechaInicio");
		Date fechaInicio2;
		java.sql.Date fechaInicio=null;
		java.sql.Date fechaFinal=null;
		try {
			fechaInicio2 = sdf.parse(fechaInicio1);
			 fechaInicio=new java.sql.Date(fechaInicio2.getTime()); 
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String fechaFinal1=request.getParameter("fechaFinal");
		Date fechaFinal2;
		
		try {
			fechaFinal2 = sdf.parse(fechaFinal1);
			fechaFinal=new java.sql.Date(fechaFinal2.getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String horario=request.getParameter("horario");
		String duracion1=request.getParameter("duracion");
		double duracion=Double.parseDouble(duracion1);
		String precio1=request.getParameter("precio");
		double precio=Double.parseDouble(precio1);
		String plazas=request.getParameter("plazas");
		String inscritos=request.getParameter("inscritos");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarCursos"));
			sentencia.setString(1, nombreCurso);
			sentencia.setDate(2, fechaInicio);
			sentencia.setDate(3, fechaFinal);
			sentencia.setString(4, horario);
			sentencia.setDouble(5, duracion);
			sentencia.setDouble(6, precio);
			sentencia.setString(7, plazas);
			sentencia.setString(8, inscritos);
			sentencia.setString(9, codigoCurso);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar en cursos "+e.getMessage());
		}
		
	}
}
