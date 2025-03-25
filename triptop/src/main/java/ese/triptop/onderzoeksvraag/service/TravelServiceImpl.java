package ese.triptop.onderzoeksvraag.service;

import ese.triptop.onderzoeksvraag.adapters.BookingAdapter;
import ese.triptop.onderzoeksvraag.adapters.FlightAdapter;
import ese.triptop.onderzoeksvraag.adapters.TripAdvisorAdapter;
import ese.triptop.onderzoeksvraag.api.ApiException;
import ese.triptop.onderzoeksvraag.api.ApiGateway;
import ese.triptop.onderzoeksvraag.domain.FlightOffer;
import ese.triptop.onderzoeksvraag.domain.FlightSearchCriteria;
import ese.triptop.onderzoeksvraag.domain.HotelBookingOption;
import ese.triptop.onderzoeksvraag.domain.HotelSearchCriteria;
import ese.triptop.onderzoeksvraag.domain.Location;
import ese.triptop.onderzoeksvraag.domain.RestaurantRecommendation;
import ese.triptop.onderzoeksvraag.dto.FlightOfferDto;
import ese.triptop.onderzoeksvraag.dto.FlightSearchRequest;
import ese.triptop.onderzoeksvraag.dto.FlightSearchResponse;
import ese.triptop.onderzoeksvraag.dto.HotelDto;
import ese.triptop.onderzoeksvraag.dto.HotelSearchRequest;
import ese.triptop.onderzoeksvraag.dto.HotelSearchResponse;
import ese.triptop.onderzoeksvraag.dto.RestaurantDto;
import ese.triptop.onderzoeksvraag.dto.RestaurantSearchRequest;
import ese.triptop.onderzoeksvraag.dto.RestaurantSearchResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the TravelService interface.
 * Uses the API Gateway to interact with external APIs.
 */
public class TravelServiceImpl implements TravelService {
    
