package ese.triptop.onderzoeksvraag.api;

/**
 * Exception thrown when an API call fails.
 * Contains additional information about the error such as error code and status code.
 */
public class ApiException extends Exception {
    
    private final String errorCode;
    private final int statusCode;
    
    /**
     * Creates a new ApiException with the specified message, error code, status code, and cause.
     * 
     * @param message The error message
     * @param errorCode The error code from the API
     * @param statusCode The HTTP status code
     * @param cause The cause of the exception
     */
    public ApiException(String message, String errorCode, int statusCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
    
    /**
     * Creates a new ApiException with the specified message, error code, and status code.
     * 
     * @param message The error message
     * @param errorCode The error code from the API
     * @param statusCode The HTTP status code
     */
    public ApiException(String message, String errorCode, int statusCode) {
        super(message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
    
    /**
     * Gets the error code from the API.
     * 
     * @return The error code
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * Gets the HTTP status code.
     * 
     * @return The status code
     */
    public int getStatusCode() {
        return statusCode;
    }
}
