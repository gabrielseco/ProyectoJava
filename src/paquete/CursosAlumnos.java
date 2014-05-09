package paquete;

import java.util.Date;

public class CursosAlumnos {

	String secCurso;
	String secAlumno;
	Date fechaInscripcion;
	double importe;
	String nombre;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSecCurso() {
		return secCurso;
	}
	public void setSecCurso(String secCurso) {
		this.secCurso = secCurso;
	}
	public String getSecAlumno() {
		return secAlumno;
	}
	public void setSecAlumno(String secAlumno) {
		this.secAlumno = secAlumno;
	}
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	@Override
	public String toString() {
		return "CursosAlumnos [secAlumno=" + secAlumno + ", secCurso="
				+ secCurso + ", fechaInscripcion=" + fechaInscripcion
				+ ", importe=" + importe + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((fechaInscripcion == null) ? 0 : fechaInscripcion.hashCode());
		long temp;
		temp = Double.doubleToLongBits(importe);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((secAlumno == null) ? 0 : secAlumno.hashCode());
		result = prime * result
				+ ((secCurso == null) ? 0 : secCurso.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CursosAlumnos other = (CursosAlumnos) obj;
		if (fechaInscripcion == null) {
			if (other.fechaInscripcion != null)
				return false;
		} else if (!fechaInscripcion.equals(other.fechaInscripcion))
			return false;
		if (Double.doubleToLongBits(importe) != Double
				.doubleToLongBits(other.importe))
			return false;
		if (secAlumno == null) {
			if (other.secAlumno != null)
				return false;
		} else if (!secAlumno.equals(other.secAlumno))
			return false;
		if (secCurso == null) {
			if (other.secCurso != null)
				return false;
		} else if (!secCurso.equals(other.secCurso))
			return false;
		return true;
	}
	public CursosAlumnos(String secAlumno, String secCurso,
			Date fechaInscripcion, double importe,String nombre) {
		super();
		this.secAlumno = secAlumno;
		this.secCurso = secCurso;
		this.fechaInscripcion = fechaInscripcion;
		this.importe = importe;
		this.nombre=nombre;
	}
	public CursosAlumnos() {
		// TODO Auto-generated constructor stub
	}
	public CursosAlumnos(String secAlumno) {
		super();
		this.secAlumno=secAlumno;
	}
	
	
}
