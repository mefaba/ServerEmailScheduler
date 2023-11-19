package org.emailreportmanager.services;

import org.emailreportmanager.configurations.CsvConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.*;
import java.util.Properties;

public class ResultSetProducer {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    String url, fileBaseName;
    Properties props = new Properties();

    ResultSetProducer(CsvConfiguration csvConfiguration) {
        File file = new File(csvConfiguration.getPath());
        String directory =  file.getParent();
        String fileName = file.getName();
        String fileExtension;

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex <= fileName.length() - 2) {
            fileBaseName = fileName.substring(0, dotIndex);
        }

        if (dotIndex > 0 && dotIndex <= fileName.length() - 2) {
            fileExtension = fileName.substring( dotIndex);
        } else {
            fileExtension = ".csv";
        }

        props.put("fileExtension", fileExtension);
        props.put("charset", csvConfiguration.getCharset());
        if (csvConfiguration.getColumnTypes() != null)
            props.put("columnTypes", csvConfiguration.getColumnTypes());
        props.put("defectiveHeaders", csvConfiguration.getDefectiveHeaders());
        if (csvConfiguration.getHeaderline() != null)
            props.put("headerline", csvConfiguration.getHeaderline());
        props.put("ignoreNonParseableLines", csvConfiguration.getIgnoreNonParseableLines());
        if (csvConfiguration.getMissingValue() != null)
            props.put("missingValue", csvConfiguration.getMissingValue());
        props.put("quotechar", csvConfiguration.getQuotechar());
        props.put("locale", csvConfiguration.getLocale());
        props.put("separator", csvConfiguration.getSeparator());
        props.put("suppressHeaders", csvConfiguration.getSuppressHeaders());
        props.put("timestampFormat", csvConfiguration.getTimestampFormat());
        props.put("timeFormat",csvConfiguration.getTimeFormat());
        props.put("dateFormat",csvConfiguration.getDateFormat());
        props.put("trimHeaders",csvConfiguration.getTrimHeaders());
        props.put("trimValues",csvConfiguration.getTrimValues());
        this.url = "jdbc:relique:csv:" + directory +"/" ;
    }

    public ResultSet getResultSet() {

        try {
            Class.forName("org.relique.jdbc.csv.CsvDriver");
            logger.info("url: " + url);
            logger.info("props: " + props);
            Connection conn = DriverManager.getConnection(url, props);
            logger.info("connection: " + conn.getSchema());
            Statement stmt = conn.createStatement();
            String query = "SELECT * from " + fileBaseName ;
            logger.info("query: " + query);
            return stmt.executeQuery(query);


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
