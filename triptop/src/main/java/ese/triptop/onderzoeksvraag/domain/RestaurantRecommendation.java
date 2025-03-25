package ese.triptop.onderzoeksvraag.domain;

/**
 * Represents a restaurant recommendation.
 * Contains information about a restaurant that can be visited.
 */
public class RestaurantRecommendation {
    
    private String restaurantId;
    private String name;
    private String description;
    private Location location;
    private String cuisine;
    private double rating;
    private String priceLevel;
    private String imageUrl;
    
    /**
     * Default constructor.
     */
    public RestaurantRecommendation() {
    }
    
    /**
     * Creates a new RestaurantRecommendation with the specified ID and name.
     * 
     * @param restaurantId The restaurant ID
     * @param name The restaurant name
     */
    public RestaurantRecommendation(String restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
    }
    
    /**
     * Gets the restaurant ID.
     * 
     * @return The restaurant ID
     */
    public String getRestaurantId() {
        return restaurantId;
    }
    
    /**
     * Sets the restaurant ID.
     * 
     * @param restaurantId The restaurant ID
     */
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
    
    /**
     * Gets the restaurant name.
     * 
     * @return The restaurant name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the restaurant name.
     * 
     * @param name The restaurant name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the restaurant description.
     * 
     * @return The restaurant description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the restaurant description.
     * 
     * @param description The restaurant description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets the restaurant location.
     * 
     * @return The restaurant location
     */
    public Location getLocation() {
        return location;
    }
    
    /**
     * Sets the restaurant location.
     * 
     * @param location The restaurant location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    
    /**
     * Gets the cuisine type.
     * 
     * @return The cuisine type
     */
    public String getCuisine() {
        return cuisine;
    }
    
    /**
     * Sets the cuisine type.
     * 
     * @param cuisine The cuisine type
     */
    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }
    
    /**
     * Gets the rating.
     * 
     * @return The rating
     */
    public double getRating() {
        return rating;
    }
    
    /**
     * Sets the rating.
     * 
     * @param rating The rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    /**
     * Gets the price level.
     * 
     * @return The price level
     */
    public String getPriceLevel() {
        return priceLevel;
    }
    
    /**
     * Sets the price level.
     * 
     * @param priceLevel The price level
     */
    public void setPriceLevel(String priceLevel) {
        this.priceLevel = priceLevel;
    }
    
    /**
     * Gets the image URL.
     * 
     * @return The image URL
     */
    public String getImageUrl() {
        return imageUrl;
    }
    
    /**
     * Sets the image URL.
     * 
     * @param imageUrl The image URL
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    
    @Override
    public String toString() {
        return "RestaurantRecommendation{" +
                "restaurantId='" + restaurantId + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", cuisine='" + cuisine + '\'' +
                ", rating=" + rating +
                ", priceLevel='" + priceLevel + '\'' +
                '}';
    }
}
