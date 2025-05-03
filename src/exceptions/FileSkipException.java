package exceptions;

// Represents an exception that should be thrown when
// told to skip but there are no specified lines to skip
public class FileSkipException extends Exception {

    public FileSkipException() {
        super();
    }

    public FileSkipException(String msg) {
        super(msg);
    }

}
