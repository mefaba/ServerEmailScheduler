package org.configurations;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.nio.charset.Charset;
import java.util.Locale;


@Entity
@Table(name = "csv_configuration")
public class CsvConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

}
