package model;

import java.util.List;

// Represents the system that can sort records in terms
// of some specified column and algorithm
public class RecordSorter {

    // EFFECTS: Constructs a new RecordSorter with provided list of records to sort,
    // and sorts in ascending order
    public RecordSorter(List<DataRecord> records) {

    }

    public List<DataRecord> getRecords() {
        return null; // stub
    }

    // MODIFIES: this
    // EFFECTS: Sorts the provided list of records based on a category and
    // ComparisonType
    // if ascending is true, sorts in ascending order, if false it does so in the
    // reverse order
    public void sortRecords(String category, RecordComparator.CompareType type) {

    }

    public void setSortOrder(boolean ascending) {

    }

}
