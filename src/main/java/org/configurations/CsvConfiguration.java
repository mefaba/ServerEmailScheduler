package org.configurations;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.lang.Nullable;


@Entity
@Table(name = "csv_configuration")
public class CsvConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String charset ;
    private String columnTypes ;
    private Boolean defectiveHeaders;
    @NotEmpty
    private String path;
    private String headerline;
    private Boolean ignoreNonParseableLines ;
    private String missingValue;
    private Character quotechar;
    private String locale;
    @NotEmpty
    private String separator;
    private Boolean suppressHeaders;
    private String timestampFormat ;
    private String timeFormat ;
    private String dateFormat ;
    private Boolean trimHeaders ;
    private Boolean trimValues ;
    @NotEmpty
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

    public Character getQuotechar() {
        return quotechar;
    }

    public void setQuotechar(Character quotechar) {
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
