package com.github.fshim.testdevjava.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionEvent {

    @JsonProperty("PID")
    private Long id;

    @JsonProperty("PAMOUNT")
    private BigDecimal amount;

    @JsonProperty("PDATA")
    private Object data;

}
