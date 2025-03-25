package ese.triptop.onderzoeksvraag.config;

import ese.triptop.onderzoeksvraag.adapters.BookingAdapter;
import ese.triptop.onderzoeksvraag.adapters.BookingComAdapter;
import ese.triptop.onderzoeksvraag.adapters.FlightAdapter;
import ese.triptop.onderzoeksvraag.adapters.FlightApiAdapter;
import ese.triptop.onderzoeksvraag.adapters.TripAdvisorAdapter;
import ese.triptop.onderzoeksvraag.adapters.TripAdvisorApiAdapter;
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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Spring configuration class for the API architecture.
 * Defines all the beans needed for the architecture.
 */
@Configuration
public class ApiArchitectureConfig {
    
    /**
     * Creates a BookingAdapter bean.
     * 
     * @param configManager The configuration manager
     * @return The BookingAdapter
     */
    @Bean
    public BookingAdapter bookingAdapter(ConfigurationManager configManager) {
        return new BookingComAdapter(configManager);
    }
    
    /**
     * Creates a TripAdvisorAdapter bean.
     * 
     * @param configManager The configuration manager
     * @return The TripAdvisorAdapter
     */
    @Bean
    public TripAdvisorAdapter tripAdvisorAdapter(ConfigurationManager configManager) {
        return new TripAdvisorApiAdapter(configManager);
    }
    
    /**
     * Creates a FlightAdapter bean.
     * 
     * @param configManager The configuration manager
     * @return The FlightAdapter
     */
    @Bean
    public FlightAdapter flightAdapter(ConfigurationManager configManager) {
        return new FlightApiAdapter(configManager);
    }
    
    /**
     * Creates an ApiGateway bean.
     * 
     * @param configManager The configuration manager
     * @param bookingAdapter The BookingAdapter
     * @param tripAdvisorAdapter The TripAdvisorAdapter
     * @param flightAdapter The FlightAdapter
     * @return The ApiGateway
     */
    @Bean
    public DefaultApiGateway apiGateway(
            ConfigurationManager configManager,
            BookingAdapter bookingAdapter,
            TripAdvisorAdapter tripAdvisorAdapter,
            FlightAdapter flightAdapter) {
        DefaultApiGateway apiGateway = new DefaultApiGateway(configManager);
        apiGateway.registerAdapter(BookingAdapter.class, bookingAdapter);
        apiGateway.registerAdapter(TripAdvisorAdapter.class, tripAdvisorAdapter);
        apiGateway.registerAdapter(FlightAdapter.class, flightAdapter);
        return apiGateway;
    }
    
    /**
     * Creates a TravelService bean.
     * 
     * @param apiGateway The API gateway
     * @return The TravelService
     */
    @Bean
    public TravelService travelService(DefaultApiGateway apiGateway) {
        // For demonstration purposes, we'll use a mock implementation
        return new MockTravelServiceImpl(apiGateway);
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
