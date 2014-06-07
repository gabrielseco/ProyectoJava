package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class LoginDAO {

	Connection miConexion;
	PreparedStatement sentencia;
	ResultSet resultados;
	
	public LoginDAO(Connection miConexion, PreparedStatement sentencia) {
		// TODO Auto-generated constructor stub
		this.miConexion=miConexion;
		this.sentencia=sentencia;
	}

	public int acceso(HttpServletRequest request, Properties comandos) {
		// TODO Auto-generated method stub
		String usuario=request.getParameter("usuario");
		String password=request.getParameter("password");
		int size;
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("login"));
			sentencia.setString(1,usuario);
			sentencia.setString(2, password);
			resultados=sentencia.executeQuery();
			if(resultados!=null){
				resultados.last();  
				size = resultados.getRow(); 
				if(size == 0){
					return 1;
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Error al hacer login "+e.getErrorCode()+e.getMessage());
			return 1;
		}
			return 0;
	}
	
	
	
	

}
