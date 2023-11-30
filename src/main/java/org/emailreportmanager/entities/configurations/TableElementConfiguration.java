package org.emailreportmanager.entities.configurations;

import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Table(name = "component_config_table")
public class TableElementConfiguration extends ElementConfiguration {


    String cssFilePath = "./src/main/resources/element_styles/table_styles/modern.css";



    String someOtherConfig;

    public String getCssFilePath() {
        return cssFilePath;
    }

    public void setCssFilePath(String cssFilePath) {
        this.cssFilePath = cssFilePath;
    }


}
