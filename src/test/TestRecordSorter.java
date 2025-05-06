package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.DataRecord;
import model.RecordSorter;
import model.RecordComparator.CompareType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class TestRecordSorter {
    private RecordSorter sorter;

    private DataRecord r1;
    private DataRecord r2;
    private DataRecord r3;
    private DataRecord r4;

    private LinkedHashMap<String, String> m1;
    private LinkedHashMap<String, String> m2;
    private LinkedHashMap<String, String> m3;

    @BeforeEach
    public void setUp() {
        m1 = new LinkedHashMap<>();
        m2 = new LinkedHashMap<>();
        m3 = new LinkedHashMap<>();

        m1.put("Variable1", "3");
        m1.put("Variable2", "ShikiRyougi");
        m1.put("Variable3", "2024-08-15");
        r1 = new DataRecord(m1);

        m2.put("Variable1", "6");
        m2.put("Variable2", "AngelBeats");
        m2.put("Variable3", "2012-01-11");

        r2 = new DataRecord(m2);

        m3.put("Variable1", "10");
        m3.put("Variable2", "HitagiSenjougahara");
        m3.put("Variable3", "2014-03-11");

        r3 = new DataRecord(m3);
        r4 = new DataRecord(m3);

        List<DataRecord> records = new ArrayList<>();
        Collections.addAll(records, r1, r2, r3, r4);
        sorter = new RecordSorter(records);

    }

    @Test
    public void testConstructor() {
        assertFalse(sorter.getSortOrder());
    }

    @Test
    public void testSortNumericAscending() {
        try {
            sorter.sortRecords("Variable1", CompareType.NUMERIC);
        } catch (IllegalArgumentException e) {
            fail("Comparator shouldn't throw exception");
        }

        assertEquals(4, sorter.getRecords().size());

        assertEquals(r1, sorter.getRecords().get(0));
        assertEquals(r2, sorter.getRecords().get(1));
        assertEquals(r3, sorter.getRecords().get(2));
        assertEquals(r4, sorter.getRecords().get(3));

    }

    @Test
    public void testSortNumericDescending() {
        sorter.setSortOrder(false);
        try {
            sorter.sortRecords("Variable1", CompareType.NUMERIC);
        } catch (IllegalArgumentException e) {
            fail("Comparator shouldn't throw exception");
        }

        assertEquals(4, sorter.getRecords().size());

        assertEquals(r4, sorter.getRecords().get(0));
        assertEquals(r3, sorter.getRecords().get(1));
        assertEquals(r2, sorter.getRecords().get(2));
        assertEquals(r1, sorter.getRecords().get(3));

    }

    @Test
    public void testSortAlphabeticalAscending() {
        try {
            sorter.sortRecords("Variable2", CompareType.ALPHABETICAL);
        } catch (IllegalArgumentException e) {
            fail("Comparator shouldn't throw exception");
        }

        assertEquals(4, sorter.getRecords().size());

        assertEquals(r1, sorter.getRecords().get(0));
        assertEquals(r3, sorter.getRecords().get(1));
        assertEquals(r4, sorter.getRecords().get(2));
        assertEquals(r2, sorter.getRecords().get(3));
    }

    @Test
    public void testSortAlphabeticalDescending() {
        sorter.setSortOrder(false);
        try {
            sorter.sortRecords("Variable2", CompareType.ALPHABETICAL);
        } catch (IllegalArgumentException e) {
            fail("Comparator shouldn't throw exception");
        }

        assertEquals(4, sorter.getRecords().size());

        assertEquals(r2, sorter.getRecords().get(0));
        assertEquals(r4, sorter.getRecords().get(1));
        assertEquals(r3, sorter.getRecords().get(2));
        assertEquals(r1, sorter.getRecords().get(3));
    }

    @Test
    public void testSortDateAscending() {
        try {
            sorter.sortRecords("Variable3", CompareType.DATE);
        } catch (IllegalArgumentException e) {
            fail("Comparator shouldn't throw exception");
        }

        assertEquals(4, sorter.getRecords().size());

        assertEquals(r2, sorter.getRecords().get(0));
        assertEquals(r3, sorter.getRecords().get(1));
        assertEquals(r4, sorter.getRecords().get(2));
        assertEquals(r1, sorter.getRecords().get(3));
    }

    @Test
    public void testSortDateDescending() {
        sorter.setSortOrder(false);
        try {
            sorter.sortRecords("Variable3", CompareType.DATE);
        } catch (IllegalArgumentException e) {
            fail("Comparator shouldn't throw exception");
        }

        assertEquals(4, sorter.getRecords().size());

        assertEquals(r1, sorter.getRecords().get(0));
        assertEquals(r4, sorter.getRecords().get(1));
        assertEquals(r3, sorter.getRecords().get(2));
        assertEquals(r2, sorter.getRecords().get(3));
    }

    @Test
    public void testSortNumericParseIssue() {
        try {
            sorter.sortRecords("Variable2", CompareType.NUMERIC);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            // pass
        }

    }

    @Test
    public void testParseDateIssue() {
        try {
            sorter.sortRecords("Variable3", CompareType.ALPHABETICAL);
            fail("Exception should be thrown");
        } catch (IllegalArgumentException e) {
            // pass
        }
    }

}
