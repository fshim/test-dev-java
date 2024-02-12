package com.github.fshim.testdevjava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TransactionData {
    private Long id;
    private BigDecimal amount;
}
