package org.emailreportmanager.frontend.controllers;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.emailreportmanager.entities.MailTemplate;
import org.emailreportmanager.entities.configurations.CsvConfiguration;
import org.emailreportmanager.entities.configurations.MailConfig;
import org.emailreportmanager.entities.configurations.TableElementConfiguration;
import org.emailreportmanager.entities.elements.Element;
import org.emailreportmanager.services.ElementFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WebUIController {
    WebUIController(Javalin app) {
        app.post("/test-email", this::formSubmitMain);
        app.post("/tool-table", this::formSubmitTableTool);
    }

    private void formSubmitMain(Context ctx) {
        // templatehtml
        String rawEmailData = ctx.formParam("data_email");

        Properties properties = new Properties();
        // properties.put("cc", ""); properties.put("priority", "");
        String from = ctx.formParam("emailFrom");
        String to = ctx.formParam("emailTo");
        String subject = ctx.formParam("emailSubject");
        MailConfig mailConfig = new MailConfig(from, to, subject, properties);

        MailTemplate mailTemplate = new MailTemplate("FM-dev-testing", rawEmailData, mailConfig);
    }

    private void formSubmitTableTool(Context ctx) throws IOException {
        List<Element> componentList = new ArrayList<>();

        // Recieve file from request body, Access request body
        UploadedFile uploaded_file = ctx.uploadedFiles().get(0);
        InputStream inputStream = uploaded_file.getContent();

        // Write to file
        OutputStream outputStream = Files.newOutputStream(Paths.get("./tmp/test_email.csv"));
        int data = uploaded_file.getContent().read();
        while (data != -1) {
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

        Element element = new ElementFactory("element_name", csvConfiguration, tableElementConfiguration).getElement();
        componentList.add(element);
        String componentId = element.getElementCode();
        System.out.println(componentId);

        // Send a response to web-client
        ctx.status(200).result(componentId);
    }
}
