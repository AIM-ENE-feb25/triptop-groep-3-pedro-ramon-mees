package ese.triptop.onderzoeksvraag.dto;

/**
 * Data Transfer Object for restaurant search requests.
 * Contains parameters for searching restaurants.
 */
public class RestaurantSearchRequest {
    
    private String locationId;
    private int limit;
    private String language;
    private double latitude;
    private double longitude;
    
    /**
     * Default constructor.
     */
    public RestaurantSearchRequest() {
    }
    
    /**
     * Creates a new RestaurantSearchRequest with the specified location ID.
     * 
     * @param locationId The location ID
     */
    public RestaurantSearchRequest(String locationId) {
        this.locationId = locationId;
    }
    
    /**
     * Creates a new RestaurantSearchRequest with the specified coordinates.
     * 
     * @param latitude The latitude
     * @param longitude The longitude
     */
    public RestaurantSearchRequest(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**
     * Gets the location ID.
     * 
     * @return The location ID
     */
    public String getLocationId() {
        return locationId;
    }
    
    /**
     * Sets the location ID.
     * 
     * @param locationId The location ID
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }
    
    /**
     * Gets the limit.
     * 
     * @return The limit
     */
    public int getLimit() {
        return limit;
    }
    
    /**
     * Sets the limit.
     * 
     * @param limit The limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }
    
    /**
     * Gets the language.
     * 
     * @return The language
     */
    public String getLanguage() {
        return language;
    }
    
    /**
     * Sets the language.
     * 
     * @param language The language
     */
    public void setLanguage(String language) {
        this.language = language;
    }
    
    /**
     * Gets the latitude.
     * 
     * @return The latitude
     */
    public double getLatitude() {
        return latitude;
    }
    
    /**
     * Sets the latitude.
     * 
     * @param latitude The latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    /**
     * Gets the longitude.
     * 
     * @return The longitude
     */
    public double getLongitude() {
        return longitude;
    }
    
    /**
     * Sets the longitude.
     * 
     * @param longitude The longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
