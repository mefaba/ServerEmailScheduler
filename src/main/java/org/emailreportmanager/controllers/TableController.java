package org.emailreportmanager.controllers;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.emailreportmanager.entities.configurations.CsvConfiguration;
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

public class TableController {
    static List<Element> componentList = new ArrayList<>();

    public void onSubmitTable(Context ctx) throws IOException {
        // Recieve file from request body, Access request body
        UploadedFile uploaded_file = ctx.uploadedFiles().get(0);
        InputStream inputStream = uploaded_file.getContent();

        // Write to file
        OutputStream outputStream = Files.newOutputStream(Paths.get("./tmp/test_email.csv"));
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

        Element element = new ElementFactory("element_name", csvConfiguration, tableElementConfiguration).getElement();
        componentList.add(element);
        String componentId = element.getElementCode();
        System.out.println(componentId);

        // Send a response to web-client
        ctx.status(200).result(componentId);
    }

    /* Get HTML of Table component */
    public void htmxTable(Context ctx) throws IOException {
        ctx.render("templates/table.peb");
    }
}
