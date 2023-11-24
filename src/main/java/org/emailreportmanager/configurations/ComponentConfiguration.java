package org.emailreportmanager.configurations;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ComponentConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

}
