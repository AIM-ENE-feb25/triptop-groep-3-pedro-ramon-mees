package ese.triptop.onderzoeksvraag.adapters;

import ese.triptop.onderzoeksvraag.api.ApiException;
import ese.triptop.onderzoeksvraag.api.ConfigurationManager;
import ese.triptop.onderzoeksvraag.dto.RestaurantDto;
import ese.triptop.onderzoeksvraag.dto.RestaurantSearchRequest;
import ese.triptop.onderzoeksvraag.dto.RestaurantSearchResponse;
import ese.triptop.onderzoeksvraag.util.JsonParser;
import ese.triptop.onderzoeksvraag.util.JsonParser.JsonArray;
import ese.triptop.onderzoeksvraag.util.JsonParser.JsonException;
import ese.triptop.onderzoeksvraag.util.JsonParser.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Adapter for the TripAdvisor API.
 * Implements the TripAdvisorAdapter interface to provide restaurant search functionality.
 */
public class TripAdvisorApiAdapter implements TripAdvisorAdapter {
    
    private final HttpClient httpClient;
    private final ConfigurationManager configManager;
    
    private static final String API_KEY_CONFIG = "tripadvisor.api.key";
    private static final String API_HOST_CONFIG = "tripadvisor.api.host";
    private static final String API_BASE_URL_CONFIG = "tripadvisor.api.baseUrl";
    
