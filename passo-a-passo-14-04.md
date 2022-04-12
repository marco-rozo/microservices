# Comunicação REST entre microserviços

- Ideia será fazer uma chamada do microserviço customer para o microserviço fraud
- Notem que criei alguns #TODO's na classe CustomerService
- Dentre eles, o "check fraudster" já pode ser resolvido

## Passos
- criar uma classe chamada CustomerConfig para configurarmos o template rest

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

- No CustomerService vamos chamar esse Bean que criamos na classe Customer config
- Como estamos usando record, apenas devemos passar como parâmetro, ficando:
```java
public record CustomerService(CustomerRepository customerRepository, 
                              RestTemlate restTemplate){
}
```
- Para saber mais sobre Spring RestTemplate aqui tem um bom artigo: https://reflectoring.io/spring-resttemplate/
mas por enquanto vamos usar apenas o metodo getForEntity;
- Então vamos fazer algumas alterações no método, ficando assim:
```java
public record CustomerService(CustomerRepository customerRepository,
                              RestTemlate restTemplate){
    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse fraudCheckResponse =
                restTemplate.getForObject(
                        "http://localhost:8092/fraud/v1/fraud-check/{customerId}",
                        FraudCheckResponse.class,
                        customer.getId()
                );
        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }
    }
}
 ```
- Agora estamos usando saveAndFlush para o customer, assim vamos receber o id no momento do save;
- Copiei e colei a classe FraudCheckResponse do module Fraud
- Assim no metodo getForObject, eu passo a URL que será feita a chamada GET, o objeto que sera à resposta e por fim o customerId que será o pathVariable
- E caso seja Fraudster no momento apenas dispararemos uma Exception.

### obervações

Comentaremos mais sobre essa implementação na próxima aula, falaremos sobre a classe RestTemplate e entenderemos o que é uma chamada REST

