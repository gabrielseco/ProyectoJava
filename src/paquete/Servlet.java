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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2,	// 2MB 
maxFileSize=1024*1024*10,		// 10MB
maxRequestSize=1024*1024*50)	// 50MB
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
	ProductosAlumnosDAO productosAlumnos;
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
		productosAlumnos=new ProductosAlumnosDAO(miConexion,sentencia);
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
				 response.sendRedirect("protegido/admin/error.jsp?mensajeError=Este alumno ya existe");
			}
			alumnos.consultar(comandos,sesion);
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
			cursos.registrar(request,comandos);
			cursos.consultar(sesion, comandos,request);
			response.sendRedirect("protegido/admin/listadoCursos.jsp");
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
			productos.registrar(comandos,request);
			productos.consultar(sesion, comandos);
			response.sendRedirect("protegido/admin/listadoProductos.jsp");
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
			cursosAlumnos.actualizarInscritos(sesion,comandos,request);
			response.sendRedirect("protegido/admin/cursosAlumnos.jsp");
			break;
		case "Insertar Alumnos"://insertamos y actualizamos los alumnos de un curso
			cursosAlumnos.insertar(sesion,comandos,request);
			cursosAlumnos.consultar(sesion,comandos,request);
			cursosAlumnos.actualizarInscritos(sesion, comandos, request);
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
			cursosAlumnos.actualizarInscritos(sesion, comandos, request);
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
		case "ProductosAlumnos":
			productosAlumnos.consultar(request,comandos,sesion);
			response.sendRedirect("protegido/admin/productosAlumnos.jsp");
			break;
		case "Insertar Alumnos Productos":
			productosAlumnos.insertar(request,comandos,sesion);
			productosAlumnos.consultar(request, comandos, sesion);
			response.sendRedirect("protegido/admin/productosAlumnos.jsp");
			break;
		case "Modificar Productos":
			productosAlumnos.modificar(sesion,comandos,request);
			productosAlumnos.consultar(request, comandos, sesion);
			response.sendRedirect("protegido/admin/productosAlumnos.jsp");
			break;
		case "Eliminar Productos":
			productosAlumnos.eliminar(sesion, comandos, request);
			productosAlumnos.consultar(request, comandos, sesion);
			response.sendRedirect("protegido/admin/productosAlumnos.jsp");
			break;
		case "VerProductosDeAlumnos":
			productosAlumnos.consultarProductosCompradosPorCurso(sesion,comandos,request);
			response.sendRedirect("protegido/admin/alumnosProductos.jsp");
			break;
		case "Salir":
			sesion.invalidate();
			response.sendRedirect("protegido/admin/admin.html");
			break;
		}
	}
}

