package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

// Represents a Comparator that compares different DataRecords
// Possesses enum which represents the different strategies to sort data records
public class RecordComparator implements Comparator<DataRecord> {
    public enum CompareType {
        NUMERIC, ALPHABETICAL, DATE
    }

    // EFFECTS: Creates a new Comparator with provided category to sort by
    // and provided comparison strategy
    public RecordComparator(String category, CompareType type) {

    }

    public void setSortCategory(String category) {

    }

    @Override
    public int compare(DataRecord o1, DataRecord o2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compare'");
    }
}
