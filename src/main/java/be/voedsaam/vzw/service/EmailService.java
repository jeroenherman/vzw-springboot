package be.voedsaam.vzw.service;

import be.voedsaam.vzw.business.Drive;

import javax.mail.MessagingException;
import java.util.Locale;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

    void sendTextMail(String recipientName, String recipientEmail, Locale locale) throws MessagingException;

    void sendSimpleMail(String recipientName, String recipientEmail, Locale locale) throws MessagingException;

    void sendMailWithAttachment(String recipientName, String recipientEmail, String originalFilename, byte[] bytes, String contentType, Locale locale) throws MessagingException;

    void sendMailWithInline(String recipientName, String recipientEmail, String name, byte[] bytes, String contentType, Locale locale) throws MessagingException;

    void sendEditableMail(String recipientName, String recipientEmail, String body, Locale locale) throws MessagingException;

    void sendDriveMail(String recipientName, String recipientEmail, Locale locale, Drive drive) throws MessagingException;
}
