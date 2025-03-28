package ese.triptop.features.Patterns.facade;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import kong.unirest.core.json.JSONObject;

public class TravelApiServiceFacadeImpl implements TravelApiServiceFacade {

    private static final String IDENTITY_BASE_URL = "https://triptop-identity.wiremockapi.cloud";
    private static final String FLIGHT_OFFERS_URL = "https://kk039.wiremockapi.cloud/shopping/flight-offers";
    private static final String BOOKING_BASE_URL = "https://booking-com15.p.rapidapi.com/api/v1/hotels";
    private static final String TRIPADVISOR_BASE_URL = "https://tripadvisor16.p.rapidapi.com/api/v1/restaurant";

    private static final String RAPIDAPI_KEY = "01fcc8ef7dmsh9371396917f2b4fp187e1ejsn778fecdf4de9"; // Example key from ImplementationPedro

    @Override
    public JsonNode findFlights(String origin, String destination, String departureDate, int adults) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(FLIGHT_OFFERS_URL)
                    .queryString("originLocationCode", origin)
                    .queryString("destinationLocationCode", destination)
                    .queryString("departureDate", departureDate)
                    .queryString("adults", adults)
                    .asJson();

            if (response.getStatus() == 200) {
                System.out.println("[Facade] Flight search successful.");
                return response.getBody();
            } else {
                System.err.println("[Facade] Flight search failed: " + response.getStatus() + " - " + response.getBody());
                return null;
            }
        } catch (UnirestException e) {
            System.err.println("[Facade] Error during flight search: " + e.getMessage());
            return null;
        }
    }

    @Override
    public JsonNode findHotels(double latitude, double longitude, String arrivalDate, String departureDate) {
         // Parameters based on ImplementationPedro example
        try {
            HttpResponse<JsonNode> response = Unirest.get(BOOKING_BASE_URL + "/searchHotelsByCoordinates")
                    .header("x-rapidapi-key", RAPIDAPI_KEY)
                    .header("x-rapidapi-host", "booking-com15.p.rapidapi.com")
                    .queryString("latitude", latitude)
                    .queryString("longitude", longitude)
                    .queryString("arrival_date", arrivalDate)
                    .queryString("departure_date", departureDate)
                    .queryString("adults", 1) // Hardcoded based on example
                    .queryString("room_qty", 1) // Hardcoded based on example
                    .queryString("units", "metric") // Hardcoded based on example
                    .queryString("page_number", 1) // Hardcoded based on example
                    .queryString("languagecode", "en-us") // Hardcoded based on example
                    .queryString("currency_code", "EUR") // Hardcoded based on example
                    .asJson(); // Changed to asJson for consistency

            if (response.getStatus() == 200) {
                System.out.println("[Facade] Hotel search successful.");
                return response.getBody();
            } else {
                System.err.println("[Facade] Hotel search failed: " + response.getStatus() + " - " + response.getBody());
                return null;
            }
        } catch (UnirestException e) {
            System.err.println("[Facade] Error during hotel search: " + e.getMessage());
            return null;
        }
    }

    @Override
    public JsonNode findRestaurants(String locationId) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(TRIPADVISOR_BASE_URL + "/searchRestaurants")
                    .header("x-rapidapi-key", RAPIDAPI_KEY)
                    .header("x-rapidapi-host", "tripadvisor16.p.rapidapi.com")
                    .queryString("locationId", locationId)
                    .asJson(); // Changed to asJson for consistency

            if (response.getStatus() == 200) {
                System.out.println("[Facade] Restaurant search successful.");
                return response.getBody();
            } else {
                System.err.println("[Facade] Restaurant search failed: " + response.getStatus() + " - " + response.getBody());
                return null;
            }
        } catch (UnirestException e) {
            System.err.println("[Facade] Error during restaurant search: " + e.getMessage());
            return null;
        }
    }
}
