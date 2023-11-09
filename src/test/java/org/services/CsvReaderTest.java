package org.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {
    private static final Logger logger = LoggerFactory.getLogger(CsvReaderTest.class);
    private static final String url = "your_database_url_here";
    private static final String fileName = "mySample";
    private static final String directory = "C:\\Users\\murat\\Desktop\\";
    private static final String separator = ";";
    private static final String fileExtension = ".csv";

    @BeforeAll
    public static void setUp() {
        File file = new File(directory + fileName + fileExtension);
        System.out.println("is file exists: " + file.exists());
        System.out.println("is file readable: " + file.canRead());
        System.out.println("file path: " + file);

    }

    @Test
    public void testGetResultSet() {
        CsvReader csvReader = new CsvReader(directory, fileName, separator, fileExtension);
        ResultSet results = csvReader.getResultSet();
        try {
            assertNotNull(results);
            assertTrue(results.next());

        } catch (Exception e) {
            throw new RuntimeException("Test failed: " + e.getMessage(), e);
        } finally {
            // Close the ResultSet, Statement, and Connection to release resources
            closeResultSet(results);
        }

    }

    protected ResultSet getTestGetResultSet () throws SQLException {
        CsvReader csvReader = new CsvReader(directory, fileName, separator, fileExtension);
        ResultSet results = csvReader.getResultSet();
        StringBuilder sb = new StringBuilder();
        ResultSetMetaData resultSetMetaData = results.getMetaData();
        for ( int i = 1; i <= results.getMetaData().getColumnCount() ; i++) {
            sb.append(resultSetMetaData.getColumnName(i))
                    .append(" ")
                    .append(resultSetMetaData.getColumnTypeName(i))
                    .append(" / ");
        }
        logger.info("result columns: " +sb );
        return results;
    }

    private void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception ignored) {
            }
        }
    }
}