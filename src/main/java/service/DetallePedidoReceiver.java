package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.DetallePedido;
import domain.MovimientoStock;
import domain.Producto;
import domain.Provision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DetallePedidoReceiver {

    @Autowired
    MovimientoStockService movimientoStockService;

    @Autowired
    ProvisionService provisionService;

    Logger log = LoggerFactory.getLogger(DetallePedidoReceiver.class);

    @JmsListener(destination = "COLA_PEDIDOS", id = "default")
    public void recibirDetallePedido(String jsonDetallePedido) {
        log.info("Recibido <" + jsonDetallePedido + ">");

        //Deserializa el detalle pedido recibido
        List<DetallePedido> detallePedidoRecibido = new Gson().fromJson(jsonDetallePedido, new TypeToken<List<DetallePedido>>(){}.getType());

        for(int i=0; i<detallePedidoRecibido.size(); i++){
            Producto productoRecibido = detallePedidoRecibido.get(i).getProducto();
            MovimientoStock nuevoMovimientoStock = new MovimientoStock();
            nuevoMovimientoStock.setProducto(productoRecibido);
            nuevoMovimientoStock.setDetallePedido(detallePedidoRecibido.get(i));

            /* TO DO
            Setear atributos faltantes en el nuevo movimiento de stock y chequear si hay que crear una nueva orden de provision
             */

            movimientoStockService.crear(nuevoMovimientoStock);

            /* if faltante */
            Provision provision = new Provision();
            /* setters */
            provisionService.crear(provision);
        }


    }

}
