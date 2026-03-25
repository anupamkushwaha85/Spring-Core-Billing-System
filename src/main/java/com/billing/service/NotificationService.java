package com.billing.service;

import com.billing.processor.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    // NOT final! This means we can change it later.
    private NotificationSender notificationSender;

    /**
     * Setter Injection.
     * Spring calls this method automatically after the bean is created.
     * We are telling it to default to the Email implementation.
     */
    @Autowired
    public void setNotificationSender(@Qualifier("emailSender") NotificationSender notificationSender) {
        System.out.println("🔧 [CONTAINER] Injecting EmailSender into NotificationService via Setter...");
        this.notificationSender = notificationSender;
    }

    public void notifyClient(String clientName, double amount) {
        // Because it's a setter, it's possible it was never set (e.g., if it wasn't @Autowired).
        // Good engineering practice is to check for null on setter-injected dependencies.
        if (this.notificationSender != null) {
            this.notificationSender.sendReceipt(clientName, amount);
        } else {
            System.out.println("⚠️ [NOTIFICATION] No sender configured. Skipping receipt.");
        }
    }
}