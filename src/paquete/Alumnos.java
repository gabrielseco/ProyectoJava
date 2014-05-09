package paquete;

public class Alumnos {
	String secAlumno;
	String nombre;
	String apellidos;
	String telefono;
	String email;
	String direccion;
	String calle;
	String numero;
	String piso;
	String localidad;
	String codigoPostal;
	String provincia;
	String usuario;
	String contrasenya;
	public String getSecAlumno() {
		return secAlumno;
	}
	public void setSecAlumno(String secAlumno) {
		this.secAlumno = secAlumno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPiso() {
		return piso;
	}
	public void setPiso(String piso) {
		this.piso = piso;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	@Override
	public String toString() {
		return "Alumnos [secAlumno=" + secAlumno + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", telefono=" + telefono
				+ ", email=" + email + ", direccion=" + direccion + ", calle="
				+ calle + ", numero=" + numero + ", piso=" + piso
				+ ", localidad=" + localidad + ", codigoPostal=" + codigoPostal
				+ ", provincia=" + provincia + ", usuario=" + usuario
				+ ", contrasenya=" + contrasenya + "]";
	}
	public Alumnos(String nombre) {
		super();
		this.nombre=nombre;
	}
	public Alumnos(String secAlumno, String nombre, String apellidos,
			String telefono, String email, String direccion, String calle,
			String numero, String piso, String localidad, String codigoPostal,
			String provincia, String usuario, String contrasenya) {
		super();
		this.secAlumno = secAlumno;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.calle = calle;
		this.numero = numero;
		this.piso = piso;
		this.localidad = localidad;
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.usuario = usuario;
		this.contrasenya = contrasenya;
	}
	public Alumnos(String secAlumno, String nombre, String apellidos,
			String telefono, String email, String direccion, String usuario,
			String contrasenya) {
		super();
		this.secAlumno = secAlumno;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.email = email;
		this.direccion = direccion;
		this.usuario = usuario;
		this.contrasenya = contrasenya;
	}

	public Alumnos(String secAlumno,String nombre){
		super();
		this.secAlumno=secAlumno;
		this.nombre=nombre;
	}
	public Alumnos() {
		// TODO Auto-generated constructor stub
	}
}
