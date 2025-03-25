package ese.triptop.onderzoeksvraag;

import ese.triptop.onderzoeksvraag.adapters.BookingAdapter;
import ese.triptop.onderzoeksvraag.adapters.BookingComAdapter;
import ese.triptop.onderzoeksvraag.adapters.FlightAdapter;
import ese.triptop.onderzoeksvraag.adapters.FlightApiAdapter;
import ese.triptop.onderzoeksvraag.adapters.TripAdvisorAdapter;
import ese.triptop.onderzoeksvraag.adapters.TripAdvisorApiAdapter;
import ese.triptop.onderzoeksvraag.api.ApiException;
import ese.triptop.onderzoeksvraag.api.ConfigurationManager;
import ese.triptop.onderzoeksvraag.api.DefaultApiGateway;
import ese.triptop.onderzoeksvraag.domain.FlightOffer;
import ese.triptop.onderzoeksvraag.domain.FlightSearchCriteria;
import ese.triptop.onderzoeksvraag.domain.HotelBookingOption;
import ese.triptop.onderzoeksvraag.domain.HotelSearchCriteria;
import ese.triptop.onderzoeksvraag.domain.Location;
import ese.triptop.onderzoeksvraag.domain.RestaurantRecommendation;
import ese.triptop.onderzoeksvraag.service.ServiceException;
import ese.triptop.onderzoeksvraag.service.TravelService;
import ese.triptop.onderzoeksvraag.service.TravelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Runner class for demonstrating the API architecture.
 * This class sets up the components and runs example scenarios.
 */
@Component
public class ApiArchitectureRunner {
    
    private final TravelService travelService;
    
    /**
     * Creates a new ApiArchitectureRunner with the specified travel service.
     * 
     * @param travelService The travel service
     */
    @Autowired
    public ApiArchitectureRunner(TravelService travelService) {
        this.travelService = travelService;
    }

