package com.billing.processor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("smsSender")
public class SmsSender implements NotificationSender {
    @Override
    public void sendReceipt(String clientName, double amount) {
        System.out.println("📱 [SMS] Texting brief receipt to " + clientName + ": PAID $" + amount);
    }
}