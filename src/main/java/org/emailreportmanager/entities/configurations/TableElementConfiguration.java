package org.emailreportmanager.entities.configurations;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(name = "component_config_table")
public class TableElementConfiguration extends ElementConfiguration {

    private String someConfig;
    private String someOtherConfig;

}
