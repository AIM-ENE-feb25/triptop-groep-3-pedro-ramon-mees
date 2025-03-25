package ese.triptop.onderzoeksvraag.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for hotel search responses.
 * Contains the results of a hotel search.
 */
public class HotelSearchResponse {
    
    private List<HotelDto> hotels;
    private int totalCount;
    private String currency;
    
    /**
     * Default constructor.
     */
    public HotelSearchResponse() {
        this.hotels = new ArrayList<>();
    }
    
    /**
     * Gets the list of hotels.
     * 
     * @return The list of hotels
     */
    public List<HotelDto> getHotels() {
        return hotels;
    }
    
    /**
     * Sets the list of hotels.
     * 
     * @param hotels The list of hotels
     */
    public void setHotels(List<HotelDto> hotels) {
        this.hotels = hotels;
    }
    
    /**
     * Gets the total count of hotels.
     * 
     * @return The total count
     */
    public int getTotalCount() {
        return totalCount;
    }
    
    /**
     * Sets the total count of hotels.
     * 
     * @param totalCount The total count
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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
     * Adds a hotel to the list of hotels.
     * 
     * @param hotel The hotel to add
     */
    public void addHotel(HotelDto hotel) {
        if (this.hotels == null) {
            this.hotels = new ArrayList<>();
        }
        this.hotels.add(hotel);
    }
}
