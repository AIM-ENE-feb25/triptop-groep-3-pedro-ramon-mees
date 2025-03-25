package ese.triptop.onderzoeksvraag.dto;

/**
 * Data Transfer Object for restaurant information.
 * Contains details about a restaurant from an external API.
 */
public class RestaurantDto {
    
    private String id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String address;
    private String city;
    private String country;
    private String cuisine;
    private double rating;
    private String priceLevel;
    private String imageUrl;
    
    /**
     * Default constructor.
     */
    public RestaurantDto() {
    }
    
    /**
     * Creates a new RestaurantDto with the specified ID and name.
     * 
     * @param id The restaurant ID
     * @param name The restaurant name
     */
    public RestaurantDto(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    /**
     * Gets the restaurant ID.
     * 
     * @return The restaurant ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the restaurant ID.
     * 
     * @param id The restaurant ID
     */
    public void setId(String id) {
        this.id = id;
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
    
    /**
     * Gets the address.
     * 
     * @return The address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Sets the address.
     * 
     * @param address The address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Gets the city.
     * 
     * @return The city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Sets the city.
     * 
     * @param city The city
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Gets the country.
     * 
     * @return The country
     */
    public String getCountry() {
        return country;
    }
    
    /**
     * Sets the country.
     * 
     * @param country The country
     */
    public void setCountry(String country) {
        this.country = country;
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
}
