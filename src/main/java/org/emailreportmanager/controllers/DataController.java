package org.emailreportmanager.controllers;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.emailreportmanager.entities.MailTemplate;
import org.emailreportmanager.entities.configurations.MailConfig;
import org.emailreportmanager.entities.configurations.SmtpConfig;
import org.emailreportmanager.entities.elements.Element;
import org.emailreportmanager.entities.configurations.CsvConfiguration;
import org.emailreportmanager.entities.configurations.TableElementConfiguration;
import org.emailreportmanager.services.EmailService;
import org.emailreportmanager.services.ElementFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.mail.internet.InternetAddress;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.emailreportmanager.services.ConfigProperties.config;

public class DataController {
    List<Element> componentList = new ArrayList<>();

    /*
     * POST - /data
     */
    public void handleChartData(Context ctx) throws IOException {
        // Recieve file from request body, Access request body
        UploadedFile uploaded_file = ctx.uploadedFiles().get(0);
        InputStream inputStream = uploaded_file.getContent();

        // Write to file
        OutputStream outputStream = new FileOutputStream("./tmp/test_email.csv");
        int data = uploaded_file.getContent().read();
        while (data != -1) {
            // do something with data...
            outputStream.write(data);
            data = inputStream.read();
        }
        inputStream.close();
        outputStream.close();

        // Create a CsvConfiguration instance
        // Set the path for the CSV file
        // Set the separator for the CSV file (semicolon in this case)
        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setPath("./tmp/test_email.csv");
        csvConfiguration.setSeparator(";");

        // Table generate
        TableElementConfiguration tableElementConfiguration = new TableElementConfiguration();

        Element element = new ElementFactory("element_name",csvConfiguration, tableElementConfiguration).getElement();
        this.componentList.add(element);
        String componentId = element.getElementCode();
        System.out.println(componentId);

        // Send a response to web-client
        ctx.status(200).result(componentId);
    }

    /*
     * POST - /test-email
     */
    public void handleEmailData(Context context) {
        // templatehtml
        String dataEmail = context.formParam("data_email");

        //old
        //TemplateHtml templateHtml = new TemplateHtml();
        //templateHtml.setTemplateHtml(dataEmail);

        //new
        String templateHtml  = dataEmail;

        Properties properties = new Properties();
        //properties.put("cc", "");  properties.put("priority", "");
        String from = config.getString("email.from");
        String to = config.getString("email.to");
        String subject = "FM-dev-test";
        MailConfig mailConfig = new MailConfig(from, to, subject, properties);

        MailTemplate mailTemplate = new MailTemplate("FM-dev-testing", templateHtml, mailConfig);
        for (Element e : componentList) {
            mailTemplate.addElement(e);
        } // new version of adding elements

        EmailService emailService = new EmailService(new SmtpConfig());
        emailService.sendMail(mailTemplate);
        emailService.getResult();
        /* END Email */
        context.result("Noluyo");
    }
}
