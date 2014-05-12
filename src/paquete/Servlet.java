package paquete;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection miConexion;
	PreparedStatement sentencia;
	Statement sentenciaSQL;
	ResultSet resultados;
	Properties comandos;
	AlumnosDAO alumnos;
	ProductosDAO productos;
	CursosDAO cursos;
	CursosAlumnosDAO cursosAlumnos;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No has importado la libreria"+e.getMessage());
		}
			String url="jdbc:mysql://localhost/mydb";
		try {
			miConexion=DriverManager.getConnection(url,"gabriel","temporal");
			sentenciaSQL=miConexion.createStatement();
		} catch (SQLException e) {
			System.out.println("Has introducido mal usuario/contraseña"+e.getMessage());
		}
		System.out.println("Conexion OK");
		comandos=new Properties();
		try {
			comandos.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("script.properties"));
			System.out.println("Properties cogido");
		} catch (FileNotFoundException e) {
			System.out.println("Fichero properties no encontrado");
		} catch (IOException e) {
			System.out.println("error fichero properties");
		}
		alumnos=new AlumnosDAO(miConexion,sentencia);
		cursos=new CursosDAO(miConexion,sentencia);
		productos=new ProductosDAO(miConexion,sentencia);
		cursosAlumnos=new CursosAlumnosDAO(miConexion,sentencia);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession sesion=request.getSession();
		String submit=request.getParameter("submit");
		switch(submit){
		case "RegistroAlumnos":
			response.sendRedirect("protegido/admin/registroAlumnos.html");
			break;
		case "Enviar Registro Alumnos":
			if(alumnos.registrar(request,comandos)==0){
				alumnos.consultar(comandos,sesion);
				response.sendRedirect("protegido/admin/listadoAlumnos.jsp");
			}
			else{
				response.sendRedirect("error.jsp?mensajeError=Este alumno ya existe");
			}
			break;
		case "ListadoAlumnos":
			alumnos.consultar(comandos,sesion);
			response.sendRedirect("protegido/admin/listadoAlumnos.jsp");
			break;
		case "EliminarAlumnos":
			alumnos.eliminar(comandos,request);
			alumnos.consultar(comandos,sesion);
			response.sendRedirect("protegido/admin/listadoAlumnos.jsp");
			break;
		case "ModificarAlumnos":
			alumnos.modificar(sesion,comandos,request);
			response.sendRedirect("protegido/admin/modificarAlumnos.jsp");
			break;
		case "Actualizar Alumnos":
			alumnos.actualizar(request,comandos);
			alumnos.consultar(comandos,sesion);
			response.sendRedirect("protegido/admin/listadoAlumnos.jsp");
			break;
		case "RegistroCursos":
			response.sendRedirect("protegido/admin/registroCursos.html");
			break;
		case "Enviar Registro Cursos":
			if(cursos.registrar(request,comandos)==0){
				cursos.consultar(sesion, comandos,request);
				response.sendRedirect("protegido/admin/listadoCursos.jsp");
			}
			else{
				response.sendRedirect("error.jsp?mensajeError=Este curso ya existe");
			}
			break;
		case "ListadoCursos":
			cursos.consultar(sesion,comandos,request);
			response.sendRedirect("protegido/admin/listadoCursos.jsp");
			break;
		case "EliminarCursos":
			cursos.eliminar(comandos,request);
			cursos.consultar(sesion, comandos,request);
			response.sendRedirect("protegido/admin/listadoCursos.jsp");
			break;
		case "ModificarCursos":
			cursos.modificar(sesion,comandos,request);
			response.sendRedirect("protegido/admin/modificarCursos.jsp");
			break;
		case "Actualizar Cursos":
			cursos.actualizar(request, comandos);
			cursos.consultar(sesion, comandos,request);
			response.sendRedirect("protegido/admin/listadoCursos.jsp");
			break;
		
		case "RegistroProductos":
			response.sendRedirect("protegido/admin/registroProductos.html");
			break;
		case "Enviar Registro Productos":
			if(productos.registrar(comandos,request)==0){
				productos.consultar(sesion, comandos);
				response.sendRedirect("protegido/admin/listadoProductos.jsp");
			}
			else{
				response.sendRedirect("error.jsp?mensajeError=Este producto ya existe");
			}
			break;
		case "ListadoProductos":
			productos.consultar(sesion,comandos);
			response.sendRedirect("protegido/admin/listadoProductos.jsp");
			break;
		case "EliminarProductos":
			productos.eliminar(request,comandos);
			productos.consultar(sesion, comandos);
			response.sendRedirect("protegido/admin/listadoProductos.jsp");
			break;
		case "ModificarProductos":
			productos.modificar(sesion,comandos,request);
			response.sendRedirect("protegido/admin/modificarProductos.jsp");
			break;
		case "Actualizar Productos":
			productos.actualizar(request,comandos);
			productos.consultar(sesion, comandos);
			response.sendRedirect("protegido/admin/listadoProductos.jsp");
			break;
		case "CursosAlumnos":
			cursosAlumnos.consultar(sesion,comandos,request);
			cursos.consultar(sesion, comandos, request);
			response.sendRedirect("protegido/admin/cursosAlumnos.jsp");
			break;
		case "Insertar Alumnos"://insertamos y actualizamos los alumnos de un curso
			cursosAlumnos.insertar(sesion,comandos,request);
			cursosAlumnos.consultar(sesion,comandos,request);
			cursos.consultar(sesion, comandos, request);
			response.sendRedirect("protegido/admin/cursosAlumnos.jsp");
			break;
		case "Modificar Alumnos":
			cursosAlumnos.modificar(sesion,comandos,request);
			cursosAlumnos.consultar(sesion,comandos,request);
			response.sendRedirect("protegido/admin/cursosAlumnos.jsp");
			break;
		case "Eliminar Alumnos":
			cursosAlumnos.eliminar(sesion,comandos,request);
			cursosAlumnos.consultar(sesion, comandos, request);
			cursos.consultar(sesion, comandos, request);
			response.sendRedirect("protegido/admin/cursosAlumnos.jsp");
			break;
		case "VolverCursosAlumnos":
			cursos.consultar(sesion,comandos,request);
			response.sendRedirect("protegido/admin/listadoCursos.jsp");
			break;
		case "VerCursosDeAlumnos":
			cursosAlumnos.consultarAlumnosApuntadosCurso(sesion,comandos,request);
			response.sendRedirect("protegido/admin/alumnosCursos.jsp");
			break;
		case "Salir":
			sesion.invalidate();
			response.sendRedirect("protegido/admin/admin.html");
			break;
		}
	}
}

