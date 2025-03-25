package ese.triptop.onderzoeksvraag.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the ApiGateway interface.
 * Manages a collection of API adapters and routes requests to the appropriate adapter.
 */
public class DefaultApiGateway implements ApiGateway {
    
    private final Map<Class<?>, ApiAdapter<?, ?>> adapters;
    private final ConfigurationManager configManager;
    
    /**
     * Creates a new DefaultApiGateway with the specified configuration manager.
     * 
     * @param configManager The configuration manager to use
     */
    public DefaultApiGateway(ConfigurationManager configManager) {
        this.configManager = configManager;
        this.adapters = new HashMap<>();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T, R> R executeApiCall(Class<? extends ApiAdapter<T, R>> adapterType, T request) throws ApiException {
        ApiAdapter<T, R> adapter = getAdapter(adapterType);
        
        try {
            return adapter.execute(request);
        } catch (ApiException e) {
            // Log the error
            System.err.println("API call failed: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            // Wrap unexpected exceptions
            throw new ApiException(
                "Unexpected error during API call to " + adapter.getServiceName(),
                "UNEXPECTED_ERROR",
                500,
                e
            );
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T, R> ApiAdapter<T, R> getAdapter(Class<? extends ApiAdapter<T, R>> adapterType) throws ApiException {
        ApiAdapter<?, ?> adapter = adapters.get(adapterType);
        
        if (adapter == null) {
            throw new ApiException(
                "No adapter registered for type: " + adapterType.getName(),
                "ADAPTER_NOT_FOUND",
                500
            );
        }
        
        if (!adapter.isAvailable()) {
            throw new ApiException(
                "Adapter is not available: " + adapter.getServiceName(),
                "ADAPTER_UNAVAILABLE",
                503
            );
        }
        
        return (ApiAdapter<T, R>) adapter;
    }
    
    /**
     * Registers an adapter with the gateway.
     * 
     * @param adapterType The adapter type/interface
     * @param adapter The adapter instance
     */
    public void registerAdapter(Class<?> adapterType, ApiAdapter<?, ?> adapter) {
        adapters.put(adapterType, adapter);
    }
}
