package org.emailreportmanager.controllers;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import org.emailreportmanager.configurations.CsvConfiguration;
import org.emailreportmanager.configurations.repository.CsvConfigurationRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DataController {
    public static void handlePostRequest(Context ctx) throws IOException {
        // Access request body
        UploadedFile uploaded_file = ctx.uploadedFiles().get(0);
        InputStream inputStream = uploaded_file.getContent();
        
        OutputStream outputStream = new FileOutputStream("C:\\Temp\\text1.csv");
        int data = uploaded_file.getContent().read();
        while(data != -1) {
            //do something with data...
            outputStream.write(data);
            data = inputStream.read();

        }
        inputStream.close();
        outputStream.close();



//        for (Map.Entry<String, List<String>> entry : requestBodyMap.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
        //populate entity
        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setPath("/home/murat/IdeaProjects/ServerEmailScheduler/src/test/resources/test_0.csv");
        csvConfiguration.setSeparator(";");


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("csvConfiguration");
        EntityManager em = emf.createEntityManager();
        JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
        CsvConfigurationRepository repo = factory.getRepository(CsvConfigurationRepository.class);


        //save populated data into database
        try {
            em.getTransaction().begin();
            repo.save(csvConfiguration);
            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        // Process the data as needed
        // For example, you can parse JSON or perform other operations

        // Send a response
        ctx.status(200).result("POST request received successfully murat");
    }

}
