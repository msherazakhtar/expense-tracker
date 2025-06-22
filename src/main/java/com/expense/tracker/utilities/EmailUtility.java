package com.expense.tracker.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.expense.tracker.dtos.GmailDTO;
import com.expense.tracker.models.MailConfigurationORM;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class EmailUtility {
    public static Map<String, Object> sendUserVerificationEmail(MailConfigurationORM mailConfiguration,
            String reciepent, String code) {
        mailConfiguration.setReciever(reciepent);
        mailConfiguration.setMailTemplate(
                mailConfiguration.getMailTemplate().replace("{verificationCode}",
                        code));
        String mailSubject = "Verification Code for Expense Tracker";
        GmailDTO gmailDTO = EmailUtility.mapToGmailDTO(mailConfiguration, mailSubject);
        return sendMailUsingGmail(gmailDTO);
    }

    public static Map<String, Object> sendMailUsingGmail(GmailDTO gmailDTO) {
        System.out.println("Sending Verification Email......");
        Map<String, Object> emailResponseMap = new HashMap<>();
        final String username = gmailDTO.username();
        final String password = gmailDTO.password();
        Session session = intiateMailSession(username, password);
        try {
            Transport.send(prepareMimeMessage(gmailDTO, session));
            System.out.println("Done");
            emailResponseMap.put("success", "Email sent successfully");
            return emailResponseMap;
        } catch (MessagingException e) {
            emailResponseMap.put("error", "Email can't be sent!!!");
            return emailResponseMap;
        }
    }

    private static Session intiateMailSession(String userName, String password) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
        return session;
    }

    private static MimeMessage prepareMimeMessage(GmailDTO gmailDTO, Session session) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setContent(gmailDTO.body(), "text/html");
            message.setFrom(new InternetAddress(gmailDTO.sender()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(gmailDTO.reciever()));
            message.setSubject(gmailDTO.subject());
            return message;
        } catch (MessagingException e) {
            return null;
        }

    }

    public static GmailDTO mapToGmailDTO(MailConfigurationORM config, String subject) {
        return new GmailDTO(
                config.getSender(),
                config.getReciever(),
                config.getMailTemplate(),
                subject,
                config.getUsername(),
                config.getPassword());
    }

}
