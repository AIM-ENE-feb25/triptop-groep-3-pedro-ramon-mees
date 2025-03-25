package ese.triptop.onderzoeksvraag.dto;

import java.util.ArrayList;
import java.util.List;

import ese.triptop.onderzoeksvraag.dto.RestaurantDto;

/**
 * Data Transfer Object for restaurant search responses.
 * Contains the results of a restaurant search.
 */
public class RestaurantSearchResponse {
    
    private List<RestaurantDto> restaurants;
    private int totalCount;
    
    /**
     * Default constructor.
     */
    public RestaurantSearchResponse() {
        this.restaurants = new ArrayList<>();
    }
    
    /**
     * Gets the list of restaurants.
     * 
     * @return The list of restaurants
     */
    public List<RestaurantDto> getRestaurants() {
        return restaurants;
    }
    
    /**
     * Sets the list of restaurants.
     * 
     * @param restaurants The list of restaurants
     */
    public void setRestaurants(List<RestaurantDto> restaurants) {
        this.restaurants = restaurants;
    }
    
    /**
     * Gets the total count of restaurants.
     * 
     * @return The total count
     */
    public int getTotalCount() {
        return totalCount;
    }
    
    /**
     * Sets the total count of restaurants.
     * 
     * @param totalCount The total count
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    
    /**
     * Adds a restaurant to the list of restaurants.
     * 
     * @param restaurant The restaurant to add
     */
    public void addRestaurant(RestaurantDto restaurant) {
        if (this.restaurants == null) {
            this.restaurants = new ArrayList<>();
        }
        this.restaurants.add(restaurant);
    }
}
