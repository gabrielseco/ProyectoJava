package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		String numUnidades1=request.getParameter("numUnidades");
		int numUnidades=Integer.parseInt(numUnidades1);
		String precio1=request.getParameter("precio");
		double precio=Double.parseDouble(precio1);
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("insertarProductos"));
			sentencia.setString(1,codigoProducto);
			sentencia.setInt(2, numUnidades);
			sentencia.setDouble(3,precio);
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
				p=new Productos(resultados.getString(1),resultados.getString(2),resultados.getInt(3),resultados.getDouble(4));
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
			p.setCodigo(resultados.getString(2));
			p.setNumUnidades(resultados.getInt(3));
			p.setPrecio(resultados.getDouble(4));			
		} catch (SQLException e) {
			System.out.println("Error al modificar productos "+e.getMessage()+","+e.getErrorCode());
		}
		sesion.setAttribute("modificarProductos", p);
		
	}
	public void actualizar(HttpServletRequest request, Properties comandos) {
		// TODO Auto-generated method stub
		String codigo1=request.getParameter("codigoModificar");
		int codigo=Integer.parseInt(codigo1);
		String codigoProducto=request.getParameter("codigo");
		String numUnidades1=request.getParameter("numUnidades");
		int numUnidades=Integer.parseInt(numUnidades1);
		String precio1=request.getParameter("precio");
		double precio=Double.parseDouble(precio1);
		
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("actulizarProductos"));
			sentencia.setString(1, codigoProducto);
			sentencia.setInt(2, numUnidades);
			sentencia.setDouble(3, precio);
			sentencia.setInt(4, codigo);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar "+e.getMessage());
		}
	}
}
