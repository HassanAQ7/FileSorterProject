package model;

import java.util.Map;
import java.util.Set;

import exceptions.ValueNotFoundException;

// Represents a singular row of the specific CSV dataset loaded in
public class DataRecord {
    private Map<String, String> columnValues;

    // EFFECTS: constructs a DataRecord with given column values
    public DataRecord(Map<String, String> columnValues) {
        this.columnValues = columnValues;
    }

    // EFFECTS: Returns a value from a specific header name from the dataset
    // Throws exception if no such value exists.
    public String getValue(String header) throws ValueNotFoundException {
        String value = columnValues.get(header);
        if (value != null) {
            return value;
        }

        throw new ValueNotFoundException("Cannot find value");
    }

    public Map<String, String> getColumnValues() {
        return columnValues;
    }

    // EFFECTS: Returns the header names of this row as a set
    public Set<String> getHeaders() {
        return columnValues.keySet();
    }
    



}
