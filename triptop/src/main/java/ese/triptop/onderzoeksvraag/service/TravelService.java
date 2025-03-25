package ese.triptop.onderzoeksvraag.service;

import ese.triptop.onderzoeksvraag.domain.FlightOffer;
import ese.triptop.onderzoeksvraag.domain.FlightSearchCriteria;
import ese.triptop.onderzoeksvraag.domain.HotelBookingOption;
import ese.triptop.onderzoeksvraag.domain.HotelSearchCriteria;
import ese.triptop.onderzoeksvraag.domain.Location;
import ese.triptop.onderzoeksvraag.domain.RestaurantRecommendation;

import java.util.List;

/**
 * Interface for travel-related services.
 * Provides methods for searching hotels, restaurants, and flights.
 */
public interface TravelService {
    
    /**
     * Gets hotel booking options based on search criteria.
     * 
     * @param searchCriteria The search criteria
     * @return A list of hotel booking options
     * @throws ServiceException If an error occurs during the operation
     */
    List<HotelBookingOption> getHotelBookingOptions(HotelSearchCriteria searchCriteria) throws ServiceException;
    
    /**
     * Gets restaurant recommendations based on location.
     * 
     * @param location The location to search for restaurants
     * @return A list of restaurant recommendations
     * @throws ServiceException If an error occurs during the operation
     */
    List<RestaurantRecommendation> getRestaurantRecommendations(Location location) throws ServiceException;
    
    /**
     * Gets flight offers based on search criteria.
     * 
     * @param searchCriteria The search criteria
     * @return A list of flight offers
     * @throws ServiceException If an error occurs during the operation
     */
    List<FlightOffer> getFlightOffers(FlightSearchCriteria searchCriteria) throws ServiceException;
}
