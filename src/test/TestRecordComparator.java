package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DataRecord;
import model.RecordComparator;
import model.RecordComparator.CompareType;
import ui.DataParser;

import java.text.ParseException;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class TestRecordComparator {
    private DataRecord r1;
    private DataRecord r2;
    private DataRecord r3;
    private DataRecord r4;

    private LinkedHashMap<String, String> m1;
    private LinkedHashMap<String, String> m2;
    private LinkedHashMap<String, String> m3;
    private LinkedHashMap<String, String> m4;

    private RecordComparator comparator;

    @BeforeEach
    public void setUp() {
        m1 = new LinkedHashMap<>();
        m2 = new LinkedHashMap<>();
        m3 = new LinkedHashMap<>();
        m4 = new LinkedHashMap<>();

        m1.put("Variable1", "3");
        m1.put("Variable2", "ShikiRyougi");
        m1.put("Variable3", "1998-08-15");
        r1 = new DataRecord(m1);

        m2.put("Variable1", "6");
        m2.put("Variable2", "MikiyaKokutou");
        m2.put("Variable3", "2012-01-11");

        r2 = new DataRecord(m2);

        m3.put("Variable1", "10");
        m3.put("Variable2", "HitagiSenjougahara");
        m3.put("Variable3", "2014-03-11");

        r3 = new DataRecord(m3);

        m4.put("Variable1", "10");
        m4.put("Variable2", "HitagiSenjougahara");
        m4.put("Variable3", "2014-03-11");

        r4 = new DataRecord(m4);

        comparator = new RecordComparator(null, null);

    }

    @Test
    public void testConstructor() {
        assertNull(comparator.getCategory());
        assertNull(comparator.getCompareType());
    }

    @Test
    public void testNumericComparisons() {
        comparator.setSortCategory("Variable1");
        comparator.setCompareType(CompareType.NUMERIC);

        try {
            int greaterThan = comparator.compare(r2, r1);
            assertEquals(1, greaterThan);
        } catch (NumberFormatException e) {
            fail("Exception shouldn't have been thrown, string is numeric");
        } catch (IllegalArgumentException e) {
            fail("A date is not being parsed");
        }

        try {
            int lessThan = comparator.compare(r1, r2);
            assertEquals(-1, lessThan);
        } catch (NumberFormatException e) {
            fail("Exception shouldn't have been thrown, string is numeric");
        } catch (IllegalArgumentException e) {
            fail("A date is not being parsed");
        }

        try {
            int sameAs = comparator.compare(r3, r4);
            assertEquals(0, sameAs);
        } catch (NumberFormatException e) {
            fail("Exception shouldn't have been thrown, string is numeric");
        } catch (IllegalArgumentException e) {
            fail("A date is not being parsed");
        }

    }

    @Test
    public void testAlphabeticalComparisons() {
        comparator.setSortCategory("Variable2");
        comparator.setCompareType(CompareType.ALPHABETICAL);

        try {
            int greaterThan = comparator.compare(r2, r1);
            assertEquals(1, greaterThan);
        } catch (IllegalArgumentException e) {
            fail("Strings are being compared, shouldn't throw exceptions");
        }

        try {
            int lessThan = comparator.compare(r1, r2);
            assertEquals(-1, lessThan);
        } catch (IllegalArgumentException e) {
            fail("Strings are being compared, shouldn't throw exceptions");
        }

        try {
            int sameAs = comparator.compare(r3, r4);
            assertEquals(0, sameAs);
        } catch (IllegalArgumentException e) {
            fail("Strings are being compared, shouldn't throw exceptions");
        }

    }

    @Test
    public void testDateComparisons() {
        comparator.setSortCategory("Variable3");
        comparator.setCompareType(CompareType.DATE);

        try {
            int greaterThan = comparator.compare(r2, r1);
            assertEquals(1, greaterThan);
        } catch (NumberFormatException e) {
            fail("String represents a date type");
        } catch (IllegalArgumentException e) {
            fail("Date format is correct");
        }

        try {
            int lessThan = comparator.compare(r1, r2);
            assertEquals(-1, lessThan);
        } catch (NumberFormatException e) {
            fail("String represents a date type");
        } catch (IllegalArgumentException e) {
            fail("Date format is correct");
        }

        try {
            int sameAs = comparator.compare(r3, r4);
            assertEquals(0, sameAs);
        } catch (NumberFormatException e) {
            fail("String represents a date type");
        } catch (IllegalArgumentException e) {
            fail("Date format is correct");
        }

    }

    @Test
    public void testIncorrectNumericParsing() {
        comparator.setSortCategory("Variable2");
        comparator.setCompareType(CompareType.NUMERIC);

        try {
            comparator.compare(r2, r1);
        } catch (NumberFormatException e) {
            // pass
        } catch (IllegalArgumentException e) {
            fail("Date not being parsed");
        }
    }

    @Test
    public void testIncorrectDateParsing() {
        r3.getColumnValues().put("WrongDate", "08-09-1999-07-02-UTC");
        r4.getColumnValues().put("WrongDate", "08-09-1999-07-02-UTC");
        comparator.setSortCategory("WrongDate");
        comparator.setCompareType(CompareType.DATE);

        try {
            comparator.compare(r3, r4);
        } catch (NumberFormatException e) {
            fail("Numeric type not being parsed");
        } catch (IllegalArgumentException e) {
            // pass
        }
    }

}
