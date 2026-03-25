package com.billing;

import com.billing.config.AppConfig;
import com.billing.processor.NotificationSender;
import com.billing.service.BillingService;
import com.billing.service.NotificationService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The manual bootstrap entry point for our Pure Spring Core application.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Booting up Enterprise Spring IoC Container...\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // Initialize the Container using our AppConfig blueprint
        // This triggers component scanning, property loading, and bean creation.
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("\n✅ Container is Ready! Executing Business Logic...\n");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

        //  Fetch the fully wired BillingService from the container
        BillingService billingService = context.getBean(BillingService.class);
        NotificationService notificationService = context.getBean(NotificationService.class);

        //  Run the application logic
        System.out.println("\n--- 🏢 CLIENT 1: Wayne Enterprises (Default Behavior) ---");
        // This will use the default Email sender we wired in the Setter
        billingService.billClient("Wayne Enterprises", 25000.50);


        System.out.println("\n--- 🔄 DYNAMIC SYSTEM OVERRIDE ---");
        System.out.println("Admin changes system settings to SMS temporarily without restarting server...");

        // We fetch the SMS bean from the container...
        NotificationSender smsSender = (NotificationSender) context.getBean("smsSender");

        // ...and we inject it into our Singleton service while the app is still running!
        notificationService.setNotificationSender(smsSender);


        System.out.println("\n--- 🏢 CLIENT 2: Stark Industries (Modified Behavior) ---");
        // This will now magically use SMS instead of Email!
        billingService.billClient("Stark Industries", 999.99);


        System.out.println("\n--- 📊 SYSTEM AUDIT: FINAL DATABASE STATE ---");
        billingService.printAllInvoices();


        //  Gracefully shut down the container
        // This ensures things like our HikariCP database pool close their connections cleanly.
        System.out.println("\n🛑 Shutting down container...");
        context.close();
    }
}