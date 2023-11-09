package org.services;

import java.sql.*;
import java.util.Properties;

public class CsvReader implements ResultSetProducer {
    String url, fileName;
    Properties props = new Properties();

    CsvReader(String directory,String fileName, String separator, String fileExtension) {
        this.fileName=fileName;

        props.put("fileExtension", fileExtension);
        props.put("indexedFiles", "true");
        this.url = "jdbc:relique:csv:" + directory + "?" + "separator="+separator + "&" + "fileExtension=" + fileExtension;
    }

    @Override
    public ResultSet getResultSet() {

        try {
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * from " + fileName);
            return results;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
