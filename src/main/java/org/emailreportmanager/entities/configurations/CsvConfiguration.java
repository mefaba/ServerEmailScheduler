package org.emailreportmanager.entities.configurations;

import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.File;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.Locale;
import java.util.Properties;


@Entity
@Audited
public class CsvConfiguration extends DataSourceConfiguration {

    private String charset = Charset.defaultCharset().name();
    private String columnTypes = null;
    private Boolean defectiveHeaders = false;
    @NotNull
    private String path;
    private String headerline = null;
    private Boolean ignoreNonParseableLines = false;
    private String missingValue = null;
    private String quotechar = "\"";
    private String locale = Locale.getDefault().toString();
    @NotNull
    private String separator = ",";
    private Boolean suppressHeaders = false;
    private String timestampFormat = "yyyy-MM-dd HH:mm:ss";
    private String timeFormat = "HH:mm:ss";
    private String dateFormat = "yyyy-MM-dd";
    private Boolean trimHeaders = true;
    private Boolean trimValues = false;
    @NotNull
    private Boolean isDeleteFlag = false;

    // Getters and Setters
    public String getCharsetInfo() {
        return "Defines the character set name of the files being read, such as UTF-16.";
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getColumnTypes() {
        return columnTypes;
    }

    public void setColumnTypes(String columnTypes) {
        this.columnTypes = columnTypes;
    }

    public Boolean getDefectiveHeaders() {
        return defectiveHeaders;
    }

    public void setDefectiveHeaders(Boolean defectiveHeaders) {
        this.defectiveHeaders = defectiveHeaders;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHeaderline() {
        return headerline;
    }

    public void setHeaderline(String headerline) {
        this.headerline = headerline;
    }

    public Boolean getIgnoreNonParseableLines() {
        return ignoreNonParseableLines;
    }

    public void setIgnoreNonParseableLines(Boolean ignoreNonParseableLines) {
        this.ignoreNonParseableLines = ignoreNonParseableLines;
    }

    public String getMissingValue() {
        return missingValue;
    }

    public void setMissingValue(String missingValue) {
        this.missingValue = missingValue;
    }

    public String getQuotechar() {
        return quotechar;
    }

    public void setQuotechar(String quotechar) {
        this.quotechar = quotechar;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public Boolean getSuppressHeaders() {
        return suppressHeaders;
    }

    public void setSuppressHeaders(Boolean suppressHeaders) {
        this.suppressHeaders = suppressHeaders;
    }

    public String getTimestampFormat() {
        return timestampFormat;
    }

    public void setTimestampFormat(String timestampFormat) {
        this.timestampFormat = timestampFormat;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public Boolean getTrimHeaders() {
        return trimHeaders;
    }

    public void setTrimHeaders(Boolean trimHeaders) {
        this.trimHeaders = trimHeaders;
    }

    public Boolean getTrimValues() {
        return trimValues;
    }

    public void setTrimValues(Boolean trimValues) {
        this.trimValues = trimValues;
    }
    public Boolean getDeleteFlag() {
        return isDeleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        isDeleteFlag = deleteFlag;
    }


    @Override
    public ResultSet produceResultSet() {

        Logger logger = LoggerFactory.getLogger(this.getClass());
        String url, fileBaseName = "";
        Properties props = new Properties();

        System.out.println("000000000 running with CsvConfiguration");
        File file = new File(getPath());
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
        props.put("charset", getCharset());
        if (getColumnTypes() != null)
            props.put("columnTypes", getColumnTypes());
        props.put("defectiveHeaders", getDefectiveHeaders());
        if (getHeaderline() != null)
            props.put("headerline", getHeaderline());
        props.put("ignoreNonParseableLines", getIgnoreNonParseableLines());
        if (getMissingValue() != null)
            props.put("missingValue", getMissingValue());
        props.put("quotechar", getQuotechar());
        props.put("locale", getLocale());
        props.put("separator", getSeparator());
        props.put("suppressHeaders", getSuppressHeaders());
        props.put("timestampFormat", getTimestampFormat());
        props.put("timeFormat", getTimeFormat());
        props.put("dateFormat", getDateFormat());
        props.put("trimHeaders", getTrimHeaders());
        props.put("trimValues", getTrimValues());
        url = "jdbc:relique:csv:" + directory +"/" ;

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
