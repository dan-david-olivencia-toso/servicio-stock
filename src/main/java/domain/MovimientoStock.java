package domain;

import java.time.Instant;

public class MovimientoStock {
    private Integer id;
    private DetallePedido detallePedido;
    private DetalleProvision detalleProvision;
    private Producto producto;
    private Integer cantidadEntrada;
    private Integer cantidadSalida;
    private Instant fecha;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public DetallePedido getDetallePedido() {
        return detallePedido;
    }
    public void setDetallePedido(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
    }
    public DetalleProvision getDetalleProvision() {
        return detalleProvision;
    }
    public void setDetalleProvision(DetalleProvision detalleProvision) {
        this.detalleProvision = detalleProvision;
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
    public Instant getFecha() {
        return fecha;
    }
    public void setFecha(Instant fecha) {
        this.fecha = fecha;
    }
}
