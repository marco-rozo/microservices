package br.edu.utfpr;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              FraudClient fraudClient) {
    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        // TODO: check if email valid
        // TODO: check if email not taken
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse checkFraudResult
                = fraudClient.checkFraud(customer.getId());
        assert checkFraudResult != null;
        if (checkFraudResult.isFraudster()) {
            throw new IllegalStateException("Fraudster");
        }
        // TODO: send notification
    }

}
