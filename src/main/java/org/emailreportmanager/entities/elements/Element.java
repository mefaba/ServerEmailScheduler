package org.emailreportmanager.entities.elements;

import org.emailreportmanager.entities.configurations.DataSourceConfiguration;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Audited
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @NotNull
    String elementCode;

    @NotNull
    @Column(unique = true)
    String elementName;

    @NotNull
    @OneToOne( cascade = CascadeType.ALL)
    DataSourceConfiguration dataSourceConfiguration;

    public abstract void render();

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementCode() {
        return this.elementCode;
    }

    public DataSourceConfiguration getDataSourceConfiguration() {
        return dataSourceConfiguration;
    }

    public void setDataSourceConfiguration(DataSourceConfiguration dataSourceConfiguration) {
        this.dataSourceConfiguration = dataSourceConfiguration;
    }



}
