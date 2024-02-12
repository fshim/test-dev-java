package com.github.fshim.testdevjava.event;

import com.github.fshim.testdevjava.domain.TransactionNotification;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class NotificationEvent {
    private String message;
    private TransactionNotification data;
}
