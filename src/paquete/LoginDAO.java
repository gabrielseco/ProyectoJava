package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginDAO {

	Connection miConexion;
	PreparedStatement sentencia;
	ResultSet resultados;
	
	public LoginDAO(Connection miConexion, PreparedStatement sentencia) {
		// TODO Auto-generated constructor stub
		this.miConexion=miConexion;
		this.sentencia=sentencia;
	}

	public int acceso(HttpServletRequest request, Properties comandos, HttpSession sesion) {
		// TODO Auto-generated method stub
		String usuario=request.getParameter("usuario");
		String password=request.getParameter("password");
		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("login"));
			sentencia.setString(1,usuario);
			sentencia.setString(2, password);
			resultados=sentencia.executeQuery();
			resultados.next();
			if(resultados!=null){
				return Integer.parseInt(resultados.getString(1));

			}
			
		} catch (SQLException e) {
			System.out.println("Error al hacer login "+e.getErrorCode()+e.getMessage());
			return -1;
		}
		return -1;
	}
	
	
	
	

}
