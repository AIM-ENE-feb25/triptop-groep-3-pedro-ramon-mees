package ese.triptop.onderzoeksvraag.dto;

/**
 * Data Transfer Object for flight search requests.
 * Contains parameters for searching flights.
 */
public class FlightSearchRequest {
    
    private String originLocationCode;
    private String destinationLocationCode;
    private String departureDate;
    private String returnDate;
    private int adults;
    private String currency;
    private String locale;
    
    /**
     * Default constructor.
     */
    public FlightSearchRequest() {
    }
    
    /**
     * Creates a new FlightSearchRequest with the specified origin and destination.
     * 
     * @param originLocationCode The origin location code
     * @param destinationLocationCode The destination location code
     */
    public FlightSearchRequest(String originLocationCode, String destinationLocationCode) {
        this.originLocationCode = originLocationCode;
        this.destinationLocationCode = destinationLocationCode;
    }
    
    /**
     * Creates a new FlightSearchRequest with the specified origin, destination, and departure date.
     * 
     * @param originLocationCode The origin location code
     * @param destinationLocationCode The destination location code
     * @param departureDate The departure date
     */
    public FlightSearchRequest(String originLocationCode, String destinationLocationCode, String departureDate) {
        this.originLocationCode = originLocationCode;
        this.destinationLocationCode = destinationLocationCode;
        this.departureDate = departureDate;
    }
    
    /**
     * Gets the origin location code.
     * 
     * @return The origin location code
     */
    public String getOriginLocationCode() {
        return originLocationCode;
    }
    
    /**
     * Sets the origin location code.
     * 
     * @param originLocationCode The origin location code
     */
    public void setOriginLocationCode(String originLocationCode) {
        this.originLocationCode = originLocationCode;
    }
    
    /**
     * Gets the destination location code.
     * 
     * @return The destination location code
     */
    public String getDestinationLocationCode() {
        return destinationLocationCode;
    }
    
    /**
     * Sets the destination location code.
     * 
     * @param destinationLocationCode The destination location code
     */
    public void setDestinationLocationCode(String destinationLocationCode) {
        this.destinationLocationCode = destinationLocationCode;
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
     * Gets the return date.
     * 
     * @return The return date
     */
    public String getReturnDate() {
        return returnDate;
    }
    
    /**
     * Sets the return date.
     * 
     * @param returnDate The return date
     */
    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
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
    
    /**
     * Checks if this is a round trip.
     * 
     * @return true if this is a round trip, false otherwise
     */
    public boolean isRoundTrip() {
        return returnDate != null && !returnDate.isEmpty();
    }
}
