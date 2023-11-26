package org.emailreportmanager.entities.elements;

import org.emailreportmanager.entities.configurations.DataSourceConfiguration;
import org.emailreportmanager.entities.configurations.TableElementConfiguration;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


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
        this.tableHtml = renderHtmlWithData().toString();
        this.tableHtml = applyCssToHtmlTable(renderHtmlWithData().toString());
    }


    private String renderHtmlWithData () {
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
        return htmlTable.toString();
    }

    public String applyCssToHtmlTable(String htmlTable) {
        try {
            // Load CSS content
            String cssContent = new String(Files.readAllBytes(Paths.get(tableElementConfiguration.getCssFilePath())));

            // Parse the HTML table
            Document document = Jsoup.parse(htmlTable);
            document.outputSettings(new Document.OutputSettings().prettyPrint(false)); // To keep inline style format

            // Apply the CSS to the HTML elements
            applyCss(document, cssContent);

            // Return the HTML table as a string
            return document.body().html();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void applyCss(Document document, String cssContent) {
        // This is a simplified implementation
        // For a more comprehensive solution, consider integrating a CSS parser
        Elements elements = document.select("table, th, td, tr");
        for (org.jsoup.nodes.Element element : elements) {
            String tagName = element.tagName();
            String inlineStyle = extractCssForTag(cssContent, tagName);
            element.attr("style", inlineStyle);
        }
    }


    private String extractCssForTag(String cssContent, String tagName) {
        // Simple extraction based on the tag name
        // Note: This method doesn't handle complex CSS selectors; it's a basic implementation.
        int startIndex = cssContent.indexOf(tagName + " {");
        if (startIndex == -1) {
            return ""; // No CSS found for the tag
        }
        int endIndex = cssContent.indexOf("}", startIndex);
        if (endIndex == -1) {
            return ""; // No closing brace found
        }
        return cssContent.substring(startIndex + tagName.length() + 2, endIndex).trim();
    }


}

