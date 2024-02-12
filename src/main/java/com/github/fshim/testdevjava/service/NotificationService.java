package com.github.fshim.testdevjava.service;

import com.github.fshim.testdevjava.domain.TransactionNotification;

public interface NotificationService {
    void notify(TransactionNotification event);
}