    /**
     * Creates a new TripAdvisorApiAdapter with the specified configuration manager.
     * 
     * @param configManager The configuration manager to use
     */
    public TripAdvisorApiAdapter(ConfigurationManager configManager) {
        this.configManager = configManager;
        this.httpClient = HttpClient.newHttpClient();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public RestaurantSearchResponse execute(RestaurantSearchRequest request) throws ApiException {
        try {
            String apiKey = configManager.getConfigValue(API_KEY_CONFIG);
            String apiHost = configManager.getConfigValue(API_HOST_CONFIG);
            String baseUrl = configManager.getConfigValue(API_BASE_URL_CONFIG);
            
            if (apiKey == null || apiHost == null || baseUrl == null) {
                throw new ApiException("Missing TripAdvisor API configuration", "CONFIG_ERROR", 500);
            }
            
            // Build the URL with query parameters
            String url;
            if (request.getLocationId() != null && !request.getLocationId().isEmpty()) {
                // Search by location ID
                url = baseUrl + "/searchRestaurants?locationId=" + request.getLocationId();
            } else {
                // Search by coordinates
                url = baseUrl + "/searchRestaurants?latitude=" + request.getLatitude() +
                        "&longitude=" + request.getLongitude();
            }
            
            // Add optional parameters
            if (request.getLimit() > 0) {
                url += "&limit=" + request.getLimit();
            }
            
            if (request.getLanguage() != null && !request.getLanguage().isEmpty()) {
                url += "&language=" + request.getLanguage();
            }
            
            // Create the HTTP request
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("x-rapidapi-key", apiKey)
                    .header("x-rapidapi-host", apiHost)
                    .GET()
                    .build();
            
            // Execute the request
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            
            // Check for errors
            if (response.statusCode() != 200) {
                throw new ApiException(
                    "TripAdvisor API error: " + response.body(),
                    "API_ERROR",
                    response.statusCode()
                );
            }
            
            // Parse the response
            return parseResponse(response.body());
            
        } catch (IOException | InterruptedException e) {
            throw new ApiException("Error communicating with TripAdvisor API", "NETWORK_ERROR", 500, e);
        } catch (JsonException e) {
            throw new ApiException("Error parsing TripAdvisor API response", "PARSE_ERROR", 500, e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getServiceName() {
        return "TripAdvisor";
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAvailable() {
        try {
            String apiKey = configManager.getConfigValue(API_KEY_CONFIG);
            String apiHost = configManager.getConfigValue(API_HOST_CONFIG);
            String baseUrl = configManager.getConfigValue(API_BASE_URL_CONFIG);
            
            return apiKey != null && apiHost != null && baseUrl != null;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getRecommendationProvider() {
        return "TripAdvisor";
    }
    
    /**
     * Parses the API response into a RestaurantSearchResponse.
     * 
     * @param responseBody The response body
     * @return The restaurant search response
     * @throws JsonException If the response cannot be parsed
     */
    private RestaurantSearchResponse parseResponse(String responseBody) throws JsonException {
        RestaurantSearchResponse response = new RestaurantSearchResponse();
        
        JsonObject jsonResponse = JsonParser.parseObject(responseBody);
        
        // Parse the restaurants
        if (jsonResponse.has("data")) {
            JsonArray dataArray = jsonResponse.getJsonArray("data");
            response.setTotalCount(dataArray.length());
            
            for (int i = 0; i < dataArray.length(); i++) {
                JsonObject restaurantJson = dataArray.getJsonObject(i);
                RestaurantDto restaurant = parseRestaurant(restaurantJson);
                response.addRestaurant(restaurant);
            }
        }
        
        return response;
    }
    
    /**
     * Parses a restaurant JSON object into a RestaurantDto.
     * 
     * @param restaurantJson The restaurant JSON object
     * @return The restaurant DTO
     * @throws JsonException If the restaurant cannot be parsed
     */
    private RestaurantDto parseRestaurant(JsonObject restaurantJson) throws JsonException {
        RestaurantDto restaurant = new RestaurantDto();
        
        // Set basic properties
        if (restaurantJson.has("location_id")) {
            restaurant.setId(restaurantJson.getString("location_id"));
        }
        
        if (restaurantJson.has("name")) {
            restaurant.setName(restaurantJson.getString("name"));
        }
        
        if (restaurantJson.has("description")) {
            restaurant.setDescription(restaurantJson.getString("description"));
        }
        
        // Set location
        if (restaurantJson.has("latitude")) {
            restaurant.setLatitude(restaurantJson.getDouble("latitude"));
        }
        
        if (restaurantJson.has("longitude")) {
            restaurant.setLongitude(restaurantJson.getDouble("longitude"));
        }
        
        if (restaurantJson.has("address")) {
            restaurant.setAddress(restaurantJson.getString("address"));
        }
        
        if (restaurantJson.has("address_obj")) {
            JsonObject addressObj = restaurantJson.getJsonObject("address_obj");
            
            if (addressObj.has("city")) {
                restaurant.setCity(addressObj.getString("city"));
            }
            
            if (addressObj.has("country")) {
                restaurant.setCountry(addressObj.getString("country"));
            }
        }
        
        // Set cuisine and rating
        if (restaurantJson.has("cuisine")) {
            JsonArray cuisineArray = restaurantJson.getJsonArray("cuisine");
            if (cuisineArray.length() > 0) {
                JsonObject cuisineObj = cuisineArray.getJsonObject(0);
                if (cuisineObj.has("name")) {
                    restaurant.setCuisine(cuisineObj.getString("name"));
                }
            }
        }
        
        if (restaurantJson.has("rating")) {
            restaurant.setRating(restaurantJson.getDouble("rating"));
        }
        
        if (restaurantJson.has("price_level")) {
            restaurant.setPriceLevel(restaurantJson.getString("price_level"));
        }
        
        // Set image URL
        if (restaurantJson.has("photo")) {
            JsonObject photoObj = restaurantJson.getJsonObject("photo");
            if (photoObj.has("images")) {
                JsonObject imagesObj = photoObj.getJsonObject("images");
                if (imagesObj.has("large")) {
                    JsonObject largeObj = imagesObj.getJsonObject("large");
                    if (largeObj.has("url")) {
                        restaurant.setImageUrl(largeObj.getString("url"));
                    }
                }
            }
        }
        
        return restaurant;
    }
}
