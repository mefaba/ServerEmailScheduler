package org.emailreportmanager.services;

import org.emailreportmanager.entities.elements.Element;
import org.emailreportmanager.entities.elements.TableElement;
import org.emailreportmanager.entities.configurations.DataSourceConfiguration;
import org.emailreportmanager.entities.configurations.TableElementConfiguration;

public class ElementFactory {

    javax.persistence.EntityManager em = EntityManagerProvider.getEntityManager();

    Element element;

    public ElementFactory(String elementName, DataSourceConfiguration dsConfiguration, TableElementConfiguration tableElementConfiguration) {
        try {
            em.getTransaction().begin();
            System.out.println("transaction begin with db");

            // Assuming save methods perform persist operation
            em.persist(dsConfiguration);
            em.persist(tableElementConfiguration);

            TableElement tableElement = new TableElement(elementName, tableElementConfiguration, dsConfiguration);
            em.persist(tableElement);

            em.getTransaction().commit();
            System.out.println("Transaction committed");

            this.element = tableElement;
        } catch (Exception e) {
            System.out.println("exception");
            e.printStackTrace();
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            System.out.println("closing the em ");
            em.close();
        }
    }

    @Deprecated
    public String getComponentId() {
        return element.getElementCode();
    }

    public String getElementCode() {
        return element.getElementCode();
    }

    public Element getElement() {
        return element;
    }

}