package org.emailreportmanager.entities.configurations;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ElementConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

}
