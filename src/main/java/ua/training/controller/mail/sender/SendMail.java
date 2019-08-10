package ua.training.controller.mail.sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.constants.Messages;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;


public class SendMail {

    private final static Logger logger = LogManager.getLogger(SendMail.class);

    private String username;
    private String password;

    /**
     * Construct new SendMail with email and password
     * @param email sender's (project's) email
     * @param password password to access email
     */
    public SendMail(String email, String password) {
        this.username = email;
        this.password = password;
    }

    /**
     * Returns property for sending emails
     * @return properties to send emails
     */
    private Properties getProperties() {
        Properties props = new Properties();
        try {
            props.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));
        } catch (IOException e) {
            logger.error(Messages.SEND_MAIL_PROPERTIES_LOADING_FAIL);
            e.printStackTrace();
        }
        return props;
    }

    /**
     * Sends mail to group of users
     * @param subject subject of th mail
     * @param text body of the mail
     * @param fromEmail email address of the sender
     * @param toEmails array of the user's emails to send letters
     */
    public void send(String subject, String text, String fromEmail, String[] toEmails) {
        Properties props = getProperties();
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        session.setDebug(true);

        try {
            InternetAddress[] mailAddressTo = new InternetAddress[toEmails.length];
            for (int i = 0; i < toEmails.length; i++) {
                mailAddressTo[i] = new InternetAddress(toEmails[i]);
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, mailAddressTo);
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

        } catch (MessagingException e) {
            logger.error(Messages.SEND_MAIL_FAIL);
            throw new RuntimeException(e);
        }
    }
}
