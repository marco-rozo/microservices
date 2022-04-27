package br.edu.utfpr;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("${customer.fraud.url}")
public interface FraudClient {

    @PostMapping(value = "fraud/v1/fraud-check/{customerId}")
    FraudCheckResponse checkFraud(@PathVariable("customerId") Integer customerId);
}