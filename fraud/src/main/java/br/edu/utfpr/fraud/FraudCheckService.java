package br.edu.utfpr.fraud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepository repository;

    // uma classe helper para acessar e enviar mensagens para RabbitMQ de forma síncrona;
    private final RabbitTemplate rabbitTemplate;
    public boolean isFraudCustomer(Integer customerId) {
        log.info("Check if {} isFraudster", customerId);
        repository.save(
                FraudCheckHistory.builder().
                        customerId(customerId).
                        isFraudster(false).
                        createdAt(LocalDateTime.now()).
                        build()
        );

        log.info("Sending message to Rabbit...");
        // convertAndSend é um método que recebe os parâmetro:
        // exchange, routingKey e message, onde são o interceptador da mensagem (direcionando para uma fila),
        // nome da fila e a mensagem a ser enviada.
        // A cada chamada do método send, será enviado uma mensagem para fila.
        rabbitTemplate.convertAndSend(ConstantsRabbitMQ.EXCHANGE, ConstantsRabbitMQ.ROUTING_KEY, "Cliente fraudulento recebido, identificador: " + customerId);
        return false;
    }

}
