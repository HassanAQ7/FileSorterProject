package ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.DataRecord;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import exceptions.FileSkipException;

// Represents a DataParser that can get records from a given path
public class DataParser {
    private String path;
    private boolean skip;

    // EFFECTS: Constructs a DataParser with provided path
    // and whether it must skip lines or not
    public DataParser(String path, boolean skip) {
        this.path = path;
        this.skip = skip;
    }

    // EFFECTS: Parses DataRecords and fields from CSV File and returns it
    // Throws IOException if file cannot be found
    // or CsvException if other issues with reading file arises
    public List<DataRecord> parseRecords(int skipLines) throws IOException, CsvException, FileSkipException {
        if (!skip && skipLines > 0) {
            throw new FileSkipException();
        }
        
        List<DataRecord> records = new ArrayList<>();
        FileReader reader = new FileReader(path);

        CSVReader recordReader;
        if (skip) {
            recordReader = new CSVReaderBuilder(reader).withSkipLines(skipLines).build();
        } else {
            recordReader = new CSVReader(reader);
        }
        String[] headers = recordReader.readNext();
        String[] recordValues;

        while ((recordValues = recordReader.readNext()) != null) {
            LinkedHashMap<String, String> columnValues = new LinkedHashMap<>();
            for (int i = 0; i < headers.length; i++) {
                columnValues.put(headers[i], recordValues[i]);
            }
            records.add(new DataRecord(columnValues));
        }

        recordReader.close();
        return records;

    }
}
