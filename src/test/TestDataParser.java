package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.opencsv.exceptions.CsvException;
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
    public void testNoCSVFile() {
        DataParser csvFile = new DataParser("resources/nonexistent.csv");
        try {
            csvFile.parseRecords();
        } catch (IOException e) {
            // pass
        } catch (CsvException e) {
            fail();
        }
    }

    @Test
    public void testExistentCSVFile() {
        DataParser csvFile = new DataParser("./resources/testFile.csv");
        try {
            List<DataRecord> records = csvFile.parseRecords();
            assertEquals(2, records.size());
            DataRecord record1 = records.get(0);
            assertEquals(2, record1.getColumnValues().size());
            assertTrue(record1.getColumnValues().containsKey("Variable1") &&
                    record1.getColumnValues().containsKey("Variable2"));
            assertEquals("1", record1.getColumnValues().get("Variable1"));
            assertEquals("Call", record1.getColumnValues().get("Variable2"));

            DataRecord record2 = records.get(1);
            assertEquals(2, record2.getColumnValues().size());
            assertTrue(record2.getColumnValues().containsKey("Variable1") &&
                    record2.getColumnValues().containsKey("Variable2"));
            assertEquals("5", record2.getColumnValues().get("Variable1"));
            assertEquals("Aloha", record2.getColumnValues().get("Variable2"));

        } catch (IOException e) {
            fail("Exception shouldn't have been thrown, file exists");
        } catch (CsvException e) {
            fail("No need for exception to be thrown");
        }
    }

}
