package ui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.opencsv.exceptions.CsvException;

import exceptions.FileSkipException;
import model.DataRecord;

// Represents a CsvFileChooser that can choose a variety of files
public class CsvFileChooser extends JFileChooser {
    private JFrame parent;

    // EFFECTS: Constructs a CsvFileChooser
    public CsvFileChooser(JFrame parent) {
        super();
        this.parent = parent;
        setVisible(true);
    }

    // EFFECTS: Opens the file, reads it through DataParser class and returns the
    // list of data records
    public List<DataRecord> openFile() throws IOException, CsvException, FileSkipException {
        int returnVal = this.showOpenDialog(parent);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File csvFile = getSelectedFile();
            String path = csvFile.getPath();
            int option = JOptionPane.showConfirmDialog(parent, "Skip lines?");
            if (option == 0) {
                String skipLines = JOptionPane.showInputDialog("How many lines to skip by?");
                int skip = Integer.parseInt(skipLines);
                if (skip < 0) {
                    throw new IllegalArgumentException();

                }

                return new DataParser(path, true).parseRecords(skip);
            } else if (option == 1) {
                return new DataParser(path, false).parseRecords(0);
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

}
