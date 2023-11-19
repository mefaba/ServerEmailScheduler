package org.webdemo;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.configurations.CsvConfiguration;
import org.configurations.repository.CsvConfigurationRepository;
import org.jetbrains.annotations.NotNull;
import org.services.H2ConsoleStarter;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create();
        app.get("/hello", ctx -> ctx.result("Hello World"));
        app.get("/", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                Map<String,String> templateVariables = new HashMap<>();
                templateVariables.put("message", "Tanri Peeble!");
                context.render("templates/index.peb",templateVariables);
            }
        });

        CsvConfiguration csvConfiguration = new CsvConfiguration();
        csvConfiguration.setPath("/home/murat/IdeaProjects/ServerEmailScheduler/src/test/resources/test_0.csv");
        csvConfiguration.setSeparator(";");

        H2ConsoleStarter h2ConsoleStarter = new H2ConsoleStarter();
        h2ConsoleStarter.startServer();

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("csvConfiguration");
        EntityManager em = emf.createEntityManager();
        JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
        CsvConfigurationRepository repo = factory.getRepository(CsvConfigurationRepository.class);

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


        app.start(7070);
    }
}