package paquete;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

public class ProductosDAO {
	private static final String SAVE_DIR = "productos";
	Connection miConexion;
	PreparedStatement sentencia;
	ResultSet resultados;
	public ProductosDAO(Connection miConexion, PreparedStatement sentencia) {
		this.miConexion=miConexion;
		this.sentencia=sentencia;
	}
	public int registrar(Properties comandos, HttpServletRequest request) throws IOException,ServletException {
		// TODO Auto-generated method stub
		String codigoProducto=request.getParameter("codigo");
		String nombre=request.getParameter("nombre");
		String numUnidades1=request.getParameter("numUnidades");
		int numUnidades=Integer.parseInt(numUnidades1);
		String precio1=request.getParameter("precio");
		double precio=Double.parseDouble(precio1);
		String descripcion=request.getParameter("descripcion");
		String fileName="";
		// gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
         
        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        Part filePart = request.getPart("imagen");
        if(filePart.getSize()!=0){
        	 fileName = extractFileName(filePart);
            String extension=fileName.substring(fileName.indexOf("."),fileName.length());
            fileName=fileName.substring(0,5)+extension;
            filePart.write(savePath + File.separator + fileName);
        }
        
 
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("insertarProductos"));
			sentencia.setString(1,codigoProducto);
			sentencia.setString(2, nombre);
			sentencia.setInt(3, numUnidades);
			sentencia.setDouble(4,precio);
			sentencia.setString(5, descripcion);
			sentencia.setString(6, fileName);
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
			sentencia=miConexion.prepareStatement(comandos.getProperty("contarProductosDetalle"));
			sentencia.setString(1, codigo);
			resultados=sentencia.executeQuery();
			resultados.next();
			if(resultados.getString(1)!="0"){
				sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarLineaProductos"));
				sentencia.setString(1, codigo);
				sentencia.executeUpdate();
			}
		} catch (SQLException e1) {
			System.out.println("ERROR AL CONTAR DETALLE DE PRODUCTOS "+e1.getErrorCode()+e1.getMessage());
		}
		
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
			p.setImagen(resultados.getString(7));
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
		
		
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarProductos"));
			sentencia.setString(1, codigoProducto);
			sentencia.setString(2, nombre);
			sentencia.setInt(3, numUnidades);
			sentencia.setDouble(4, precio);
			sentencia.setString(5, descripcion);
			sentencia.setString(6, "hola");
			sentencia.setInt(7, codigo);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al actualizar "+e.getMessage());
		}
	}
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		return "";
	}
}
