package com.dan.dot.stock.domain;

import com.dan.dot.stock.dto.Pedido;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movimiento_stock")
public class MovimientoStock {

    //region Getters & Setters
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


    public Producto getProducto() {
        return producto;
    }
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Integer getCantidadEntrada() {
        return cantidadEntrada;
    }
    public void setCantidadEntrada(Integer cantidadEntrada) {
        this.cantidadEntrada = cantidadEntrada;
    }
    public Integer getCantidadSalida() {
        return cantidadSalida;
    }
    public void setCantidadSalida(Integer cantidadSalida) {
        this.cantidadSalida = cantidadSalida;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public boolean isHabilitado() { return habilitado; }
    public void setHabilitado(boolean anulado) { this.habilitado = anulado; }
    public Pedido getPedido() {
        return pedido;
    }
    public Provision getProvision() {
        return provision;
    }

    public void setProvision(Provision provision) {
        this.provision = provision;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    //endregion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Relación con pedido registra un movimiento que decrementa el stock
    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    private Pedido pedido;

    // Relación con provisión registra un movimiento que incrementa el stock

    @OneToOne(optional = true, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_provision", referencedColumnName = "id")
    private Provision provision;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Producto producto;

    // Cero si el movimiento es por un pedido. Mayor a cero si es por una provisión.
    @Column(name = "cantidad_entrada")
    private Integer cantidadEntrada;

    // Cero si el movimiento es por una provisión. Mayor a cero si es por un pedido.
    @Column(name = "cantidad_salida")
    private Integer cantidadSalida;

    private Date fecha;
    private boolean habilitado;
}
