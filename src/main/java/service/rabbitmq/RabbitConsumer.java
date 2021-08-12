package service.rabbitmq;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import domain.DetallePedido;
import domain.MovimientoStock;
import domain.Producto;
import domain.Provision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.MovimientoStockService;
import service.ProvisionService;

import java.util.List;

@Component
public class RabbitConsumer {

    @Autowired
    ProvisionService provisionServiceImpl;

    @Autowired
    MovimientoStockService movimientoStockServiceImpl;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessage(String message) {
        logger.info("Recibido (String) " + message);
        processMessage(message);
    }

    public void receiveMessage(byte[] message) {
        String strMessage = new String(message);
        logger.info("Recibido (No String) " + strMessage);
        processMessage(strMessage);
    }

    private void processMessage(String message) {
        try {
            List<DetallePedido> detallePedidoRecibido = new Gson().fromJson(message, new TypeToken<List<DetallePedido>>(){}.getType());
            for (DetallePedido detallePedido : detallePedidoRecibido) {
                Producto productoRecibido = detallePedido.getProducto();
                MovimientoStock nuevoMovimientoStock = new MovimientoStock();
                nuevoMovimientoStock.setProducto(productoRecibido);
                nuevoMovimientoStock.setDetallePedido(detallePedido);

                 /* TO DO
                 Setear atributos faltantes en el nuevo movimiento de stock y chequear si hay que crear una nueva orden de provision

                 */
                movimientoStockServiceImpl.crear(nuevoMovimientoStock);
                Provision provision = new Provision();
                provisionServiceImpl.crear(provision);
            }

        } catch (JsonParseException e) {
            logger.warn("Bad JSON in message: " + message);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
