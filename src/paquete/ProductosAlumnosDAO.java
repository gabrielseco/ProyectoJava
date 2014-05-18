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

public class ProductosAlumnosDAO {

	 Connection miConexion;
	 PreparedStatement sentencia;
	 ResultSet resultados;

	public ProductosAlumnosDAO(Connection miConexion,
			PreparedStatement sentencia) {
		// TODO Auto-generated constructor stub
		this.miConexion=miConexion;
		this.sentencia=sentencia;
	}
	//SACAMOS EL NOMBRE Y EL PRECIO DE UN PRODUCTO Y LO METEMOS EN UN ARRAYLIST DE PRODUCTOSALUMNOS
	public void consultar(HttpServletRequest request, Properties comandos,
			HttpSession sesion) {
		String codigoProducto=request.getParameter("codigoProducto");
		ProductosAlumnos pa;
		ArrayList<ProductosAlumnos>prodAlum=new ArrayList<ProductosAlumnos>();
		GregorianCalendar fechaHoy=new GregorianCalendar();
		ProductosAlumnos productosA;
		ResultSet resultadosAlumnos;
		String nombre="";
		ArrayList<ProductosAlumnos>productosAlumnos=new ArrayList<ProductosAlumnos>();
		//SACAMOS DE LA TABLA LOS ID'S DE CADA TABLA EL IMPORTE Y LA FECHA
				try {
					sentencia=miConexion.prepareStatement(comandos.getProperty("consultarTablaProductosAlumnos"));
					sentencia.setString(1, codigoProducto);
					resultados=sentencia.executeQuery();
					while(resultados.next()){
						sentencia=miConexion.prepareStatement(comandos.getProperty("selectOpcionPrincipal"));
						sentencia.setString(1, resultados.getString(2));
						resultadosAlumnos=sentencia.executeQuery();
						while(resultadosAlumnos.next()){
							 nombre=resultadosAlumnos.getString(1);
							 productosA=new ProductosAlumnos(resultados.getString(1),resultados.getString(2),resultados.getDate(3),resultados.getDouble(4),nombre);
							 productosAlumnos.add(productosA);
						}
					}
				} catch (SQLException e2) {
					System.out.println("ERROR AL CONSULTAR LA TABLA CURSOS ALUMNOS "+e2.getErrorCode()+e2.getMessage());
				}
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("seleccionarNombreYPrecio"));
			sentencia.setString(1, codigoProducto);
			resultados=sentencia.executeQuery();
			resultados.next();
			pa=new ProductosAlumnos(resultados.getString(1).toUpperCase(),resultados.getDouble(2));
			prodAlum.add(pa);
			
		} catch (SQLException e) {
			System.out.println("Error al consultar el nombre,precio del producto"+e.getErrorCode()+e.getMessage());
		}
		
		//LLAMAMOS A LA FUNCION QUE NOS SACA LOS SELECT
		pintarSelectAlumnosDeUnCurso(comandos, request,sesion);
		
		sesion.setAttribute("fechaActual", fechaHoy.getTime());
		sesion.setAttribute("codigoProducto", codigoProducto);
		sesion.setAttribute("productosAl", prodAlum);
		sesion.setAttribute("productosAlumnos", productosAlumnos);
	}
	
	//FUNCION QUE NOS PINTA EL SELECT DE LOS ALUMNOS DE UN PRODUCTO
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
	
	//FUNCION QUE DEVUELVA EL CODIGO DE TODOS LOS ALUMNOS QUE ESTEN EN PRODUCTOS
	
	public String devuelveCodigos(Properties comandos,HttpServletRequest request){
			String codigoCurso=request.getParameter("codigoProducto");
			String cadena="";
			try {
				sentencia=miConexion.prepareStatement(comandos.getProperty("devuelveCodigosAlumnosProductos"));
				sentencia.setString(1, codigoCurso);
				resultados=sentencia.executeQuery();
				while(resultados.next()){
					cadena+=resultados.getString(1)+",";
				}
			} catch (SQLException e) {
				System.out.println("ERROR AL DEVOLVER LOS CODIGOS ALUMNOS DE LA TABLA PRODUCTOS "+e.getErrorCode()+e.getMessage());
			}
			if(cadena.length()==0){
				return "";
			}
			String codigos=cadena.substring(0,cadena.length()-1);
			
			return codigos;
		}
	
	//Metodo para insertar alumnos en la tabla productosalumnos
	public void insertar(HttpServletRequest request, Properties comandos,
			HttpSession sesion) {
		String codigoProducto=request.getParameter("codigoProducto");
		String codigoAlumno=request.getParameter("codigoAlumno");
		String importe2=request.getParameter("importe");
		double importe=Double.parseDouble(importe2);
		Date fechaJava=new Date();
		java.sql.Date fecha=new java.sql.Date(fechaJava.getTime());
		
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("insertarProductosAlumnos"));
			sentencia.setString(1, codigoProducto);
			sentencia.setString(2, codigoAlumno);
			sentencia.setDate(3, fecha);
			sentencia.setDouble(4, importe);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar en productosalumnos "+e.getErrorCode()+e.getMessage() );
		}
	}	
	//Eliminar un producto del detalle
	public void eliminar(HttpSession sesion, Properties comandos,
			HttpServletRequest request) {
		String codigoProducto=request.getParameter("codigoProducto");
		String codigoAlumno=request.getParameter("codigoAlumno");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarProductosAlumnos"));
			sentencia.setString(1, codigoProducto);
			sentencia.setString(2, codigoAlumno);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al eliminar el alumno asociado al curso "+e.getMessage()+e.getErrorCode());
		}
	}
	public void modificar(HttpSession sesion, Properties comandos,
			HttpServletRequest request) {
		String codigoProducto=request.getParameter("codigoProducto");
		String codigoAlumno=request.getParameter("codigoAlumno");
		String importe2=request.getParameter(("importe"));
		double importe=Double.parseDouble(importe2);
		Date fechaJava=new Date();
		java.sql.Date fecha=new java.sql.Date(fechaJava.getTime());
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarProductosAlumnos"));
			sentencia.setString(1, codigoProducto);
			sentencia.setString(2, codigoAlumno);
			sentencia.setDate(3, fecha);
			sentencia.setDouble(4, importe);
			sentencia.setString(5, codigoProducto);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar los alumnos en insertarAlumnosEnCursos "+e.getMessage()+e.getErrorCode());
		}
		
		
	}
	public void consultarProductosCompradosPorCurso(HttpSession sesion,
			Properties comandos, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String codigo=request.getParameter("codigo");
		ArrayList<ProductosAlumnos>productos=new ArrayList<ProductosAlumnos>();
		String nombre="";
		ProductosAlumnos p=new ProductosAlumnos();
		ResultSet nombreCurso;
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("listarProductosAlumno"));
			sentencia.setString(1, codigo);
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				sentencia=miConexion.prepareStatement(comandos.getProperty("nombreProducto"));
				sentencia.setString(1, resultados.getString(1));
				nombreCurso=sentencia.executeQuery();
				nombreCurso.next();
				nombre=nombreCurso.getString(1);
				p=new ProductosAlumnos(resultados.getString(1),resultados.getString(2),resultados.getDate(3),resultados.getDouble(4),nombre);
				productos.add(p);
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
		sesion.setAttribute("alumnosApuntados", productos);
		sesion.setAttribute("nombre", nombre);
		
	}
}
