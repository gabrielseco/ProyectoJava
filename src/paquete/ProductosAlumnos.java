package paquete;

import java.util.Date;

public class ProductosAlumnos {
	String secProducto;
	String secAlumno;
	Date fechaInscripcion;
	double importe;
	//String nombre para poner el nombre en el select
	public String getSecProducto() {
		return secProducto;
	}
	public void setSecProducto(String secProducto) {
		this.secProducto = secProducto;
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
		return "ProductosAlumnos [secProducto=" + secProducto + ", secAlumno="
				+ secAlumno + ", fechaInscripcion=" + fechaInscripcion
				+ ", importe=" + importe + "]";
	}
	public ProductosAlumnos(String secProducto, String secAlumno,
			Date fechaInscripcion, double importe) {
		super();
		this.secProducto = secProducto;
		this.secAlumno = secAlumno;
		this.fechaInscripcion = fechaInscripcion;
		this.importe = importe;
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
				+ ((secProducto == null) ? 0 : secProducto.hashCode());
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
		ProductosAlumnos other = (ProductosAlumnos) obj;
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
		if (secProducto == null) {
			if (other.secProducto != null)
				return false;
		} else if (!secProducto.equals(other.secProducto))
			return false;
		return true;
	}
}
