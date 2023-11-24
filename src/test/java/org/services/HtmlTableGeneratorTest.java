package org.services;

import org.emailreportmanager.services.HtmlTableGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HtmlTableGeneratorTest {
/*
    public static boolean isValidHtml(String html) {
        try {
            // Parse the HTML using jsoup with a forgiving Parser
            Document doc = Jsoup.parse(html, "", Parser.htmlParser());

            // If parsing succeeds, the HTML syntax is valid
            return true;
        } catch (Exception e) {
            // If parsing fails, the HTML syntax is not valid
            return false;
        }
    }

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {

        MockitoAnnotations.openMocks(this);

        // Mock the behavior of ResultSet to return sample data
        ResultSetMetaData metaData = mock(ResultSetMetaData.class);


        // Define the column names and types (you can customize this based on your ResultSet structure)
        String[] columnNames = {"Column1", "Column2", "Column3"};
        String[] columnTypes = {"VARCHAR", "INTEGER", "DOUBLE"};

        // Mock the ResultSet behavior
        for (int i = 0; i < columnNames.length; i++) {
            // Mock column names and types
            Mockito.when(metaData.getColumnName(i + 1)).thenReturn(columnNames[i]);
            Mockito.when(metaData.getColumnTypeName(i + 1)).thenReturn(columnTypes[i]);

            // Mock sample data for each column
            switch (columnTypes[i]) {
                case "VARCHAR":
                    Mockito.when(resultSet.getString(i + 1)).thenReturn("Value" + (i + 1));
                    break;
                case "INTEGER":
                    Mockito.when(resultSet.getInt(i + 1)).thenReturn(i + 1);
                    break;
                case "DOUBLE":
                    Mockito.when(resultSet.getDouble(i + 1)).thenReturn((double) (i + 1));
                    break;
            }
        }
        Mockito.when(resultSet.getMetaData()).thenReturn(metaData);
        Mockito.when(metaData.getColumnCount()).thenReturn(3);

        Mockito.when(resultSet.next())
                .thenReturn(true)  // Simulate that there are rows to process
                .thenReturn(false); // Simulate the end of the result set
    }

    @Test
    public void testGenerateHtmlTableWithMock() throws SQLException {
        // Call your generateHtmlTable method with the mocked ResultSet

        String htmlTable = HtmlTableGenerator.generateHtmlTable(resultSet);

        // Define your expected HTML table structure based on the sample data and ResultSet structure
        String expectedHtmlTable = "<table border='1'><thead><tr><th>Column1</th><th>Column2</th><th>Column3</th></tr></thead>"
                + "<tbody><tr><td>Value1</td><td>2</td><td>3.0</td></tr></tbody></table>";

        // Assert that the generated HTML table matches the expected structure
        assertTrue(isValidHtml(htmlTable));
        assertEquals(expectedHtmlTable, htmlTable);
    }

    @Test
    public void testGenerateHtmlTableWithMySample() throws SQLException {
        //CsvReaderTest csvReaderTest = new CsvReaderTest(ConfigurationService);
        //String htmlTable = HtmlTableGenerator.generateHtmlTable(csvReaderTest.getTestGetResultSet());

        //System.out.println(htmlTable);

        // Assert that the generated HTML table matches the expected structure
        //assertTrue(isValidHtml(htmlTable));
    }

         */
}