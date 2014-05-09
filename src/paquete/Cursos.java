package paquete;

import java.util.Date;

public class Cursos {

	String secCurso;
	String nombreCurso;
	Date fechaInicio;
	Date fechaFinal;
	String horario;
	double duracion;
	double precio;
	String plazas;
	String inscritos;
	public String getSecCurso() {
		return secCurso;
	}
	public void setSecCurso(String secCurso) {
		this.secCurso = secCurso;
	}
	public String getNombreCurso() {
		return nombreCurso;
	}
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFinal() {
		return fechaFinal;
	}
	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public double getDuracion() {
		return duracion;
	}
	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getPlazas() {
		return plazas;
	}
	public void setPlazas(String plazas) {
		this.plazas = plazas;
	}
	public String getInscritos() {
		return inscritos;
	}
	public void setInscritos(String inscritos) {
		this.inscritos = inscritos;
	}
	@Override
	public String toString() {
		return "Cursos [secCurso=" + secCurso + ", nombreCurso=" + nombreCurso
				+ ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal
				+ ", horario=" + horario + ", duracion=" + duracion
				+ ", precio=" + precio + ", plazas=" + plazas + ", inscritos="
				+ inscritos + "]";
	}
	public Cursos(String secCurso, String nombreCurso, Date fechaInicio,
			Date fechaFinal, String horario, double duracion, double precio,
			String plazas, String inscritos) {
		super();
		this.secCurso = secCurso;
		this.nombreCurso = nombreCurso;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.horario = horario;
		this.duracion = duracion;
		this.precio = precio;
		this.plazas = plazas;
		this.inscritos = inscritos;
	}
	
	public Cursos(String nombreCurso,double precio, String plazas, String inscritos) {
		super();
		this.nombreCurso = nombreCurso;
		this.precio = precio;
		this.plazas=plazas;
		this.inscritos=inscritos;
	}
	public Cursos(){
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(duracion);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((fechaFinal == null) ? 0 : fechaFinal.hashCode());
		result = prime * result
				+ ((fechaInicio == null) ? 0 : fechaInicio.hashCode());
		result = prime * result + ((horario == null) ? 0 : horario.hashCode());
		result = prime * result
				+ ((inscritos == null) ? 0 : inscritos.hashCode());
		result = prime * result
				+ ((nombreCurso == null) ? 0 : nombreCurso.hashCode());
		result = prime * result + ((plazas == null) ? 0 : plazas.hashCode());
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Cursos other = (Cursos) obj;
		if (Double.doubleToLongBits(duracion) != Double
				.doubleToLongBits(other.duracion))
			return false;
		if (fechaFinal == null) {
			if (other.fechaFinal != null)
				return false;
		} else if (!fechaFinal.equals(other.fechaFinal))
			return false;
		if (fechaInicio == null) {
			if (other.fechaInicio != null)
				return false;
		} else if (!fechaInicio.equals(other.fechaInicio))
			return false;
		if (horario == null) {
			if (other.horario != null)
				return false;
		} else if (!horario.equals(other.horario))
			return false;
		if (inscritos == null) {
			if (other.inscritos != null)
				return false;
		} else if (!inscritos.equals(other.inscritos))
			return false;
		if (nombreCurso == null) {
			if (other.nombreCurso != null)
				return false;
		} else if (!nombreCurso.equals(other.nombreCurso))
			return false;
		if (plazas == null) {
			if (other.plazas != null)
				return false;
		} else if (!plazas.equals(other.plazas))
			return false;
		if (Double.doubleToLongBits(precio) != Double
				.doubleToLongBits(other.precio))
			return false;
		if (secCurso == null) {
			if (other.secCurso != null)
				return false;
		} else if (!secCurso.equals(other.secCurso))
			return false;
		return true;
	}
}
