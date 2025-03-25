package ese.triptop.onderzoeksvraag.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a hotel booking option.
 * Contains information about a hotel that can be booked.
 */
public class HotelBookingOption {
    
    private String hotelId;
    private String name;
    private String description;
    private Location location;
    private double price;
    private String currency;
    private double rating;
    private List<String> amenities;
    private String imageUrl;
    
    /**
     * Default constructor.
     */
    public HotelBookingOption() {
        this.amenities = new ArrayList<>();
    }
    
    /**
     * Creates a new HotelBookingOption with the specified ID and name.
     * 
     * @param hotelId The hotel ID
     * @param name The hotel name
     */
    public HotelBookingOption(String hotelId, String name) {
        this.hotelId = hotelId;
        this.name = name;
        this.amenities = new ArrayList<>();
    }
    
    /**
     * Gets the hotel ID.
     * 
     * @return The hotel ID
     */
    public String getHotelId() {
        return hotelId;
    }
    
    /**
     * Sets the hotel ID.
     * 
     * @param hotelId The hotel ID
     */
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
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
     * Gets the hotel location.
     * 
     * @return The hotel location
     */
    public Location getLocation() {
        return location;
    }
    
    /**
     * Sets the hotel location.
     * 
     * @param location The hotel location
     */
    public void setLocation(Location location) {
        this.location = location;
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
    
    @Override
    public String toString() {
        return "HotelBookingOption{" +
                "hotelId='" + hotelId + '\'' +
                ", name='" + name + '\'' +
                ", location=" + location +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", rating=" + rating +
                '}';
    }
}
