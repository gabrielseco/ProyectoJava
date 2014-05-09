package paquete;

public class Productos {

	String secProducto;
	String codigo;
	int numUnidades;
	double precio;
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
	@Override
	public String toString() {
		return "Productos [secProducto=" + secProducto + ", codigo="
				+ codigo + ", numUnidades=" + numUnidades + ", precio="
				+ precio + "]";
	}
	public Productos(String secProducto, String codigo,
			int numUnidades, double precio) {
		super();
		this.secProducto = secProducto;
		this.codigo = codigo;
		this.numUnidades = numUnidades;
		this.precio = precio;
	}
	public Productos() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codigo == null) ? 0 : codigo.hashCode());
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
}
