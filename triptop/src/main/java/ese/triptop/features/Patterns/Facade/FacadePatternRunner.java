package ese.triptop.features.Patterns.facade;

import kong.unirest.core.JsonNode;
import org.springframework.stereotype.Component;

@Component 
public class FacadePatternRunner {

    private final TravelApiServiceFacade travelApi;

    public FacadePatternRunner() {
        this.travelApi = new TravelApiServiceFacadeImpl(); // Using the default implementation
    }

    public FacadePatternRunner(TravelApiServiceFacade travelApi) {
        this.travelApi = travelApi;
    }

    public void runDemo() {
        System.out.println("\n--- Running Facade Pattern Demonstration ---");

        System.out.println("\n[Demo] Searching for flights...");
        JsonNode flights = travelApi.findFlights("AMS", "LHR", "2025-06-15", 2); // Example values
        if (flights != null) {
            System.out.println("[Demo] Flight results: " + flights.toString().substring(0, Math.min(flights.toString().length(), 200)) + "..."); // Print snippet
        } else {
            System.out.println("[Demo] Flight search failed or returned no results.");
        }

        System.out.println("\n[Demo] Searching for hotels...");
        JsonNode hotels = travelApi.findHotels(19.242327, 72.858419, "2025-03-31", "2025-04-15");
        if (hotels != null) {
            System.out.println("[Demo] Hotel results: " + hotels.toString().substring(0, Math.min(hotels.toString().length(), 200)) + "..."); // Print snippet
        } else {
            System.out.println("[Demo] Hotel search failed or returned no results.");
        }

        System.out.println("\n[Demo] Searching for restaurants...");
        JsonNode restaurants = travelApi.findRestaurants("304554");
        if (restaurants != null) {
            System.out.println("[Demo] Restaurant results: " + restaurants.toString().substring(0, Math.min(restaurants.toString().length(), 200)) + "..."); // Print snippet
        } else {
            System.out.println("[Demo] Restaurant search failed or returned no results.");
        }
        System.out.println("\n--- Facade Pattern Demonstration Finished ---");
    }
}
