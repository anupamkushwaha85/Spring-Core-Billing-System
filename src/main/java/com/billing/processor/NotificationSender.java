package com.billing.processor;

public interface NotificationSender {
    void sendReceipt(String clientName, double amount);
}