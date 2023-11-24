package org.emailreportmanager.configurations;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.ResultSet;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class DataSourceConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    public abstract ResultSet produceResultSet();




}
