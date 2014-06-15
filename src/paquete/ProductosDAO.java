package paquete;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

import javax.servlet.ServletContext;
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
	
	
	//METODO REGISTRAR QUE RECIBE UN FORMULARIO MULTIPART/FORM DATA.
	//UTILIZO LA COLECCIÓN PART QUE NOS PERMITE RECUPERAR UN FICHERO
	//CREAMOS UNA CARPETA QUE ALMACENARÁ LAS IMAGENES
	//COMPROBAMOS QUE SI EL TAMAÑO DE LA COLECCION ES 0 SINO SE SUBE UNA CADENA NULA
	//SE SUBE UNA CADENA RANDOM DE 5 CARACTERES CON SU EXTENSION
	
	
	
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
      
        ServletContext servletContext = request.getSession().getServletContext();
		String absoluteDiskPath = servletContext.getRealPath(SAVE_DIR);
		
        Part filePart = request.getPart("imagen");
        if(filePart.getSize()!=0){
        	fileName = extractFileName(filePart);
            String extension=fileName.substring(fileName.lastIndexOf("."),fileName.length());
            char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            String output = sb.toString();
            fileName=output+extension;
            filePart.write(absoluteDiskPath + File.separator + fileName);
        }
        
 
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("insertarProductos"));
			sentencia.setString(1,codigoProducto);
			sentencia.setString(2, nombre);
			sentencia.setInt(3, numUnidades);
			sentencia.setDouble(4,precio);
			sentencia.setString(5, descripcion);
			sentencia.setString(6, fileName);
			java.util.Date fecha = new java.util.Date();
			sentencia.setTimestamp(7,new Timestamp(fecha.getTime()));
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar "+e.getMessage());
			if(e.getErrorCode()==0001){
				return 1;
			}
		}
		return 0;
	}
	
	//METODO CONSULTAR QUE ACUMULAMOS NUESTRO RESULTADOS DEVUELTOS EN UN ARRAYLIST PARA LUEGO MOSTRARLOS EN NUESTRA VISTA (LISTADOPRODUCTOS.JSP)
	
	
	public void consultar(HttpSession sesion, Properties comandos) throws UnsupportedEncodingException {
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
	
	
	//METODO QUE ELIMINA PRIMERO LOS DETALLES LUEGO LA IMAGEN SI LA HUBIESE Y FINALMENTE EL PRODUCTO
	public void eliminar(HttpServletRequest request, Properties comandos) {
		// TODO Auto-generated method stub
		String codigo=request.getParameter("codigo");
		String imagen="";//Variable que almacenara la imagen que recuperamos y vamos a eliminar
		 ServletContext servletContext = request.getSession().getServletContext();
			String absoluteDiskPath = servletContext.getRealPath(SAVE_DIR);
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
			sentencia=miConexion.prepareStatement(comandos.getProperty("consultarFotoProducto"));
			sentencia.setString(1, codigo);
			resultados=sentencia.executeQuery();
			resultados.next();
			imagen=resultados.getString(1);
	        //String ruta = "C:"+File.separator+File.separator+"Users"+File.separator+"Gabriel"+File.separator+"Desktop"+File.separator+"FP  SEGUNDO AÑO"+File.separator+"PROYECTO"+File.separator+"Proyecto"+File.separator+"WebContent"+File.separator+SAVE_DIR+File.separator+imagen;
			File fichero=new File(absoluteDiskPath+File.separator+imagen);
			fichero.delete();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("eliminarProductos"));
			sentencia.setString(1, codigo);
			sentencia.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al eliminar productos "+e.getMessage());
		}
		
	}
	
	
	//METODO QUE ALMACENA EN UN OBJETO EL RESULTADO DEVUELTO DE UNA SECUENCIA DE PRODUCTO DETERMINADA
	//RECOGEMOS EL PATH EN EL QUE METEMOS EL PRODUCTO Y LE CONCATENAMOS EL STRING DE LA IMAGEN IGUAL QUE EL MÉTODO ELIMINAR
	//DESPUES PINTAMOS LA VISTA EN MODIFICARPRODUCTOS.JSP
	//SI NO HAY IMAGEN PONEMOS UNA IMAGEN PREDEFINIDA
	
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
			if (resultados.getString(7).equals("") ) {
				p.setImagen("thumbnail.png");
			}
			else{
				p.setImagen(resultados.getString(7));
			}
		} catch (SQLException e) {
			System.out.println("Error al modificar productos "+e.getMessage()+","+e.getErrorCode());
		}
		
		sesion.setAttribute("modificarProductos", p);
		
	}
	public void actualizar(HttpServletRequest request, Properties comandos) throws IllegalStateException, IOException, ServletException {
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
		String nombreImagen=request.getParameter("nombreImagen");
        Part filePart = request.getPart("imagen");
		String fileName="";
		ServletContext servletContext = request.getSession().getServletContext();
		String absoluteDiskPath = servletContext.getRealPath(SAVE_DIR);
		
        if(filePart.getSize()!=0){//si la imagen ha sido escogida
        	File fichero=new File(absoluteDiskPath+File.separator+nombreImagen);
        	if(fichero.exists()&& !nombreImagen.equals("thumbnail.png")){//y si ya habia una se borra la anterior
    			fichero.delete();
    			fileName = extractFileName(filePart);
                String extension=fileName.substring(fileName.lastIndexOf("."),fileName.length());
                char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < 5; i++) {
                    char c = chars[random.nextInt(chars.length)];
                    sb.append(c);
                }
                String output = sb.toString();
                fileName=output+extension;
                filePart.write(absoluteDiskPath + File.separator + fileName);
    		}
        	else{
        		fileName = extractFileName(filePart);
                String extension=fileName.substring(fileName.lastIndexOf("."),fileName.length());
                char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
                StringBuilder sb = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < 5; i++) {
                    char c = chars[random.nextInt(chars.length)];
                    sb.append(c);
                }
                String output = sb.toString();
                fileName=output+extension;
                filePart.write(absoluteDiskPath + File.separator + fileName);
        	}
        }
		
		if(filePart.getSize()!=0){
			try {
				sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarProductos"));
				sentencia.setString(1, codigoProducto);
				sentencia.setString(2, nombre);
				sentencia.setInt(3, numUnidades);
				sentencia.setDouble(4, precio);
				sentencia.setString(5, descripcion);
				sentencia.setString(6, fileName);
				java.util.Date fecha = new java.util.Date();
				sentencia.setTimestamp(7, new Timestamp(fecha.getTime()));
				sentencia.setInt(8, codigo);
				sentencia.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al actualizar "+e.getMessage());
			}
		}
		else{
			try {
				sentencia=miConexion.prepareStatement(comandos.getProperty("actualizarProductosSinImagen"));
				sentencia.setString(1, codigoProducto);
				sentencia.setString(2, nombre);
				sentencia.setInt(3, numUnidades);
				sentencia.setDouble(4, precio);
				sentencia.setString(5, descripcion);
				java.util.Date fecha = new java.util.Date();
				sentencia.setTimestamp(6, new Timestamp(fecha.getTime()));
				sentencia.setInt(7, codigo);
				sentencia.executeUpdate();
			} catch (SQLException e) {
				System.out.println("Error al actualizar "+e.getMessage());
			}
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
