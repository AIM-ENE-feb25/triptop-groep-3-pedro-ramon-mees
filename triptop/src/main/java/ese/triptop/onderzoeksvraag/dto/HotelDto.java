package ese.triptop.onderzoeksvraag.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for hotel information.
 * Contains details about a hotel from an external API.
 */
public class HotelDto {
    
    private String id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String address;
    private String city;
    private String country;
    private double price;
    private String currency;
    private double rating;
    private List<String> amenities;
    private String imageUrl;
    
    /**
     * Default constructor.
     */
    public HotelDto() {
        this.amenities = new ArrayList<>();
    }
    
    /**
     * Creates a new HotelDto with the specified ID and name.
     * 
     * @param id The hotel ID
     * @param name The hotel name
     */
    public HotelDto(String id, String name) {
        this.id = id;
        this.name = name;
        this.amenities = new ArrayList<>();
    }
    
    /**
     * Gets the hotel ID.
     * 
     * @return The hotel ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the hotel ID.
     * 
     * @param id The hotel ID
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Gets the hotel name.
     * 
     * @return The hotel name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the hotel name.
     * 
     * @param name The hotel name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the hotel description.
     * 
     * @return The hotel description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the hotel description.
     * 
     * @param description The hotel description
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
     * Gets the price.
     * 
     * @return The price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Sets the price.
     * 
     * @param price The price
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * Gets the currency.
     * 
     * @return The currency
     */
    public String getCurrency() {
        return currency;
    }
    
    /**
     * Sets the currency.
     * 
     * @param currency The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
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
     * Gets the amenities.
     * 
     * @return The amenities
     */
    public List<String> getAmenities() {
        return amenities;
    }
    
    /**
     * Sets the amenities.
     * 
     * @param amenities The amenities
     */
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
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
    
    /**
     * Adds an amenity to the list of amenities.
     * 
     * @param amenity The amenity to add
     */
    public void addAmenity(String amenity) {
        if (this.amenities == null) {
            this.amenities = new ArrayList<>();
        }
        this.amenities.add(amenity);
    }
}
