package org.emailreportmanager.services;

import org.emailreportmanager.components.Component;
import org.emailreportmanager.components.TableComponent;
import org.emailreportmanager.configurations.DataSourceConfiguration;
import org.emailreportmanager.configurations.TableConfiguration;
import org.emailreportmanager.configurations.repository.DataSourceConfigurationRepository;
import org.emailreportmanager.configurations.repository.ComponentConfigurationRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenerateComponent {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("reportMailManagerPersistence");
    EntityManager em = emf.createEntityManager();
    JpaRepositoryFactory factory = new JpaRepositoryFactory(em);
    DataSourceConfigurationRepository dsConfigRepo = factory.getRepository(DataSourceConfigurationRepository.class);
    ComponentConfigurationRepository compConfigRepo = factory.getRepository(ComponentConfigurationRepository.class);

    Component component;

    public GenerateComponent(DataSourceConfiguration dsConfiguration, TableConfiguration tableConfiguration) {

        TableComponent tableComponent  = new TableComponent();
        try {
            em.getTransaction().begin();
            dsConfigRepo.save(dsConfiguration);
            compConfigRepo.save(tableConfiguration);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        tableComponent.setTableConfiguration(tableConfiguration);
        tableComponent.setTableHtml(HtmlTableGenerator.generateHtmlTable(dsConfiguration));

        this.component = tableComponent;

    }

    public String getComponentId() {
        return component.getComponentId();
    }

    public Component getComponent() {
        return component;
    }


}
