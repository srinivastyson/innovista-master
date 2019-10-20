package org.verizon.sharingservices.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.verizon.sharingservices.enums.EmailSendState;
import org.verizon.sharingservices.model.Email;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * This is utility class which is basically responsible for sending the actual email using gmail server
 * 
 * @author Bhuvitha
 * @since 1.0
 */
public class GmailSMTPEmailSender extends HystrixCommand<EmailSendState> {
    private Logger logger = LoggerFactory.getLogger(GmailSMTPEmailSender.class);
    
    /**  Email Object to be sent out to users */
    private Email email;
    /**  user name to login to email server */
    private String userName;
    /**  password to login to email server */
    private String password;
   
    /**
     * Constructor of this class
     * @param email
     */
    public GmailSMTPEmailSender(Email email) {
        super(HystrixCommandGroupKey.Factory.asKey("GmailSMTPEmailSender"));
        this.email = email;
        this.userName = "UserName";
        this.password = "Password";
    }
    
    /**
     * @author Bhuvitha
     * @throws Exception
     * @see com.netflix.hystrix.HystrixCommand#run()
     */
    @Override
    protected EmailSendState run() throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        try {
            this.sendEmail(this.createMailSession(props));
            logger.info("Mail sent successfully : {}", this.email.getToAddress());
            return EmailSendState.SUCCESS;
        } catch (Exception e) {
            logger.error("Mail could not be sent to " + this.email.getToAddress() + " : ", e);
            throw e;
        }
    }
    
    /** It is fallback methods which gets invoked after sending the email if anything goes wrong   */
    @Override
    protected EmailSendState getFallback() {
        /**
         * Return stubbed fallback with some static defaults, placeholders,
         * and an injected value 'countryCodeFromGeoLookup' that we'll use
         * instead of what we would have retrieved from the remote service.
         */
        return EmailSendState.QUEUED;
    }
    
    /** Actually sends the email by setting up the required fields 
     * @param session
     * @throws MessagingException
     * */
    private void sendEmail(Session session) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.email.getFromAddress()));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.email.getToAddress()));
        message.setSubject(this.email.getEmailSubject());
        message.setText(this.email.getEmailBody());

        Transport.send(message);
    }
    
    /** creates session for sending emails 
     * @param props
     * @return Session
     * */
    private Session createMailSession(Properties props) {
        final String userName = this.userName;
        final String password = this.password;
        return Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        });
    }
    
    /**  Unit Test class */
    public static class UnitTest {
        @Test
        public void test() {
            GmailSMTPEmailSender emailSender = new GmailSMTPEmailSender(new Email());//set the properties to unit test
            System.out.println(emailSender.execute().getValue());
            System.out.println(emailSender.isFailedExecution());
            System.out.println(emailSender.isResponseFromFallback());
        }
    }
}

