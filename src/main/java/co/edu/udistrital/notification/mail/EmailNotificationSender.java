package co.edu.udistrital.notification.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spinn3r.log5j.Logger;

import co.edu.udistrital.notification.NotificationSender;
import co.edu.udistrital.service.ConfigurationService;

@Service("emailNotificationSender")
public class EmailNotificationSender implements NotificationSender {

	protected static final String EMAIL_SEPARATOR = ",";
	
	protected static final String MAIL_SMTP_HOST = "mail.smtp.host";
	protected static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
	protected static final String MAIL_SMTP_PORT = "mail.smtp.port";
	protected static final String MAIL_SMTP_USER = "mail.smtp.user";
	protected static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

	protected static final Logger logger = Logger.getLogger();
	
	@Autowired
	protected ConfigurationService configurationService;
	
	@Override
    public void sendNotification(String subject, String message, String emailDestination) {

        Properties emailProperties = new Properties();
        emailProperties.setProperty(MAIL_SMTP_HOST, configurationService.getEmailSenderHost());
        emailProperties.setProperty(MAIL_SMTP_STARTTLS_ENABLE, configurationService.getEmailSenderStartTLS());
        emailProperties.setProperty(MAIL_SMTP_PORT, configurationService.getEmailSenderPort());
        emailProperties.setProperty(MAIL_SMTP_USER, configurationService.getEmailSenderUsername());
        emailProperties.setProperty(MAIL_SMTP_AUTH, configurationService.getEmailSenderAuth());

        Session session = Session.getDefaultInstance(emailProperties);
        Transport t = null;
        try {
	        MimeMessage mimeMessage = new MimeMessage(session);
	        mimeMessage.setFrom(new InternetAddress(configurationService.getEmailSenderFrom()));
	        for(String email : emailDestination.split(EMAIL_SEPARATOR)) {
	        	mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
	        }
	        mimeMessage.setSubject(subject);
	        mimeMessage.setText(message);
	
	        t = session.getTransport("smtp");
	        t.connect(configurationService.getEmailSenderUsername(), configurationService.getEmailSenderPassword());
	        t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
	        
        } catch (MessagingException ex) {
        	logger.error("Message not sent", ex);
        } finally {
        	try {
        		t.close();
        	} catch (MessagingException ignore) {
        	}
        }
    }
}
