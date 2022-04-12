package br.edu.utfpr.fraud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudCheckHistory {

    @MongoId
    private String id;
    private Integer customerId;
    private Boolean isFraudster;
    private LocalDateTime createdAt;

}
