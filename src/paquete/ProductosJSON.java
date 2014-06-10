package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ProductosJSON {

	Connection miConexion;
	PreparedStatement sentencia;
	ResultSet resultados;
	public ProductosJSON(Connection miConexion, PreparedStatement sentencia) {
		// TODO Auto-generated constructor stub
		this.miConexion=miConexion;
		this.sentencia=sentencia;
	}
	public void consultar(HttpServletRequest request, Properties comandos, HttpSession sesion) {
		String cadena = "[\n";

		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("listarProductos"));
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				cadena += "{\n";
				cadena += "\"secProducto\":\""+resultados.getString(1)+"\",\n";
				cadena += "\"codigo\":\""+resultados.getString(2)+"\",\n";
				cadena += "\"nombre\":\""+resultados.getString(3)+"\",\n";
				cadena += "\"numUnidades\":\""+resultados.getInt(4)+"\",\n";
				cadena += "\"precio\":\""+resultados.getDouble(5)+"\",\n";
				cadena += "\"descripcion\":\""+resultados.getString(6)+"\",\n";
				cadena += "\"imagen\":\"../../productos/"+resultados.getString(7)+"\"\n";
				cadena += "},\n";
			}
		} catch (SQLException e) {
			System.out.println("ERROR AL TRATAR EL JSON DE PRODUCTOS "+e.getErrorCode()+e.getMessage());
		}
		cadena = cadena.substring(0,cadena.lastIndexOf(",")-1);
		cadena = cadena +"}\n";
		cadena = cadena + "]";

		
		sesion.setAttribute("productosJSON", cadena);
		
	}

}
