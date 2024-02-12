package com.github.fshim.testdevjava.domain;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionNotification {
    private NotificationType notificationType;
    private Long transactionId;
    private BigDecimal expectedAmount;
    private BigDecimal actualAmount;
}
