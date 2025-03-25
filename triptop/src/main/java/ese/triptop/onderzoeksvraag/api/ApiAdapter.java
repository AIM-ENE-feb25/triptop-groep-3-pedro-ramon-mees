package ese.triptop.onderzoeksvraag.api;

/**
 * Generic interface for API adapters.
 * Provides a consistent way to interact with external APIs.
 * 
 * @param <T> The request type
 * @param <R> The response type
 */
public interface ApiAdapter<T, R> {
    
    /**
     * Executes an API call with the provided request data
     * 
     * @param request The request data
     * @return The response data
     * @throws ApiException If an error occurs during the API call
     */
    R execute(T request) throws ApiException;
    
    /**
     * Gets the name of the API service
     * 
     * @return The service name
     */
    String getServiceName();
    
    /**
     * Checks if the adapter is available/healthy
     * 
     * @return true if the adapter is available, false otherwise
     */
    boolean isAvailable();
}
