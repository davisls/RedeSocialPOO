package exceptions;

public class InvalidFormatPasswordException extends Exception {
    public InvalidFormatPasswordException(String errorMessage) {
        super(errorMessage);
    }
}
