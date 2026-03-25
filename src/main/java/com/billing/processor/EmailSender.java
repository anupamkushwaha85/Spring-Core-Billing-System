package com.billing.processor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("emailSender")
public class EmailSender implements NotificationSender {
    @Override
    public void sendReceipt(String clientName, double amount) {
        System.out.println("📧 [EMAIL] Sending PDF receipt to " + clientName + " for $" + amount);
    }
}