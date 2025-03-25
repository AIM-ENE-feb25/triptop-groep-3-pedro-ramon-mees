package ese.triptop.onderzoeksvraag.adapters;

import ese.triptop.onderzoeksvraag.api.ApiException;
import ese.triptop.onderzoeksvraag.api.ConfigurationManager;
import ese.triptop.onderzoeksvraag.dto.HotelDto;
import ese.triptop.onderzoeksvraag.dto.HotelSearchRequest;
import ese.triptop.onderzoeksvraag.dto.HotelSearchResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import ese.triptop.onderzoeksvraag.util.JsonParser;
import ese.triptop.onderzoeksvraag.util.JsonParser.JsonArray;
import ese.triptop.onderzoeksvraag.util.JsonParser.JsonException;
import ese.triptop.onderzoeksvraag.util.JsonParser.JsonObject;

/**
 * Adapter for the Booking.com API.
 * Implements the BookingAdapter interface to provide hotel search functionality.
 */
public class BookingComAdapter implements BookingAdapter {
    
    private final HttpClient httpClient;
    private final ConfigurationManager configManager;
    
    private static final String API_KEY_CONFIG = "booking.api.key";
    private static final String API_HOST_CONFIG = "booking.api.host";
    private static final String API_BASE_URL_CONFIG = "booking.api.baseUrl";
    
    /**
     * Creates a new BookingComAdapter with the specified configuration manager.
     * 
     * @param configManager The configuration manager to use
     */
    public BookingComAdapter(ConfigurationManager configManager) {
        this.configManager = configManager;
        this.httpClient = HttpClient.newHttpClient();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public HotelSearchResponse execute(HotelSearchRequest request) throws ApiException {
        try {
            String apiKey = configManager.getConfigValue(API_KEY_CONFIG);
            String apiHost = configManager.getConfigValue(API_HOST_CONFIG);
            String baseUrl = configManager.getConfigValue(API_BASE_URL_CONFIG);
            
            if (apiKey == null || apiHost == null || baseUrl == null) {
                throw new ApiException("Missing Booking.com API configuration", "CONFIG_ERROR", 500);
            }
            
            // Build the URL with query parameters
            String url = baseUrl + "/searchHotelsByCoordinates" +
                    "?latitude=" + request.getLatitude() +
                    "&longitude=" + request.getLongitude() +
                    "&arrival_date=" + request.getArrivalDate() +
                    "&departure_date=" + request.getDepartureDate() +
                    "&adults=" + request.getAdults() +
                    "&room_qty=" + request.getRooms() +
                    "&currency_code=" + request.getCurrency();
            
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
                    "Booking.com API error: " + response.body(),
                    "API_ERROR",
                    response.statusCode()
                );
            }
            
            // Parse the response
            return parseResponse(response.body());
            
        } catch (IOException | InterruptedException e) {
            throw new ApiException("Error communicating with Booking.com API", "NETWORK_ERROR", 500, e);
        } catch (JsonException e) {
            throw new ApiException("Error parsing Booking.com API response", "PARSE_ERROR", 500, e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getServiceName() {
        return "Booking.com";
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
    public String getBookingProvider() {
        return "Booking.com";
    }
    
    /**
     * Parses the API response into a HotelSearchResponse.
     * 
     * @param responseBody The response body
     * @return The hotel search response
     * @throws JsonException If the response cannot be parsed
     */
    private HotelSearchResponse parseResponse(String responseBody) throws JsonException {
        HotelSearchResponse response = new HotelSearchResponse();
        
        JsonObject jsonResponse = JsonParser.parseObject(responseBody);
        
        // Set the currency
        if (jsonResponse.has("currency")) {
            response.setCurrency(jsonResponse.getString("currency"));
        }
        
        // Parse the hotels
        if (jsonResponse.has("results")) {
            JsonArray resultsArray = jsonResponse.getJsonArray("results");
            response.setTotalCount(resultsArray.length());
            
            for (int i = 0; i < resultsArray.length(); i++) {
                JsonObject hotelJson = resultsArray.getJsonObject(i);
                HotelDto hotel = parseHotel(hotelJson);
                response.addHotel(hotel);
            }
        }
        
        return response;
    }
    
    /**
     * Parses a hotel JSON object into a HotelDto.
     * 
     * @param hotelJson The hotel JSON object
     * @return The hotel DTO
     * @throws JsonException If the hotel cannot be parsed
     */
    private HotelDto parseHotel(JsonObject hotelJson) throws JsonException {
        HotelDto hotel = new HotelDto();
        
        // Set basic properties
        if (hotelJson.has("hotel_id")) {
            hotel.setId(hotelJson.getString("hotel_id"));
        }
        
        if (hotelJson.has("hotel_name")) {
            hotel.setName(hotelJson.getString("hotel_name"));
        }
        
        if (hotelJson.has("hotel_description")) {
            hotel.setDescription(hotelJson.getString("hotel_description"));
        }
        
        // Set location
        if (hotelJson.has("latitude")) {
            hotel.setLatitude(hotelJson.getDouble("latitude"));
        }
        
        if (hotelJson.has("longitude")) {
            hotel.setLongitude(hotelJson.getDouble("longitude"));
        }
        
        if (hotelJson.has("city")) {
            hotel.setCity(hotelJson.getString("city"));
        }
        
        if (hotelJson.has("country")) {
            hotel.setCountry(hotelJson.getString("country"));
        }
        
        if (hotelJson.has("address")) {
            hotel.setAddress(hotelJson.getString("address"));
        }
        
        // Set price and rating
        if (hotelJson.has("min_total_price")) {
            hotel.setPrice(hotelJson.getDouble("min_total_price"));
        }
        
        if (hotelJson.has("currency")) {
            hotel.setCurrency(hotelJson.getString("currency"));
        }
        
        if (hotelJson.has("review_score")) {
            hotel.setRating(hotelJson.getDouble("review_score"));
        }
        
        // Set image URL
        if (hotelJson.has("main_photo_url")) {
            hotel.setImageUrl(hotelJson.getString("main_photo_url"));
        }
        
        // Set amenities
        if (hotelJson.has("facilities")) {
            JsonArray facilitiesArray = hotelJson.getJsonArray("facilities");
            List<String> amenities = new ArrayList<>();
            
            for (int i = 0; i < facilitiesArray.length(); i++) {
                amenities.add(facilitiesArray.getString(i));
            }
            
            hotel.setAmenities(amenities);
        }
        
        return hotel;
    }
}
