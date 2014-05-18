package paquete;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Blob;
@MultipartConfig(maxFileSize = 16177215) // upload file up to 16MB  
public class ProductosDAO {

	Connection miConexion;
	PreparedStatement sentencia;
	ResultSet resultados;
	public ProductosDAO(Connection miConexion, PreparedStatement sentencia) {
		this.miConexion=miConexion;
		this.sentencia=sentencia;
	}
	public int registrar(Properties comandos, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String codigoProducto=request.getParameter("codigo");
		String nombre=request.getParameter("nombre");
		String numUnidades1=request.getParameter("numUnidades");
		int numUnidades=Integer.parseInt(numUnidades1);
		String precio1=request.getParameter("precio");
		double precio=Double.parseDouble(precio1);
		String descripcion=request.getParameter("descripcion");
		String imagenDirectorio=request.getParameter("imagen");
		System.out.println("el directorio es: "+imagenDirectorio);
		File subirImagen=new File("calculadora.PNG");
		FileInputStream inputStream=null;
		try {
			 inputStream=new FileInputStream(subirImagen);
		} catch (FileNotFoundException e1) {
			System.out.println("Fichero no encontrado en registrar "+e1.getMessage());
		}
		
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("insertarProductos"));
			sentencia.setString(1,codigoProducto);
			sentencia.setString(2, nombre);
			sentencia.setInt(3, numUnidades);
			sentencia.setDouble(4,precio);
			sentencia.setString(5, descripcion);
			sentencia.setBinaryStream(6, (InputStream)inputStream,(int) subirImagen.length());
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar "+e.getMessage());
			if(e.getErrorCode()==0001){
				return 1;
			}
		}
		return 0;
	}
	public void consultar(HttpSession sesion, Properties comandos) {
		// TODO Auto-generated method stub
		Productos p;
		ArrayList<Productos>productos=new ArrayList<Productos>();
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("listarProductos"));
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				p=new Productos(resultados.getString(1),resultados.getString(2),resultados.getString(3),resultados.getInt(4),resultados.getDouble(5));
				productos.add(p);
			}
		} catch (SQLException e) {
			System.out.println("Error al listar productos "+e.getMessage());
		}
		sesion.setAttribute("listadoProductos", productos);
	}
	public void eliminar(HttpServletRequest request, Properties comandos) {
		// TODO Auto-generated method stub
		String codigo=request.getParameter("codigo");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarProductos"));
			sentencia.setString(1, codigo);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al eliminar productos "+e.getMessage());
		}
		
	}
	public void modificar(HttpSession sesion, Properties comandos,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Productos p=new Productos();
		String codigoProducto=request.getParameter("codigo");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("consultarSecuenciaProducto"));
			sentencia.setString(1,codigoProducto);
			resultados=sentencia.executeQuery();
			resultados.next();
			p.setSecProducto(resultados.getString(1));
			p.setNombre(resultados.getString(2));
			p.setCodigo(resultados.getString(3));
			p.setNumUnidades(resultados.getInt(4));
			p.setPrecio(resultados.getDouble(5));
			p.setDescripcion(resultados.getString(6));
			p.setImagen((Blob) resultados.getBlob(7));
		} catch (SQLException e) {
			System.out.println("Error al modificar productos "+e.getMessage()+","+e.getErrorCode());
		}
		sesion.setAttribute("modificarProductos", p);
		
	}
	public void actualizar(HttpServletRequest request, Properties comandos) {
		// TODO Auto-generated method stub
		String codigo1=request.getParameter("codigoModificar");
		int codigo=Integer.parseInt(codigo1);
		String nombre=request.getParameter("nombre");
		String codigoProducto=request.getParameter("codigo");
		String numUnidades1=request.getParameter("numUnidades");
		int numUnidades=Integer.parseInt(numUnidades1);
		String precio1=request.getParameter("precio");
		double precio=Double.parseDouble(precio1);
		String descripcion=request.getParameter("descripcion");
		String imagenDirectorio=request.getParameter("imagen");
		System.out.println("el directorio es: "+imagenDirectorio);
		File subirImagen=new File(imagenDirectorio);
		FileInputStream inputStream=null;
		try {
			 inputStream=new FileInputStream(subirImagen);
		} catch (FileNotFoundException e1) {
			System.out.println("Fichero no encontrado en registrar "+e1.getMessage());
		}
		
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarProductos"));
			sentencia.setString(1, codigoProducto);
			sentencia.setString(2, nombre);
			sentencia.setInt(3, numUnidades);
			sentencia.setDouble(4, precio);
			sentencia.setString(5, descripcion);
			sentencia.setBinaryStream(6, (InputStream)inputStream,(int) subirImagen.length());
			sentencia.setInt(7, codigo);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar "+e.getMessage());
		}
	}
}
