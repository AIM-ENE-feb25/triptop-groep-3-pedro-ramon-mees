package ese.triptop.features.Patterns.facade;

import kong.unirest.core.JsonNode;

// Define simplified operations for interacting with various travel-related APIs
public interface TravelApiServiceFacade {
    JsonNode findFlights(String origin, String destination, String departureDate, int adults);
    JsonNode findHotels(double latitude, double longitude, String arrivalDate, String departureDate);
    JsonNode findRestaurants(String locationId);
}
