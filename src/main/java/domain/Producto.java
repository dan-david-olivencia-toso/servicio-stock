package domain;

public class Producto {

	private Integer id;
	private String descripcion;
	private Double precio;
	private boolean habilitado;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public boolean isHabilitado() { return habilitado; }
	public void setHabilitado(boolean habilitado) { this.habilitado = habilitado; }

	@Override
	public String toString() {
		return "Producto{" +
				"id=" + id +
				", descripcion='" + descripcion + '\'' +
				", precio=" + precio +
				'}';
	}
}
