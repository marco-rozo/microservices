package br.edu.utfpr;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

//@Component: O consumer deve ser um bean mapeado no Spring, por isso anotado como componente;
@Component
@Slf4j
public class MessagingConsumer {

    // Para consumir a fila, a dependência do spring-boot-starter-amqp,
    // disponibiliza a anotação @RabbitListener que recebe como parâmetro
    // um array de String, que são os nomes da filas que serão consumidas,
    // dessa forma, quando inicializado a aplicação o método anotado começará a consumir a fila.

    //@RabbitListener: É a anotação que marca o método como um listener;
    @RabbitListener(queues = ConstantsRabbitMQ.QUEUE)
    //@Payload: É a anotação que informa que o parâmetro vai receber o corpo da mensagem.
    //Observação: não é obrigatório quando tem apenas um parâmetro.
    public void receive(@Payload String order) {
        log.info("Order: " + order);
    }
}