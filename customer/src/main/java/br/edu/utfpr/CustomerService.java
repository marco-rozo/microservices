package br.edu.utfpr;

import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository) {
    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        Customer customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        // TODO: check if email valid
        // TODO: check if email not taken
        // TODO: check if fraudster
        customerRepository.save(customer);
        // TODO: send notification
    }

}
