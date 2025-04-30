package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exceptions.ValueNotFoundException;
import model.DataRecord;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.LinkedHashMap;


public class TestDataRecord {
    private DataRecord record;
    private Map<String, String> columnValues;


    @BeforeEach
    public void setUp() {
        columnValues = new LinkedHashMap<>();
        columnValues.put("Client", "Alan");
        columnValues.put("Purchased Amount", "150");
        columnValues.put("Subscription", "FALSE");
        record = new DataRecord(columnValues);
    }

    @Test
    public void testGetValueWithoutException() {
        try {
            String value = record.getValue("Client");
            assertEquals("Alan", value);
            String value2 = record.getValue("Purchased Amount");
            assertEquals("150", value2);
            String value3 = record.getValue("Subscription");
            assertEquals("FALSE", value3);

        } catch (ValueNotFoundException e) {
            fail("Exception shouldn't have been thrown");
        }

    }

    @Test
    public void testGetValueException() {
        try {
            record.getValue("random non-existent thingy");
            fail("Exception should've been thrown");
        } catch (ValueNotFoundException e) {
            // pass
        }
    }



}
