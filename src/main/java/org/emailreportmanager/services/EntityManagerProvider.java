package org.emailreportmanager.services;

import org.emailreportmanager.entities.elements.ElementRepository;
import org.emailreportmanager.entities.configurations.ElementConfigurationRepository;
import org.emailreportmanager.entities.configurations.DataSourceConfigurationRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("reportMailManagerPersistence");
    public static javax.persistence.EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public static DataSourceConfigurationRepository getDataSourceConfigurationRepository() {
        JpaRepositoryFactory factory = new JpaRepositoryFactory(getEntityManager());
        return factory.getRepository(DataSourceConfigurationRepository.class);
    }

    public static ElementConfigurationRepository getComponentConfigurationRepository() {
        JpaRepositoryFactory factory = new JpaRepositoryFactory(getEntityManager());
        return factory.getRepository(ElementConfigurationRepository.class);
    }

    public static ElementRepository getComponentRepository() {
        System.out.println("Component repo generated..");
        JpaRepositoryFactory factory = new JpaRepositoryFactory(getEntityManager());
        return factory.getRepository(ElementRepository.class);
    }

}
