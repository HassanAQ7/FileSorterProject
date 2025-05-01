package exceptions;

// represents an Exception that is thrown when a value from a
// dataset cannot be found
public class ValueNotFoundException extends Exception{

    public ValueNotFoundException() {
        super();
    }

    public ValueNotFoundException(String msg) {
        super(msg);
    }

}