    private final ApiGateway apiGateway;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * Creates a new TravelServiceImpl with the specified API Gateway.
     * 
     * @param apiGateway The API Gateway to use
     */
    public TravelServiceImpl(ApiGateway apiGateway) {
        this.apiGateway = apiGateway;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<HotelBookingOption> getHotelBookingOptions(HotelSearchCriteria searchCriteria) throws ServiceException {
        try {
            HotelSearchRequest request = createHotelSearchRequest(searchCriteria);
            HotelSearchResponse response = apiGateway.executeApiCall(BookingAdapter.class, request);
            return mapToHotelBookingOptions(response);
        } catch (ApiException e) {
            throw new ServiceException("Failed to get hotel booking options", "HOTEL_SEARCH_FAILED", e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RestaurantRecommendation> getRestaurantRecommendations(Location location) throws ServiceException {
        try {
            RestaurantSearchRequest request = createRestaurantSearchRequest(location);
            RestaurantSearchResponse response = apiGateway.executeApiCall(TripAdvisorAdapter.class, request);
            return mapToRestaurantRecommendations(response);
        } catch (ApiException e) {
            throw new ServiceException("Failed to get restaurant recommendations", "RESTAURANT_SEARCH_FAILED", e);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<FlightOffer> getFlightOffers(FlightSearchCriteria searchCriteria) throws ServiceException {
        try {
            FlightSearchRequest request = createFlightSearchRequest(searchCriteria);
            FlightSearchResponse response = apiGateway.executeApiCall(FlightAdapter.class, request);
            return mapToFlightOffers(response);
        } catch (ApiException e) {
            throw new ServiceException("Failed to get flight offers", "FLIGHT_SEARCH_FAILED", e);
        }
    }
    
    /**
     * Creates a HotelSearchRequest from a HotelSearchCriteria.
     * 
     * @param criteria The search criteria
     * @return The search request
     */
    private HotelSearchRequest createHotelSearchRequest(HotelSearchCriteria criteria) {
        HotelSearchRequest request = new HotelSearchRequest();
        
        if (criteria.getLocation() != null) {
            request.setLatitude(criteria.getLocation().getLatitude());
            request.setLongitude(criteria.getLocation().getLongitude());
        }
        
        if (criteria.getCheckInDate() != null) {
            request.setArrivalDate(criteria.getCheckInDate().format(DATE_FORMATTER));
        }
        
        if (criteria.getCheckOutDate() != null) {
            request.setDepartureDate(criteria.getCheckOutDate().format(DATE_FORMATTER));
        }
        
        request.setAdults(criteria.getAdults());
        request.setRooms(criteria.getRooms());
        request.setCurrency("EUR");
        request.setLocale("en-US");
        
        return request;
    }
    
    /**
     * Creates a RestaurantSearchRequest from a Location.
     * 
     * @param location The location
     * @return The search request
     */
    private RestaurantSearchRequest createRestaurantSearchRequest(Location location) {
        RestaurantSearchRequest request = new RestaurantSearchRequest();
        
        request.setLatitude(location.getLatitude());
        request.setLongitude(location.getLongitude());
        
        if (location.getCity() != null) {
            // Some APIs use location IDs instead of coordinates
            // This is a simplified example
            request.setLocationId(location.getCity());
        }
        
        request.setLimit(20);
        request.setLanguage("en");
        
        return request;
    }
    
    /**
     * Creates a FlightSearchRequest from a FlightSearchCriteria.
     * 
     * @param criteria The search criteria
     * @return The search request
     */
    private FlightSearchRequest createFlightSearchRequest(FlightSearchCriteria criteria) {
        FlightSearchRequest request = new FlightSearchRequest();
        
        request.setOriginLocationCode(criteria.getOrigin());
        request.setDestinationLocationCode(criteria.getDestination());
        
        if (criteria.getDepartureDate() != null) {
            request.setDepartureDate(criteria.getDepartureDate().format(DATE_FORMATTER));
        }
        
        if (criteria.getReturnDate() != null) {
            request.setReturnDate(criteria.getReturnDate().format(DATE_FORMATTER));
        }
        
        request.setAdults(criteria.getPassengers());
        request.setCurrency("EUR");
        request.setLocale("en-US");
        
        return request;
    }
    
    /**
     * Maps a HotelSearchResponse to a list of HotelBookingOptions.
     * 
     * @param response The response
     * @return The list of hotel booking options
     */
    private List<HotelBookingOption> mapToHotelBookingOptions(HotelSearchResponse response) {
        List<HotelBookingOption> options = new ArrayList<>();
        
        if (response.getHotels() != null) {
            for (HotelDto hotelDto : response.getHotels()) {
                HotelBookingOption option = new HotelBookingOption();
                
                option.setHotelId(hotelDto.getId());
                option.setName(hotelDto.getName());
                option.setDescription(hotelDto.getDescription());
                
                Location location = new Location();
                location.setLatitude(hotelDto.getLatitude());
                location.setLongitude(hotelDto.getLongitude());
                location.setCity(hotelDto.getCity());
                location.setCountry(hotelDto.getCountry());
                option.setLocation(location);
                
                option.setPrice(hotelDto.getPrice());
                option.setCurrency(hotelDto.getCurrency());
                option.setRating(hotelDto.getRating());
                option.setAmenities(hotelDto.getAmenities());
                option.setImageUrl(hotelDto.getImageUrl());
                
                options.add(option);
            }
        }
        
        return options;
    }
    
    /**
     * Maps a RestaurantSearchResponse to a list of RestaurantRecommendations.
     * 
     * @param response The response
     * @return The list of restaurant recommendations
     */
    private List<RestaurantRecommendation> mapToRestaurantRecommendations(RestaurantSearchResponse response) {
        List<RestaurantRecommendation> recommendations = new ArrayList<>();
        
        if (response.getRestaurants() != null) {
            for (RestaurantDto restaurantDto : response.getRestaurants()) {
                RestaurantRecommendation recommendation = new RestaurantRecommendation();
                
                recommendation.setRestaurantId(restaurantDto.getId());
                recommendation.setName(restaurantDto.getName());
                recommendation.setDescription(restaurantDto.getDescription());
                
                Location location = new Location();
                location.setLatitude(restaurantDto.getLatitude());
                location.setLongitude(restaurantDto.getLongitude());
                location.setCity(restaurantDto.getCity());
                location.setCountry(restaurantDto.getCountry());
                recommendation.setLocation(location);
                
                recommendation.setCuisine(restaurantDto.getCuisine());
                recommendation.setRating(restaurantDto.getRating());
                recommendation.setPriceLevel(restaurantDto.getPriceLevel());
                recommendation.setImageUrl(restaurantDto.getImageUrl());
                
                recommendations.add(recommendation);
            }
        }
        
        return recommendations;
    }
    
    /**
     * Maps a FlightSearchResponse to a list of FlightOffers.
     * 
     * @param response The response
     * @return The list of flight offers
     */
    private List<FlightOffer> mapToFlightOffers(FlightSearchResponse response) {
        List<FlightOffer> offers = new ArrayList<>();
        
        if (response.getFlightOffers() != null) {
            for (FlightOfferDto offerDto : response.getFlightOffers()) {
                FlightOffer offer = new FlightOffer();
                
                offer.setOfferId(offerDto.getId());
                offer.setAirline(offerDto.getAirline());
                offer.setDepartureAirport(offerDto.getDepartureAirport());
                offer.setArrivalAirport(offerDto.getArrivalAirport());
                
                // Parse date-time strings to LocalDateTime
                // This is a simplified example
                offer.setDepartureTime(LocalDateTime.now()); // In a real implementation, parse from offerDto.getDepartureTime()
                offer.setArrivalTime(LocalDateTime.now().plusHours(2)); // In a real implementation, parse from offerDto.getArrivalTime()
                
                offer.setPrice(offerDto.getPrice());
                offer.setCurrency(offerDto.getCurrency());
                offer.setStops(offerDto.getStops());
                offer.setDuration(offerDto.getDuration());
                
                offers.add(offer);
            }
        }
        
        return offers;
    }
}
