package org.emailreportmanager.entities.configurations;

import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.ResultSet;

@Entity
@Audited
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DataSourceConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    public abstract ResultSet produceResultSet();


}
