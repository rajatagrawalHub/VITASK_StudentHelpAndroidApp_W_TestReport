package com.base.ProjectTestAndroid;


import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private static final String FROM_EMAIL = "pandtechofficial@gmail.com";
    private static final String PASSWORD = "vnxpnkexhwmmugqw";

    private String toEmail;
    private String otp;

    public EmailSender(String toEmail, String otp) {
        this.toEmail = toEmail;
        this.otp = otp;
    }

    public void sendOTPEmail() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Void> future = executor.submit(() -> {
            try {
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                Session session = Session.getInstance(properties, new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
                    }
                });

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(FROM_EMAIL));
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                message.setSubject("[VITASK]: One Time Password");
                message.setText("You have requested for OTP. Your One Time Password for registration Purpose is: " + otp);

                Transport.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return null;
        });

        try {
            // Block until the email is sent or an exception occurs
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Shutdown the executor
            executor.shutdown();
        }
    }
}
