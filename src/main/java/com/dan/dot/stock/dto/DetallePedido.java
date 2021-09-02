package com.dan.dot.stock.dto;

import  com.dan.dot.stock.domain.Producto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

	//region Description
	public Integer getId() { return id; }
	public void setId(Integer id) {
		this.id = id;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	//endregion

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer cantidad;
	private Double precio;

	@ManyToOne
	@JoinColumn(name = "id_producto", referencedColumnName = "id")
	@JsonBackReference
	private Producto producto;
}
