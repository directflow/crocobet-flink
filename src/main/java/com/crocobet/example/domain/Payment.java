package com.crocobet.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private Long id;

    private Long transactionId;

    private Long createdAt;

    private String flinkStream;

    private Double amount;

}
