package ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.DataRecord;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

// Represents a DataParser that can get records from a given path
public class DataParser {
    private String path;

    // EFFECTS: Constructs a DataParser with provided path
    public DataParser(String path) {
        this.path = path;
    }


    // EFFECTS: Parses DataRecords and fields from CSV File and returns it
    // Throws IOException if file cannot be found
    // or CsvException if other issues with reading file arises
    public List<DataRecord> parseRecords() throws IOException, CsvException {
        List<DataRecord> records = new LinkedList<>();
        FileReader reader = new FileReader(path);

        CSVReader recordReader = new CSVReader(reader);
        String[] headers = recordReader.readNext();
        String[] recordValues;

        while ((recordValues = recordReader.readNext()) != null) {
            Map<String, String> columnValues = new LinkedHashMap<>();
            for (int i = 0; i < headers.length; i++) {
                columnValues.put(headers[i], recordValues[i]);
            }
            records.add(new DataRecord(columnValues));
        }

        return records;

    }
}
