package org.emailreportmanager.controllers;

import io.javalin.http.Context;
import org.emailreportmanager.entities.MailTemplate;
import org.emailreportmanager.entities.configurations.MailConfig;
import org.emailreportmanager.entities.configurations.SmtpConfig;
import org.emailreportmanager.entities.elements.Element;
import org.emailreportmanager.services.EmailService;

import java.util.List;
import java.util.Properties;

public class EmailController {
    /*
     * POST - /test-email
     */
    public void handleEmailSubmit(Context ctx) {
        // templatehtml
        String rawEmailData = ctx.formParam("data_email");

        Properties properties = new Properties();
        // properties.put("cc", ""); properties.put("priority", "");
        String from = ctx.formParam("emailFrom");
        String to = ctx.formParam("emailTo");
        String subject = ctx.formParam("emailSubject");
        MailConfig mailConfig = new MailConfig(from, to, subject, properties);

        MailTemplate mailTemplate = new MailTemplate("FM-dev-testing", rawEmailData, mailConfig);
        List<Element> componentList = TableController.componentList;
        for (Element e : componentList) {
            mailTemplate.addElement(e);
        } // new version of adding elements

        EmailService emailService = new EmailService(new SmtpConfig());
        emailService.sendMail(mailTemplate);
        emailService.getResult();
        /* END Email */
        ctx.result("Noluyo");
    }
}
