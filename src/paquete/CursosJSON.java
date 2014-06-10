package paquete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CursosJSON {

	Connection miConexion;
	PreparedStatement sentencia;
	ResultSet resultados;

	
	public CursosJSON(Connection miConexion, PreparedStatement sentencia) {
		// TODO Auto-generated constructor stub
		this.miConexion = miConexion;
		this.sentencia  = sentencia;
	}

	public void consultar(HttpServletRequest request, Properties comandos,
			HttpSession sesion) {
		
		String cadena = "[\n";

		try {
			sentencia=miConexion.prepareStatement(comandos.getProperty("listarCursos"));
			resultados=sentencia.executeQuery();
			while(resultados.next()){
				cadena += "{\n";
				cadena += "\"secCurso\":\""+resultados.getString(1)+"\",\n";
				cadena += "\"nombre\":\""+resultados.getString(2)+"\",\n";
				cadena += "\"fechaInicio\":\""+resultados.getDate(3)+"\",\n";
				cadena += "\"fechaFinal\":\""+resultados.getDate(4)+"\",\n";
				cadena += "\"horario\":\""+resultados.getString(5)+"\",\n";
				cadena += "\"duracion\":\""+resultados.getDouble(6)+"\",\n";
				cadena += "\"precio\":\""+resultados.getDouble(7)+"\",\n";
				cadena += "\"plazas\":\""+resultados.getInt(8)+"\",\n";
				cadena += "\"inscritos\":\""+resultados.getInt(9)+"\",\n";
				cadena += "\"imagen\":\"../../cursos/"+resultados.getString(10)+"\"\n";
				cadena += "},\n";
			}
		} catch (SQLException e) {
			System.out.println("ERROR AL TRATAR EL JSON DE PRODUCTOS "+e.getErrorCode()+e.getMessage());
		}
		cadena = cadena.substring(0,cadena.lastIndexOf(",")-1);
		cadena = cadena +"}\n";
		cadena = cadena + "]";

		
		sesion.setAttribute("cursosJSON", cadena);
		
		
		
	}

}
