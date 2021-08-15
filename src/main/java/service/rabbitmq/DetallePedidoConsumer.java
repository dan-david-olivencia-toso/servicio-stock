package service.rabbitmq;

import domain.DetallePedido;
import domain.MovimientoStock;
import domain.Producto;
import domain.Provision;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;
import service.MovimientoStockService;
import service.ProvisionService;

import java.util.List;

@Component
public class DetallePedidoConsumer {

    public static void main(String[] args) {
        final ApplicationContext rabbitConfig = new AnnotationConfigApplicationContext(RabbitConfiguration.class);
        final ConnectionFactory rabbitConnectionFactory = rabbitConfig.getBean(ConnectionFactory.class);
        final Queue rabbitQueue = rabbitConfig.getBean(Queue.class);
        final MessageConverter messageConverter = new SimpleMessageConverter();


        final SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
        listenerContainer.setConnectionFactory(rabbitConnectionFactory);
        listenerContainer.setQueueNames(rabbitQueue.getName());

        listenerContainer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                final DetallePedido detallePedidoRecibido = (DetallePedido) messageConverter.fromMessage(message);
                // TODO: Procesamiento de DetallePedido -->  Registrar un movimiento de stock del producto y además actualizar el stock actual en la tabla de productos. Si se llegó a un stock debajo del mínimo se crea una nueva orden de provisión. //
                System.out.println("Recibido desde RabbitMQ: " + detallePedidoRecibido);
            }
        });

        listenerContainer.setErrorHandler(new ErrorHandler() {
            public void handleError(Throwable t) {
                t.printStackTrace();
            }
        });

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("Shutting down DetallePedidoConsumer");
                listenerContainer.shutdown();
            }
        });

        listenerContainer.start();
        System.out.println("DetallePedidoConsumer started");
        }

    }
