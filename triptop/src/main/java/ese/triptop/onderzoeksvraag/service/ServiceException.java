package ese.triptop.onderzoeksvraag.service;

/**
 * Exception thrown when a service operation fails.
 * Contains additional information about the error such as error code.
 */
public class ServiceException extends Exception {
    
    private final String errorCode;
    
    /**
     * Creates a new ServiceException with the specified message, error code, and cause.
     * 
     * @param message The error message
     * @param errorCode The error code
     * @param cause The cause of the exception
     */
    public ServiceException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    /**
     * Creates a new ServiceException with the specified message and error code.
     * 
     * @param message The error message
     * @param errorCode The error code
     */
    public ServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    /**
     * Gets the error code.
     * 
     * @return The error code
     */
    public String getErrorCode() {
        return errorCode;
    }
}
