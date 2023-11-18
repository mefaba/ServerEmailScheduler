package org.configurations;

public enum CsvConfigurationEnum {
    CHARSET("String", "Java default", "Defines the character set name of the files being read, such as UTF-16."),
    COLUMNTYPES("String", "all Strings", "A comma-separated list defining SQL data types for columns in tables."),
    DEFECTIVEHEADERS("Boolean", "False", "Replace empty column name with COLUMNx."),
    FILEEXTENSION("String", ".csv", "Specifies file extension of the CSV files."),
    HEADERLINE("String", "None", "Specifies a custom header line for tables."),
    IGNORENONPARSEABLELINES("Boolean", "False", "Ignores lines with incorrect number of columns."),
    ISHEADERFIXEDWIDTH("Boolean", "True", "Specifies if header line is fixed width."),
    MISSINGVALUE("String", "null", "Value for missing columns."),
    QUOTECHAR("Character", "\"", "Defines quote character."),
    LOCALE("String", "Java default", "Defines locale for parsing timestamps."),
    SEPARATOR("String", ",", "Defines column separator."),
    SUPPRESSHEADERS("Boolean", "False", "Indicates if file does not contain column headers."),
    TIMESTAMPFORMAT("String", "yyyy-MM-dd HH:mm:ss", "Format for Timestamp columns."),
    TIMEFORMAT("String", "HH:mm:ss", "Format for Time columns."),
    DATEFORMAT("String", "yyyy-MM-dd", "Format for Date columns."),
    TRIMHEADERS("Boolean", "True", "Trims whitespace from header names."),
    TRIMVALUES("Boolean", "False", "Trims whitespace from column values.");

    private final String type;
    private final String defaultValue;
    private final String description;

    CsvConfigurationEnum(String type, String defaultValue, String description) {
        this.type = type;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getDescription() {
        return description;
    }
}
