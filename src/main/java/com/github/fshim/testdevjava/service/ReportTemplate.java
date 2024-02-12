package com.github.fshim.testdevjava.service;

import com.github.fshim.testdevjava.domain.TransactionNotification;

public interface ReportTemplate<T> {
    T generateMessage(TransactionNotification notificationEvent);
}