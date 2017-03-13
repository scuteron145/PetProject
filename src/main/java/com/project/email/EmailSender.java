package com.project.email;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/*
 EmailSender tlsSender = new EmailSender("chatbyanton@gmail.com", "qwerty4321");
        tlsSender.send("Title!", "Text", "xiaomithebest145@gmail.com");
 */

public class EmailSender {

    private String senderEmail;
    private String password;
    private Properties props;

    public EmailSender(String senderEmail, String password) {
        this.senderEmail = senderEmail;
        this.password = password;
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    }

    public void send(String subject, String text, String toEmail){
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