    /**
     * Runs the demonstration of the API architecture.
     * This method runs example scenarios using the injected components.
     */
    public void run() {
        System.out.println("\n\n==== API Architecture Demonstration ====\n");
        
        try {
            System.out.println("Spring-based configuration and dependency injection is being used.");
            
            // Run the hotel search scenario
            runHotelSearchScenario(travelService);
            
            // Run the restaurant recommendation scenario
            runRestaurantRecommendationScenario(travelService);
            
            // Run the flight search scenario
            runFlightSearchScenario(travelService);
            
            // Demonstrate API change handling
            demonstrateApiChangeHandling();
            
            System.out.println("\n==== API Architecture Demonstration Completed ====\n");
        } catch (Exception e) {
            System.err.println("Error during demonstration: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    /**
     * Runs the hotel search scenario.
     * 
     * @param travelService The travel service
     */
    private void runHotelSearchScenario(TravelService travelService) {
        System.out.println("\n--- Hotel Search Scenario ---");
        
        try {
            // Create search criteria
            Location location = new Location(19.24232736426361, 72.85841985686734, "Mumbai", "India");
            LocalDate checkInDate = LocalDate.now().plusDays(30);
            LocalDate checkOutDate = checkInDate.plusDays(7);
            
            HotelSearchCriteria criteria = new HotelSearchCriteria(location, checkInDate, checkOutDate);
            criteria.setAdults(2);
            criteria.setRooms(1);
            
            System.out.println("Searching for hotels in " + location.getCity() + " from " + 
                    checkInDate + " to " + checkOutDate + " for " + criteria.getAdults() + 
                    " adults in " + criteria.getRooms() + " room(s)...");
            
            // In a real implementation, this would call the actual API
            // For demonstration, we'll simulate the response
            List<HotelBookingOption> hotels = travelService.getHotelBookingOptions(criteria);
            
            // Print the results
            System.out.println("Found " + hotels.size() + " hotels:");
            for (HotelBookingOption hotel : hotels) {
                System.out.println("- " + hotel.getName() + " (" + hotel.getRating() + " stars) - " + 
                        hotel.getPrice() + " " + hotel.getCurrency());
            }
        } catch (ServiceException e) {
            System.err.println("Error during hotel search: " + e.getMessage());
        }
    }
    
    /**
     * Runs the restaurant recommendation scenario.
     * 
     * @param travelService The travel service
     */
    private void runRestaurantRecommendationScenario(TravelService travelService) {
        System.out.println("\n--- Restaurant Recommendation Scenario ---");
        
        try {
            // Create location
            Location location = new Location(19.24232736426361, 72.85841985686734, "Mumbai", "India");
            
            System.out.println("Searching for restaurants in " + location.getCity() + "...");
            
            // In a real implementation, this would call the actual API
            // For demonstration, we'll simulate the response
            List<RestaurantRecommendation> restaurants = travelService.getRestaurantRecommendations(location);
            
            // Print the results
            System.out.println("Found " + restaurants.size() + " restaurants:");
            for (RestaurantRecommendation restaurant : restaurants) {
                System.out.println("- " + restaurant.getName() + " (" + restaurant.getCuisine() + ") - " + 
                        restaurant.getRating() + " stars, " + restaurant.getPriceLevel());
            }
        } catch (ServiceException e) {
            System.err.println("Error during restaurant search: " + e.getMessage());
        }
    }
    
    /**
     * Runs the flight search scenario.
     * 
     * @param travelService The travel service
     */
    private void runFlightSearchScenario(TravelService travelService) {
        System.out.println("\n--- Flight Search Scenario ---");
        
        try {
            // Create search criteria
            FlightSearchCriteria criteria = new FlightSearchCriteria("AMS", "JFK", LocalDate.now().plusDays(30));
            criteria.setReturnDate(LocalDate.now().plusDays(37));
            criteria.setPassengers(2);
            
            System.out.println("Searching for flights from " + criteria.getOrigin() + " to " + 
                    criteria.getDestination() + " on " + criteria.getDepartureDate() + 
                    (criteria.isRoundTrip() ? " returning on " + criteria.getReturnDate() : "") + 
                    " for " + criteria.getPassengers() + " passenger(s)...");
            
            // In a real implementation, this would call the actual API
            // For demonstration, we'll simulate the response
            List<FlightOffer> flights = travelService.getFlightOffers(criteria);
            
            // Print the results
            System.out.println("Found " + flights.size() + " flights:");
            for (FlightOffer flight : flights) {
                System.out.println("- " + flight.getAirline() + " from " + flight.getDepartureAirport() + 
                        " to " + flight.getArrivalAirport() + " - " + flight.getPrice() + " " + 
                        flight.getCurrency() + " (" + flight.getStops() + " stops)");
            }
        } catch (ServiceException e) {
            System.err.println("Error during flight search: " + e.getMessage());
        }
    }
    
    /**
     * Demonstrates how API changes are handled by the architecture.
     */
    private void demonstrateApiChangeHandling() {
        System.out.println("\n--- API Change Handling Demonstration ---");
        
        System.out.println("Scenario: Booking.com API changes its response format");
        System.out.println("1. Only the BookingComAdapter needs to be updated");
        System.out.println("2. The adapter continues to return the same domain objects");
        System.out.println("3. The service layer and above remain unchanged");
        System.out.println("4. The frontend continues to receive consistent data");
        System.out.println("5. The user experience remains consistent");
        
        System.out.println("\nExample of adapter code that handles API changes:");
        System.out.println("```java");
        System.out.println("// Before API change");
        System.out.println("if (hotelJson.has(\"hotel_id\")) {");
        System.out.println("    hotel.setId(hotelJson.getString(\"hotel_id\"));");
        System.out.println("}");
        System.out.println("");
        System.out.println("// After API change");
        System.out.println("if (hotelJson.has(\"id\")) {");
        System.out.println("    hotel.setId(hotelJson.getString(\"id\"));");
        System.out.println("} else if (hotelJson.has(\"hotel_id\")) {");
        System.out.println("    // Fallback for backward compatibility");
        System.out.println("    hotel.setId(hotelJson.getString(\"hotel_id\"));");
        System.out.println("}");
        System.out.println("```");
    }
    
    /**
     * Mock implementation of the TravelServiceImpl for demonstration purposes.
     * This implementation returns simulated data instead of making actual API calls.
     */
    private static class MockTravelServiceImpl extends TravelServiceImpl {
        
        public MockTravelServiceImpl(DefaultApiGateway apiGateway) {
            super(apiGateway);
        }
        
        @Override
        public List<HotelBookingOption> getHotelBookingOptions(HotelSearchCriteria searchCriteria) throws ServiceException {
            try {
                // Create mock hotel options
                List<HotelBookingOption> hotels = new ArrayList<>();
                
                HotelBookingOption hotel1 = new HotelBookingOption("1", "Grand Hyatt Mumbai");
                hotel1.setRating(4.5);
                hotel1.setPrice(15000);
                hotel1.setCurrency("INR");
                hotels.add(hotel1);
                
                HotelBookingOption hotel2 = new HotelBookingOption("2", "Taj Lands End");
                hotel2.setRating(4.8);
                hotel2.setPrice(18000);
                hotel2.setCurrency("INR");
                hotels.add(hotel2);
                
                HotelBookingOption hotel3 = new HotelBookingOption("3", "The Oberoi Mumbai");
                hotel3.setRating(4.9);
                hotel3.setPrice(22000);
                hotel3.setCurrency("INR");
                hotels.add(hotel3);
                
                return hotels;
            } catch (Exception e) {
                throw new ServiceException("Error getting hotel booking options", "MOCK_ERROR", e);
            }
        }
        
        @Override
        public List<RestaurantRecommendation> getRestaurantRecommendations(Location location) throws ServiceException {
            try {
                // Create mock restaurant recommendations
                List<RestaurantRecommendation> restaurants = new ArrayList<>();
                
                RestaurantRecommendation restaurant1 = new RestaurantRecommendation("1", "Trishna");
                restaurant1.setCuisine("Seafood");
                restaurant1.setRating(4.6);
                restaurant1.setPriceLevel("$$$");
                restaurants.add(restaurant1);
                
                RestaurantRecommendation restaurant2 = new RestaurantRecommendation("2", "Khyber");
                restaurant2.setCuisine("North Indian");
                restaurant2.setRating(4.5);
                restaurant2.setPriceLevel("$$$");
                restaurants.add(restaurant2);
                
                RestaurantRecommendation restaurant3 = new RestaurantRecommendation("3", "Wasabi by Morimoto");
                restaurant3.setCuisine("Japanese");
                restaurant3.setRating(4.7);
                restaurant3.setPriceLevel("$$$$");
                restaurants.add(restaurant3);
                
                return restaurants;
            } catch (Exception e) {
                throw new ServiceException("Error getting restaurant recommendations", "MOCK_ERROR", e);
            }
        }
        
        @Override
        public List<FlightOffer> getFlightOffers(FlightSearchCriteria searchCriteria) throws ServiceException {
            try {
                // Create mock flight offers
                List<FlightOffer> flights = new ArrayList<>();
                
                FlightOffer flight1 = new FlightOffer("1");
                flight1.setAirline("KLM");
                flight1.setDepartureAirport("AMS");
                flight1.setArrivalAirport("JFK");
                flight1.setPrice(850);
                flight1.setCurrency("EUR");
                flight1.setStops(0);
                flights.add(flight1);
                
                FlightOffer flight2 = new FlightOffer("2");
                flight2.setAirline("Delta");
                flight2.setDepartureAirport("AMS");
                flight2.setArrivalAirport("JFK");
                flight2.setPrice(780);
                flight2.setCurrency("EUR");
                flight2.setStops(1);
                flights.add(flight2);
                
                FlightOffer flight3 = new FlightOffer("3");
                flight3.setAirline("British Airways");
                flight3.setDepartureAirport("AMS");
                flight3.setArrivalAirport("JFK");
                flight3.setPrice(820);
                flight3.setCurrency("EUR");
                flight3.setStops(1);
                flights.add(flight3);
                
                return flights;
            } catch (Exception e) {
                throw new ServiceException("Error getting flight offers", "MOCK_ERROR", e);
            }
        }
    }
    
    /**
     * Helper class for creating lists.
     */
    private static class ArrayList<T> extends java.util.ArrayList<T> {
        // Just a convenience class for the mock implementation
    }
}
