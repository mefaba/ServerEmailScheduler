package org.emailreportmanager.entities.elements;

import org.emailreportmanager.entities.configurations.DataSourceConfiguration;
import org.emailreportmanager.entities.configurations.TableElementConfiguration;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Random;

@Entity
@Audited
@Table(name = "component_table")
public class TableElement extends Element {

    @NotAudited
    @Transient
    private String tableHtml;

    @NotNull
    @ManyToOne
    private TableElementConfiguration tableElementConfiguration;

    public TableElement(String elementName, TableElementConfiguration tableElementConfiguration, DataSourceConfiguration dataSourceConfiguration) {
        this.tableElementConfiguration = tableElementConfiguration;
        this.dataSourceConfiguration = dataSourceConfiguration;
        this.elementName = elementName;
        this.elementCode = "$T-"+elementName+"-"+new Random().nextInt(1000)+"$";
    }

    public String getTableHtml() {
        return tableHtml;
    }

    public TableElementConfiguration getTableConfiguration() {
        return tableElementConfiguration;
    }

    public void setTableConfiguration(TableElementConfiguration tableElementConfiguration) {
        this.tableElementConfiguration = tableElementConfiguration;
    }

    @Override
    public void render() {
        StringBuilder htmlTable = new StringBuilder();
        try {
            ResultSet resultSet =  dataSourceConfiguration.produceResultSet();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            // Start the HTML table
            htmlTable.append("<table border='1'><thead><tr>");

            // Create table header row
            for (int i = 1; i <= columnCount; i++) {
                htmlTable.append("<th>").append(metaData.getColumnName(i)).append("</th>");
            }
            htmlTable.append("</tr></thead><tbody>");

            // Create table data rows
            while (resultSet.next()) {
                htmlTable.append("<tr>");
                for (int i = 1; i <= columnCount; i++) {
                    switch (metaData.getColumnTypeName(i)) {
                        case "VARCHAR":
                        case "String":
                            htmlTable.append("<td>").append(resultSet.getString(i)).append("</td>");
                            break;
                        case "INTEGER":
                            htmlTable.append("<td>").append(resultSet.getInt(i)).append("</td>");
                            break;
                        case "DOUBLE":
                            htmlTable.append("<td>").append(resultSet.getDouble(i)).append("</td>");
                            break;
                    }
                }
                htmlTable.append("</tr>");
            }

            // End the HTML table
            htmlTable.append("</tbody></table>");
        } catch (SQLException e) {
            System.out.println(e);
        }

        this.tableHtml = htmlTable.toString();
    }

}

