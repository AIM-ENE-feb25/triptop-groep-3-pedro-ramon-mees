# Interface Definitions

## 1. ApiAdapter Interface

```java
public interface ApiAdapter<T, R> {
    /**
     * Executes an API call with the provided request data
     * @param request The request data
     * @return The response data
     * @throws ApiException If an error occurs during the API call
     */
    R execute(T request) throws ApiException;
    
    /**
     * Gets the name of the API service
     * @return The service name
     */
    String getServiceName();
    
    /**
     * Checks if the adapter is available/healthy
     * @return true if the adapter is available, false otherwise
     */
    boolean isAvailable();
}
```

## 2. ApiGateway Interface

```java
public interface ApiGateway {
    /**
     * Executes an API call using the appropriate adapter
     * @param adapterType The type of adapter to use
     * @param request The request data
     * @return The response data
     * @throws ApiException If an error occurs during the API call
     */
    <T, R> R executeApiCall(Class<? extends ApiAdapter<T, R>> adapterType, T request) throws ApiException;
    
    /**
     * Gets an adapter instance by type
     * @param adapterType The type of adapter to get
     * @return The adapter instance
     * @throws ApiException If the adapter is not found
     */
    <T, R> ApiAdapter<T, R> getAdapter(Class<? extends ApiAdapter<T, R>> adapterType) throws ApiException;
}
```

## 3. ConfigurationManager Interface

```java
public interface ConfigurationManager {
    /**
     * Gets a configuration value by key
     * @param key The configuration key
     * @return The configuration value
     */
    String getConfigValue(String key);
    
    /**
     * Gets a configuration value by key with a default value
     * @param key The configuration key
     * @param defaultValue The default value to return if the key is not found
     * @return The configuration value or the default value
     */
    String getConfigValue(String key, String defaultValue);
    
    /**
     * Reloads configuration from the source
     */
    void reloadConfiguration();
}
```

## 4. TravelService Interface

```java
public interface TravelService {
    /**
     * Gets hotel booking options based on search criteria
     * @param searchCriteria The search criteria
     * @return A list of hotel booking options
     * @throws ServiceException If an error occurs during the operation
     */
    List<HotelBookingOption> getHotelBookingOptions(HotelSearchCriteria searchCriteria) throws ServiceException;
    
    /**
     * Gets restaurant recommendations based on location
     * @param location The location to search for restaurants
     * @return A list of restaurant recommendations
     * @throws ServiceException If an error occurs during the operation
     */
    List<RestaurantRecommendation> getRestaurantRecommendations(Location location) throws ServiceException;
    
    /**
     * Gets flight offers based on search criteria
     * @param searchCriteria The search criteria
     * @return A list of flight offers
     * @throws ServiceException If an error occurs during the operation
     */
    List<FlightOffer> getFlightOffers(FlightSearchCriteria searchCriteria) throws ServiceException;
}
```

## 5. BookingAdapter Interface

```java
public interface BookingAdapter extends ApiAdapter<HotelSearchRequest, HotelSearchResponse> {
    /**
     * Gets the supported booking provider
     * @return The booking provider name
     */
    String getBookingProvider();
}
```

## 6. TripAdvisorAdapter Interface

```java
public interface TripAdvisorAdapter extends ApiAdapter<RestaurantSearchRequest, RestaurantSearchResponse> {
    /**
     * Gets the supported recommendation provider
     * @return The recommendation provider name
     */
    String getRecommendationProvider();
}
```

## 7. FlightAdapter Interface

```java
public interface FlightAdapter extends ApiAdapter<FlightSearchRequest, FlightSearchResponse> {
    /**
     * Gets the supported flight provider
     * @return The flight provider name
     */
    String getFlightProvider();
}
```

## 8. ApiException Class

```java
public class ApiException extends Exception {
    private final String errorCode;
    private final int statusCode;
    
    public ApiException(String message, String errorCode, int statusCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
}
```

## 9. ServiceException Class

```java
public class ServiceException extends Exception {
    private final String errorCode;
    
    public ServiceException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
}
