package org.services;

import jakarta.persistence.EntityManager;

public class SimpleEntityManagerProvider {

    private final EntityManager entityManager;

    public SimpleEntityManagerProvider(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}