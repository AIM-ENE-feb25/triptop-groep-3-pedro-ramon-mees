package ese.triptop.onderzoeksvraag.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for flight search responses.
 * Contains the results of a flight search.
 */
public class FlightSearchResponse {
    
    private List<FlightOfferDto> flightOffers;
    private String currency;
    private int totalCount;
    
    /**
     * Default constructor.
     */
    public FlightSearchResponse() {
        this.flightOffers = new ArrayList<>();
    }
    
    /**
     * Gets the list of flight offers.
     * 
     * @return The list of flight offers
     */
    public List<FlightOfferDto> getFlightOffers() {
        return flightOffers;
    }
    
    /**
     * Sets the list of flight offers.
     * 
     * @param flightOffers The list of flight offers
     */
    public void setFlightOffers(List<FlightOfferDto> flightOffers) {
        this.flightOffers = flightOffers;
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
     * Gets the total count of flight offers.
     * 
     * @return The total count
     */
    public int getTotalCount() {
        return totalCount;
    }
    
    /**
     * Sets the total count of flight offers.
     * 
     * @param totalCount The total count
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    
    /**
     * Adds a flight offer to the list of flight offers.
     * 
     * @param flightOffer The flight offer to add
     */
    public void addFlightOffer(FlightOfferDto flightOffer) {
        if (this.flightOffers == null) {
            this.flightOffers = new ArrayList<>();
        }
        this.flightOffers.add(flightOffer);
    }
}
