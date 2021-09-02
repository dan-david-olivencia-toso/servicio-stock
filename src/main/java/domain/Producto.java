package domain;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

	//region Getters & Setters
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getDescripcion() { return descripcion; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public Double getPrecio() { return precio; }
	public void setPrecio(Double precio) { this.precio = precio; }
	public boolean isHabilitado() { return habilitado; }
	public void setHabilitado(boolean habilitado) { this.habilitado = habilitado; }
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getDescuentoPorCantidad() {
		return descuentoPorCantidad;
	}

	public void setDescuentoPorCantidad(Double descuentoPorCantidad) {
		this.descuentoPorCantidad = descuentoPorCantidad;
	}

	public Integer getCantidadMinimaDescuento() {
		return cantidadMinimaDescuento;
	}

	public void setCantidadMinimaDescuento(Integer cantidadMinimaDescuento) {
		this.cantidadMinimaDescuento = cantidadMinimaDescuento;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	//endregion

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;
	private String descripcion;
	private Double precio;
	private boolean habilitado;

	@Column(name = "descuento_por_cantidad")
	private Double descuentoPorCantidad;
	@Column(name = "cantidad_minima_descuento")
	private Integer cantidadMinimaDescuento;

	@ManyToOne(optional = false)
	@JoinColumn(name = "id_unidad", referencedColumnName = "id")
	private Unidad unidad;

	@Override
	public String toString() {
		return "Producto{" +
				"id=" + id +
				", descripcion='" + descripcion + '\'' +
				", precio=" + precio +
				'}';
	}
}
