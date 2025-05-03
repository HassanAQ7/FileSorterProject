package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.opencsv.exceptions.CsvException;

import exceptions.FileSkipException;

import com.opencsv.*;

import model.DataRecord;
import ui.DataParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

public class TestDataParser {

    @Test
    public void testNoCSVFileNoSkip() {
        DataParser csvFile = new DataParser("resources/nonexistent.csv", false);
        try {
            csvFile.parseRecords(0);
        } catch (IOException e) {
            // pass
        } catch (CsvException e) {
            fail("No error in CSV file handling");
        } catch (FileSkipException e) {
            fail("No skipping or lines specified");
        }
    }

    @Test
    public void testExistentCSVFileNoSkip() {
        DataParser csvFile = new DataParser("./resources/testFile.csv", false);
        try {
            List<DataRecord> records = csvFile.parseRecords(0);
            assertEquals(2, records.size());
            DataRecord record1 = records.get(0);
            assertEquals(2, record1.getColumnValues().size());
            assertTrue(record1.getColumnValues().containsKey("Variable1") &&
                    record1.getColumnValues().containsKey("Variable2"));
            assertEquals("1", record1.getColumnValues().get("Variable1"));
            assertEquals("Call", record1.getColumnValues().get("Variable2"));

            assertTrue(record1.getHeaders().contains("Variable1"));
            assertTrue(record1.getHeaders().contains("Variable2"));

            DataRecord record2 = records.get(1);
            assertEquals(2, record2.getColumnValues().size());
            assertTrue(record2.getColumnValues().containsKey("Variable1") &&
                    record2.getColumnValues().containsKey("Variable2"));
            assertEquals("5", record2.getColumnValues().get("Variable1"));
            assertEquals("Aloha", record2.getColumnValues().get("Variable2"));

            assertTrue(record2.getHeaders().contains("Variable1"));
            assertTrue(record2.getHeaders().contains("Variable2"));

        } catch (IOException e) {
            fail("Exception shouldn't have been thrown, file exists");
        } catch (CsvException e) {
            fail("No need for exception to be thrown");
        } catch (FileSkipException e) {
            fail("No skip and no lines specified");
        }
    }

    @Test
    public void testFileSkipRowsException() {
        DataParser csvFile = new DataParser("resources/testFile.csv", true);
        try {
            csvFile.parseRecords(0);
        } catch (IOException e) {
            fail("Exception shouldn't have been thrown, file exists");
        } catch (CsvException e) {
            fail("No need for exception to be thrown");
        } catch (FileSkipException e) {
            // pass
        }
    }

    @Test
    public void testSkipExistingFileRows() {
        DataParser csvFile = new DataParser("resources/testFile.csv", true);
        try {
            List<DataRecord> records = csvFile.parseRecords(1);
            assertEquals(1, records.size());

            DataRecord record1 = records.get(0);
            assertEquals(2, record1.getColumnValues().size());
            assertTrue(record1.getColumnValues().containsKey("1") &&
                    record1.getColumnValues().containsKey("Call"));

            assertEquals("5", record1.getColumnValues().get("1"));
            assertEquals("Aloha", record1.getColumnValues().get("Call"));

            assertTrue(record1.getHeaders().contains("1"));
            assertTrue(record1.getHeaders().contains("Call"));
        } catch (IOException e) {
            fail("Exception shouldn't have been thrown, file exists");
        } catch (CsvException e) {
            fail("No need for exception to be thrown");
        } catch (FileSkipException e) {
            fail("Skipping was specified with number of lines");
        }
    }

}
