package model;


import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Comparator;

// Represents a Comparator that compares different DataRecords
// Possesses enum which represents the different strategies to sort data records
public class RecordComparator implements Comparator<DataRecord> {
    public enum CompareType {
        NUMERIC, ALPHABETICAL, DATE
    }

    private String category;
    private CompareType type;

    // EFFECTS: Creates a new Comparator with provided category to sort by
    // and provided comparison strategy
    public RecordComparator(String category, CompareType type) {
        this.category = category;
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public CompareType getCompareType() {
        return type;
    }

    public void setCompareType(CompareType type) {
        this.type = type;
    }

    public void setSortCategory(String category) {
        this.category = category;
    }

    @Override
    public int compare(DataRecord o1, DataRecord o2) {
        String stringVal1 = o1.getColumnValues().get(category);
        String stringVal2 = o2.getColumnValues().get(category);
        if (type == CompareType.NUMERIC) {
            return Double.compare(Double.parseDouble(stringVal1), Double.parseDouble(stringVal2));
        } else if (type == CompareType.ALPHABETICAL) {
            return stringVal1.compareTo(stringVal2);
        } else {
            String[] dateFormats = { "yyyy/MM/dd", "dd/MM/yyyy", "yyyy-MM-dd" };
            try {
                return DateUtils.parseDateStrictly(stringVal1, dateFormats).compareTo(
                        DateUtils.parseDateStrictly(stringVal2, dateFormats));
            } catch (ParseException e) {
                throw new IllegalArgumentException("Wrong Date format");
            }
        }

    }
}
