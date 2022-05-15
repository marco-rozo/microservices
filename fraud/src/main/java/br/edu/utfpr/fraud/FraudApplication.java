package br.edu.utfpr.fraud;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class FraudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FraudApplication.class, args);
    }

    // Queue pode ser inicializada através de um
    // @Bean na classe de configuração do Spring Boot,
    // assim centralizando e possibilitando injeção do objeto que representa a fila.
    @Bean
    public Queue queue() {
        return new Queue(ConstantsRabbitMQ.QUEUE, true);
    }

}
