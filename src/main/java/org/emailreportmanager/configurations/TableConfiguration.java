package org.emailreportmanager.configurations;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Random;

@Entity
@Table(name = "component_config_table")
public class TableConfiguration extends ComponentConfiguration {

    private String someConfig;
    private String someOtherConfig;

}
