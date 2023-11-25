package org.emailreportmanager.controllers;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.emailreportmanager.components.Component;
import org.emailreportmanager.components.TemplateHtml;
import org.emailreportmanager.configurations.CsvConfiguration;
import org.emailreportmanager.configurations.TableConfiguration;
import org.emailreportmanager.services.EmailService;
import org.emailreportmanager.services.GenerateComponent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataController {
    List<Component> componentList = new ArrayList<>();

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
        TableConfiguration tableConfiguration = new TableConfiguration();

        Component component = new GenerateComponent(csvConfiguration, tableConfiguration).getComponent();
        this.componentList.add(component);
        String componentId = component.getComponentId();
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

        TemplateHtml templateHtml = new TemplateHtml();
        templateHtml.setTemplateHtml(dataEmail);

        EmailService emailService = new EmailService(componentList, templateHtml);
        emailService.sendMail();
        emailService.getResult();
        /* END Email */
        context.result("Noluyo");
    }
}
