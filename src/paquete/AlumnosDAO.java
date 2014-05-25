package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AlumnosDAO {

	Connection miConexion;
	PreparedStatement sentencia;
	ResultSet resultados;
	Alumnos a=new Alumnos();
	

	public AlumnosDAO(Connection miConexion, PreparedStatement sentencia) {
		// TODO Auto-generated constructor stub
		this.miConexion=miConexion;
		this.sentencia=sentencia;
	}


	public int registrar(HttpServletRequest request, Properties comandos) {
		// TODO Auto-generated method stub
		String nombre=request.getParameter("nombre");
		String apellidos=request.getParameter("apellidos");
		String telefono=request.getParameter("telefono");
		String email=request.getParameter("email");
		String direccion="";
		String calle=request.getParameter("calle");
		String numero=request.getParameter("numero");
		String piso=request.getParameter("piso");
		String codigoPostal=request.getParameter("codigoPostal");
		String localidad=request.getParameter("localidad");
		String provincia=request.getParameter("provincia");
		direccion=calle+","+numero+","+piso+","+codigoPostal+","+localidad+","+provincia;
		String usuario=request.getParameter("usuario");
		String password=request.getParameter("password1");
		
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("seleccionarAlumnoExistente"));
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				if(usuario.equalsIgnoreCase(resultados.getString(1))){
					return 1;
				}
			}
		} catch (SQLException e1) {
			System.out.println("Error al listar alumnos para saber si el alumno que vamos a insertar se repite "+e1.getErrorCode()+e1.getMessage());
		}
		
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("insertarFormulario"));
			sentencia.setString(1, nombre);
			sentencia.setString(2, apellidos);
			sentencia.setString(3, telefono);
			sentencia.setString(4, email);
			sentencia.setString(5, direccion);
			sentencia.setString(6, usuario);
			sentencia.setString(7, password);
			sentencia.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Se ha productido un error al registrar los alumnos "+e.getMessage()+e.getErrorCode());
			if(e.getErrorCode()==0001){
				return 1;
			}
		}
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("insertarRoles"));
			sentencia.setString(1, "user");
			sentencia.setString(2, usuario);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Ha habido un error al insertar los usuarios-roles "+e.getMessage()+e.getErrorCode());
			if(e.getErrorCode()==0001){
			return 1;
			}
		}
		
		return 0;
}


	public void consultar(Properties comandos, HttpSession sesion) {
		// TODO Auto-generated method stub
		Alumnos a;
		ArrayList<Alumnos>alumnos=new ArrayList<Alumnos>();
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("listarAlumnos"));
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				a=new Alumnos(resultados.getString(1),resultados.getString(2),resultados.getString(3),resultados.getString(4),resultados.getString(5),resultados.getString(6),resultados.getString(7),resultados.getString(8));
				alumnos.add(a);
			}
		} catch (SQLException e) {
			System.out.println("Error al consultar "+e.getMessage());
		}
		sesion.setAttribute("listadoAlumnos", alumnos);
		
	}


	public void eliminar(Properties comandos, HttpServletRequest request) {
		String codigoAlumno=request.getParameter("codigo");
		String usuario=request.getParameter("usuario");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("contarProductosDetalle"));
			sentencia.setString(1, codigoAlumno);
			resultados=sentencia.executeQuery();
			resultados.next();
			if(resultados.getString(1)!="0"){
				sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarLineaProductosAlumnos"));
				sentencia.setString(1, codigoAlumno);
				sentencia.executeUpdate();
			}
		} catch (SQLException e1) {
			System.out.println("ERROR AL CONTAR DETALLE DE PRODUCTOS "+e1.getErrorCode()+e1.getMessage());
		}
			try {
				sentencia=miConexion.prepareStatement(comandos.getProperty("contarAlumnos"));
				sentencia.setString(1, codigoAlumno);
				resultados=sentencia.executeQuery();
				resultados.next();
				if(resultados.getString(1)!="0"){
					try {
						sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarDetalle"));//elimina el detalle de cursos alumnos
						sentencia.setString(1, codigoAlumno);
						sentencia.executeUpdate();
					} catch (SQLException e1) {
						System.out.println("Error al borrar los alumnos de cursos alumnos "+e1.getMessage()+e1.getErrorCode());
					}
				}
			} catch (SQLException e2) {
				System.out.println("ERROR AL CONTAR LOS ALUMNOS "+e2.getMessage()+e2.getErrorCode());
			}
			try {
				sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarUsuariosR"));
				sentencia.setString(1, usuario);
				sentencia.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al eliminar en la tabla users_roles "+e.getMessage());
			}
			try {
				sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarAlumnos"));
				sentencia.setString(1, codigoAlumno);
				sentencia.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error al eliminar en la tabla alumnos "+e.getMessage());
			}		
	}


	public void modificar(HttpSession sesion,Properties comandos, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String codigoAlumno=request.getParameter("codigo");
		String[]direccion;
		String nuevaDireccion="";
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("consultarSecuenciaAlumno"));
			sentencia.setString(1, codigoAlumno);
			resultados=sentencia.executeQuery();
			resultados.next();
			a.setSecAlumno(resultados.getString(1));
			a.setNombre(resultados.getString(2));
			a.setApellidos(resultados.getString(3));
			a.setTelefono(resultados.getString(4));;
			a.setEmail(resultados.getString(5));
			if(resultados.getString(6)==null){
				a.setCalle(nuevaDireccion);
				a.setNumero(nuevaDireccion);
				a.setPiso(nuevaDireccion);
				a.setCodigoPostal(nuevaDireccion);
				a.setLocalidad(nuevaDireccion);
				a.setProvincia(nuevaDireccion);
				a.setUsuario(resultados.getString(7));
				a.setContrasenya(resultados.getString(8));
			}else{
			direccion=resultados.getString(6).split(",");
			a.setCalle(direccion[0]);
			a.setNumero(direccion[1]);
			a.setPiso(direccion[2]);
			a.setCodigoPostal(direccion[3]);
			a.setLocalidad(direccion[4]);
			a.setProvincia(direccion[5]);
			a.setUsuario(resultados.getString(7));
			a.setContrasenya(resultados.getString(8));
			}
			sesion.setAttribute("modificarAlumnos",a);
		} catch (SQLException e) {
			System.out.println("Error al consultar la secuencia "+e.getMessage());
		}
	}


	public void actualizar(HttpServletRequest request, Properties comandos) {
		String codigo=request.getParameter("codigo");
		int codigoAlumno=Integer.parseInt(codigo);
		String nombre=request.getParameter("nombre");
		String apellidos=request.getParameter("apellidos");
		String telefono=request.getParameter("telefono");
		String email=request.getParameter("email");
		String direccion="";
		String calle=request.getParameter("calle");
		String numero=request.getParameter("numero");
		String piso=request.getParameter("piso");
		String codigoPostal=request.getParameter("codigoPostal");
		String localidad=request.getParameter("localidad");
		String provincia=request.getParameter("provincia");
		direccion=calle+","+numero+","+piso+","+codigoPostal+","+localidad+","+provincia;
		String usuario=request.getParameter("usuario");
		String password=request.getParameter("password1");
		String usuarioAntiguo=request.getParameter("nombreUsuario");
		
		
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarAlumnos"));
			sentencia.setString(1, nombre);
			sentencia.setString(2, apellidos);
			sentencia.setString(3, telefono);
			sentencia.setString(4, email);
			sentencia.setString(5, direccion);
			sentencia.setString(6, usuario);
			sentencia.setString(7, password);
			sentencia.setInt(8, codigoAlumno);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar "+e.getMessage());
		}
		
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarUsuariosRoles"));
			sentencia.setString(1, usuario);
			if(usuarioAntiguo.equalsIgnoreCase("gabriel")){
				sentencia.setString(2, "admin");
			}
			else{
			sentencia.setString(2, "user");
			}
			sentencia.setString(3, usuarioAntiguo);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Imposible actualizar la tabla users_roles "+e.getMessage());
		}
		
	}
	
}
