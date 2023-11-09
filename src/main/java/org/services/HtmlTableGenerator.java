package org.services;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.StringJoiner;

public class HtmlTableGenerator {

    public static String generateHtmlTable(ResultSet resultSet) throws SQLException {
        StringBuilder htmlTable = new StringBuilder();
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

        return htmlTable.toString();
    }

}

