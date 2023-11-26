package org.emailreportmanager.services;

import org.emailreportmanager.entities.MailTemplate;
import org.emailreportmanager.entities.configurations.MailConfig;
import org.emailreportmanager.entities.configurations.SmtpConfig;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

    String result;
    Session session ;

    public EmailService(SmtpConfig smtpConfig) {
        this.session  = smtpConfig.getMailSession();
    }

    public String getResult() {
        return result;
    }

    public void sendMail(MailTemplate mailTemplate) {
        try {
            MailConfig mailConfig = mailTemplate.getMailConfig();
            mailTemplate.render();
            String content = mailTemplate.getRenderedHtml();

            Message message = new MimeMessage(session);
            System.out.println("message count: " +message.getMessageNumber() + " smtp host: " + session.getProperty("mail.smtp.host"));
            message.setFrom(new InternetAddress(mailConfig.getMailFrom()));

            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(mailConfig.getMailTo())
            );
            message.setSubject(mailConfig.getMailSubject());
            message.setContent(content, "text/html");

            System.out.println("message count: " +message.getMessageNumber() + " smtp host: " + session.getProperty("mail.smtp.host"));

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
