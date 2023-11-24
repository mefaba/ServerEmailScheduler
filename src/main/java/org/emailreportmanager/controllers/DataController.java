package org.emailreportmanager.controllers;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.emailreportmanager.components.Component;
import org.emailreportmanager.components.TemplateHtml;
import org.emailreportmanager.configurations.CsvConfiguration;
import org.emailreportmanager.configurations.TableConfiguration;
import org.emailreportmanager.configurations.repository.DataSourceConfigurationRepository;
import org.emailreportmanager.services.EmailService;
import org.emailreportmanager.services.GenerateComponent;
import org.emailreportmanager.services.HtmlTableGenerator;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataController {
    HtmlTableGenerator htmlTableGenerator= new HtmlTableGenerator();
    public static void handlePostRequest(Context ctx) throws IOException {
        // Recieve file from request body, Access request body

        UploadedFile uploaded_file = ctx.uploadedFiles().get(0);
        InputStream inputStream = uploaded_file.getContent();

        //Write to file
        OutputStream outputStream = new FileOutputStream("./tmp/test_email.csv");
        int data = uploaded_file.getContent().read();
        while(data != -1) {
            //do something with data...
            outputStream.write(data);
            data = inputStream.read();
        }
        inputStream.close();
        outputStream.close();

        //populate entity
        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setPath("./tmp/test_email.csv");
        csvConfiguration.setSeparator(";");
        //Result set produce

        //Table generate

        //Send response back to client




        TableConfiguration tableConfiguration = new TableConfiguration();

        List<Component> componentList = new ArrayList<>();
        Component component = new GenerateComponent(csvConfiguration, tableConfiguration).getComponent();
        TemplateHtml templateHtml = new TemplateHtml();

        String componentId = component.getComponentId();
        System.out.println(componentId);
        templateHtml.setTemplateHtml("<html><body><h1>This is a Heading</h1><p>This is a paragraph.</p>"+ componentId + componentId+"<p>This is a footer.</p></body></html>");

        componentList.add(component);

        EmailService emailService = new EmailService(componentList, templateHtml);

        emailService.getResult();

        emailService.sendMail();






        // Process the data as needed
        // For example, you can parse JSON or perform other operations

        // Send a response
        ctx.status(200).result("POST request received successfully");
    }

}
