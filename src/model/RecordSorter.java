package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// Represents the system that can sort records in terms
// of some specified column and algorithm
public class RecordSorter {
    private List<DataRecord> records;
    private boolean ascending;

    // EFFECTS: Constructs a new RecordSorter with provided list of records to sort,
    // and sorts in ascending order
    public RecordSorter(List<DataRecord> records) {
        this.records = records;
        this.ascending = true;
    }

    public List<DataRecord> getRecords() {
        return records;
    }

    public boolean getSortOrder() {
        return ascending;
    }

    public void setSortOrder(boolean ascending) {
        this.ascending = ascending;
    }

    // MODIFIES: this
    // EFFECTS: Sorts the provided list of records based on a category and
    // ComparisonType
    // if ascending is true, sorts in ascending order, if false it does so in the
    // reverse order
    public void sortRecords(String category, RecordComparator.CompareType type) {
        RecordComparator comparator = new RecordComparator(category, type);
        if (ascending) {
            Collections.sort(records, comparator);
        } else {
            Comparator<DataRecord> newComparator = Collections.reverseOrder(comparator);
            Collections.sort(records, newComparator);
        }

    }

}
