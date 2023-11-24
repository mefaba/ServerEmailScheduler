package org.emailreportmanager.components;

import org.emailreportmanager.configurations.TableConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Random;

@Entity
@Table(name = "component_table")
public class TableComponent extends Component {
    @Lob
    @NotNull
    private String tableHtml;

    @NotNull
    @ManyToOne
    private TableConfiguration tableConfiguration;

    public TableComponent() {
        componentId = "$TABLE-ID-"+new Random().nextInt(100000)+"$";
    }

    @Override
    public String getComponentId() {
        return componentId;
    }

    public String getTableHtml() {
        return tableHtml;
    }

    public void setTableHtml(String tableHtml) {
        this.tableHtml = tableHtml;
    }

    public TableConfiguration getTableConfiguration() {
        return tableConfiguration;
    }

    public void setTableConfiguration(TableConfiguration tableConfiguration) {
        this.tableConfiguration = tableConfiguration;
    }

}

