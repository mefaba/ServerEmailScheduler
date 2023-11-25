package org.emailreportmanager.services;

import org.emailreportmanager.components.Component;
import org.emailreportmanager.components.TableComponent;
import org.emailreportmanager.components.TemplateHtml;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import java.util.List;

import static org.emailreportmanager.services.ConfigProperties.config;

public class EmailService {
    String result;

    public EmailService(List<Component> componentList, TemplateHtml templateHtml) {
        for (Component c : componentList) {
            if (c instanceof TableComponent) {
                result = templateHtml.getTemplateHtml().replace(c.getComponentId(), ((TableComponent) c).getTableHtml());
                System.out.println("final html: " +result);
            }
        }

    }

    public String getResult() {
        return result;
    }

    public void sendMail() {
        final String username = config.getString("emailservice.username");
        final String password = config.getString("emailservice.password");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(config.getString("email.from")));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(config.getString("email.to"))
            );
            message.setSubject("Testing Gmail TLS");
            message.setContent(result, "text/html");

            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
