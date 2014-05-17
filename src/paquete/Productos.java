package paquete;

import com.mysql.jdbc.Blob;


public class Productos {

	String secProducto;
	String codigo;
	int numUnidades;
	double precio;
	String descripcion;
	Blob imagen;
	
	public Productos() {
		// TODO Auto-generated constructor stub
	}

	public String getSecProducto() {
		return secProducto;
	}

	public void setSecProducto(String secProducto) {
		this.secProducto = secProducto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getNumUnidades() {
		return numUnidades;
	}

	public void setNumUnidades(int numUnidades) {
		this.numUnidades = numUnidades;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Blob getImagen() {
		return imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}

	@Override
	public String toString() {
		return "Productos [secProducto=" + secProducto + ", codigo=" + codigo
				+ ", numUnidades=" + numUnidades + ", precio=" + precio
				+ ", descripcion=" + descripcion + ", imagen=" + imagen + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((imagen == null) ? 0 : imagen.hashCode());
		result = prime * result + numUnidades;
		long temp;
		temp = Double.doubleToLongBits(precio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Productos other = (Productos) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (imagen == null) {
			if (other.imagen != null)
				return false;
		} else if (!imagen.equals(other.imagen))
			return false;
		if (numUnidades != other.numUnidades)
			return false;
		if (Double.doubleToLongBits(precio) != Double
				.doubleToLongBits(other.precio))
			return false;
		if (secProducto == null) {
			if (other.secProducto != null)
				return false;
		} else if (!secProducto.equals(other.secProducto))
			return false;
		return true;
	}

	public Productos(String secProducto, String codigo, int numUnidades,
			double precio, String descripcion, Blob imagen) {
		super();
		this.secProducto = secProducto;
		this.codigo = codigo;
		this.numUnidades = numUnidades;
		this.precio = precio;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}

	public Productos(String secProducto, String codigo, int numUnidades,
			double precio, String descripcion) {
		// TODO Auto-generated constructor stub
		super();
		this.secProducto = secProducto;
		this.codigo = codigo;
		this.numUnidades = numUnidades;
		this.precio = precio;
		this.descripcion = descripcion;
	}
	
	
}
