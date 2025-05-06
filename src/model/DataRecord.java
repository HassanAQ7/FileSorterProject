package model;

import java.util.LinkedHashMap;
import java.util.Set;


// Represents a singular row of the specific CSV dataset loaded in
public class DataRecord {
    private LinkedHashMap<String, String> columnValues;
   
    // EFFECTS: constructs a DataRecord with given column values
    public DataRecord(LinkedHashMap<String, String> columnValues) {
        this.columnValues = columnValues;
    }

    public LinkedHashMap<String, String> getColumnValues() {
        return columnValues;
    }

    // EFFECTS: Returns the header names of this row as a set
    public Set<String> getHeaders() {
        return columnValues.keySet();
    }
    



}
