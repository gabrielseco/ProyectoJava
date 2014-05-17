package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductosAlumnosDAO {

	 Connection miConexion;
	 PreparedStatement sentencia;

	public ProductosAlumnosDAO(Connection miConexion,
			PreparedStatement sentencia) {
		// TODO Auto-generated constructor stub
		this.miConexion=miConexion;
		this.sentencia=sentencia;
	}
	
	

}
