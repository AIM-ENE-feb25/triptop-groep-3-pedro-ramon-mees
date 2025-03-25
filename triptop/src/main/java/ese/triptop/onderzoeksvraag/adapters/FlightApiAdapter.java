package ese.triptop.onderzoeksvraag.adapters;

import ese.triptop.onderzoeksvraag.api.ApiException;
import ese.triptop.onderzoeksvraag.api.ConfigurationManager;
import ese.triptop.onderzoeksvraag.dto.FlightOfferDto;
import ese.triptop.onderzoeksvraag.dto.FlightSearchRequest;
import ese.triptop.onderzoeksvraag.dto.FlightSearchResponse;
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
 * Adapter for the Flight API.
 * Implements the FlightAdapter interface to provide flight search functionality.
 */
public class FlightApiAdapter implements FlightAdapter {
    
    private final HttpClient httpClient;
    private final ConfigurationManager configManager;
    
    private static final String API_KEY_CONFIG = "flight.api.key";
    private static final String API_HOST_CONFIG = "flight.api.host";
    private static final String API_BASE_URL_CONFIG = "flight.api.baseUrl";
    
    /**
     * Creates a new FlightApiAdapter with the specified configuration manager.
     * 
     * @param configManager The configuration manager to use
     */
    public FlightApiAdapter(ConfigurationManager configManager) {
        this.configManager = configManager;
        this.httpClient = HttpClient.newHttpClient();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public FlightSearchResponse execute(FlightSearchRequest request) throws ApiException {
        try {
            String apiKey = configManager.getConfigValue(API_KEY_CONFIG);
            String apiHost = configManager.getConfigValue(API_HOST_CONFIG);
            String baseUrl = configManager.getConfigValue(API_BASE_URL_CONFIG);
            
            if (apiKey == null || apiHost == null || baseUrl == null) {
                throw new ApiException("Missing Flight API configuration", "CONFIG_ERROR", 500);
            }
            
            // Build the URL with query parameters
            String url = baseUrl + "/flight-offers" +
                    "?originLocationCode=" + request.getOriginLocationCode() +
                    "&destinationLocationCode=" + request.getDestinationLocationCode() +
                    "&departureDate=" + request.getDepartureDate() +
                    "&adults=" + request.getAdults();
            
            // Add optional parameters
            if (request.getReturnDate() != null && !request.getReturnDate().isEmpty()) {
                url += "&returnDate=" + request.getReturnDate();
            }
            
            if (request.getCurrency() != null && !request.getCurrency().isEmpty()) {
                url += "&currencyCode=" + request.getCurrency();
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
                    "Flight API error: " + response.body(),
                    "API_ERROR",
                    response.statusCode()
                );
            }
            
            // Parse the response
            return parseResponse(response.body());
            
        } catch (IOException | InterruptedException e) {
            throw new ApiException("Error communicating with Flight API", "NETWORK_ERROR", 500, e);
        } catch (JsonException e) {
            throw new ApiException("Error parsing Flight API response", "PARSE_ERROR", 500, e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getServiceName() {
        return "Flight API";
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
    public String getFlightProvider() {
        return "Flight API";
    }
    
    /**
     * Parses the API response into a FlightSearchResponse.
     * 
     * @param responseBody The response body
     * @return The flight search response
     * @throws JsonException If the response cannot be parsed
     */
    private FlightSearchResponse parseResponse(String responseBody) throws JsonException {
        FlightSearchResponse response = new FlightSearchResponse();
        
        JsonObject jsonResponse = JsonParser.parseObject(responseBody);
        
        // Set the currency
        if (jsonResponse.has("meta") && jsonResponse.getJsonObject("meta").has("currency")) {
            response.setCurrency(jsonResponse.getJsonObject("meta").getString("currency"));
        }
        
        // Parse the flight offers
        if (jsonResponse.has("data")) {
            JsonArray dataArray = jsonResponse.getJsonArray("data");
            response.setTotalCount(dataArray.length());
            
            for (int i = 0; i < dataArray.length(); i++) {
                JsonObject offerJson = dataArray.getJsonObject(i);
                FlightOfferDto offer = parseFlightOffer(offerJson);
                response.addFlightOffer(offer);
            }
        }
        
        return response;
    }
    
    /**
     * Parses a flight offer JSON object into a FlightOfferDto.
     * 
     * @param offerJson The flight offer JSON object
     * @return The flight offer DTO
     * @throws JsonException If the flight offer cannot be parsed
     */
    private FlightOfferDto parseFlightOffer(JsonObject offerJson) throws JsonException {
        FlightOfferDto offer = new FlightOfferDto();
        
        // Set basic properties
        if (offerJson.has("id")) {
            offer.setId(offerJson.getString("id"));
        }
        
        // Set price
        if (offerJson.has("price") && offerJson.getJsonObject("price").has("total")) {
            String priceStr = offerJson.getJsonObject("price").getString("total");
            try {
                offer.setPrice(Double.parseDouble(priceStr));
            } catch (NumberFormatException e) {
                // Ignore parsing errors
            }
        }
        
        if (offerJson.has("price") && offerJson.getJsonObject("price").has("currency")) {
            offer.setCurrency(offerJson.getJsonObject("price").getString("currency"));
        }
        
        // Parse itineraries
        if (offerJson.has("itineraries") && offerJson.getJsonArray("itineraries").length() > 0) {
            JsonObject itinerary = offerJson.getJsonArray("itineraries").getJsonObject(0);
            
            // Set duration
            if (itinerary.has("duration")) {
                offer.setDuration(itinerary.getString("duration"));
            }
            
            // Parse segments
            if (itinerary.has("segments") && itinerary.getJsonArray("segments").length() > 0) {
                JsonArray segments = itinerary.getJsonArray("segments");
                
                // Set number of stops
                offer.setStops(segments.length() - 1);
                
                // Get first segment for departure info
                JsonObject firstSegment = segments.getJsonObject(0);
                if (firstSegment.has("departure") && firstSegment.getJsonObject("departure").has("iataCode")) {
                    offer.setDepartureAirport(firstSegment.getJsonObject("departure").getString("iataCode"));
                }
                
                if (firstSegment.has("departure") && firstSegment.getJsonObject("departure").has("at")) {
                    offer.setDepartureTime(firstSegment.getJsonObject("departure").getString("at"));
                }
                
                // Get last segment for arrival info
                JsonObject lastSegment = segments.getJsonObject(segments.length() - 1);
                if (lastSegment.has("arrival") && lastSegment.getJsonObject("arrival").has("iataCode")) {
                    offer.setArrivalAirport(lastSegment.getJsonObject("arrival").getString("iataCode"));
                }
                
                if (lastSegment.has("arrival") && lastSegment.getJsonObject("arrival").has("at")) {
                    offer.setArrivalTime(lastSegment.getJsonObject("arrival").getString("at"));
                }
                
                // Set airline from first segment
                if (firstSegment.has("carrierCode")) {
                    offer.setAirline(firstSegment.getString("carrierCode"));
                }
            }
        }
        
        return offer;
    }
}
