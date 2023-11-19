package org.emailreportmanager.controllers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import org.emailreportmanager.configurations.CsvConfiguration;
import org.emailreportmanager.configurations.repository.CsvConfigurationRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DataController {
    public static void handlePostRequest(Context ctx) {
        // Access request body
        String requestBody = ctx.body();
        System.out.println("Hello");
        System.out.println(requestBody);

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
