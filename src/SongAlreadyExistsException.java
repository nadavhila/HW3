/**
 * a class that extend RuntimeException
 */
public class SongAlreadyExistsException extends RuntimeException{
    /**
     * regular constructor of exception
     */
    public SongAlreadyExistsException() {
    }

    /**
     * constructor of exception
     * @param message a string that describe the exception
     */
    public SongAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * constructor of exception
     * @param message a string that describe the exception
     * @param cause initialize the exception with another exception
     */
    public SongAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}