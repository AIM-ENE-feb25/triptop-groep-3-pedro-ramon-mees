package ese.triptop.onderzoeksvraag.dto;

/**
 * Data Transfer Object for hotel search requests.
 * Contains parameters for searching hotels.
 */
public class HotelSearchRequest {
    
    private double latitude;
    private double longitude;
    private String arrivalDate;
    private String departureDate;
    private int adults;
    private int rooms;
    private String currency;
    private String locale;
    
    /**
     * Default constructor.
     */
    public HotelSearchRequest() {
    }
    
    /**
     * Creates a new HotelSearchRequest with the specified coordinates.
     * 
     * @param latitude The latitude
     * @param longitude The longitude
     */
    public HotelSearchRequest(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
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
     * Gets the arrival date.
     * 
     * @return The arrival date
     */
    public String getArrivalDate() {
        return arrivalDate;
    }
    
    /**
     * Sets the arrival date.
     * 
     * @param arrivalDate The arrival date
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
    
    /**
     * Gets the departure date.
     * 
     * @return The departure date
     */
    public String getDepartureDate() {
        return departureDate;
    }
    
    /**
     * Sets the departure date.
     * 
     * @param departureDate The departure date
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }
    
    /**
     * Gets the number of adults.
     * 
     * @return The number of adults
     */
    public int getAdults() {
        return adults;
    }
    
    /**
     * Sets the number of adults.
     * 
     * @param adults The number of adults
     */
    public void setAdults(int adults) {
        this.adults = adults;
    }
    
    /**
     * Gets the number of rooms.
     * 
     * @return The number of rooms
     */
    public int getRooms() {
        return rooms;
    }
    
    /**
     * Sets the number of rooms.
     * 
     * @param rooms The number of rooms
     */
    public void setRooms(int rooms) {
        this.rooms = rooms;
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
     * Gets the locale.
     * 
     * @return The locale
     */
    public String getLocale() {
        return locale;
    }
    
    /**
     * Sets the locale.
     * 
     * @param locale The locale
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }
}
