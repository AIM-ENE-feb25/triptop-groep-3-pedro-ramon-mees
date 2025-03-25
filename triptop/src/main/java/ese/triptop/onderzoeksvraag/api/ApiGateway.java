package ese.triptop.onderzoeksvraag.api;

/**
 * Interface for the API Gateway.
 * Provides a centralized point for executing API calls and managing adapters.
 */
public interface ApiGateway {
    
    /**
     * Executes an API call using the appropriate adapter.
     * 
     * @param adapterType The type of adapter to use
     * @param request The request data
     * @return The response data
     * @throws ApiException If an error occurs during the API call
     */
    <T, R> R executeApiCall(Class<? extends ApiAdapter<T, R>> adapterType, T request) throws ApiException;
    
    /**
     * Gets an adapter instance by type.
     * 
     * @param adapterType The type of adapter to get
     * @return The adapter instance
     * @throws ApiException If the adapter is not found
     */
    <T, R> ApiAdapter<T, R> getAdapter(Class<? extends ApiAdapter<T, R>> adapterType) throws ApiException;
}
