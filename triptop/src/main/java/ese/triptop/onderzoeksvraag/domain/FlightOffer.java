package ese.triptop.onderzoeksvraag.domain;

import java.time.LocalDateTime;

/**
 * Represents a flight offer.
 * Contains information about a flight that can be booked.
 */
public class FlightOffer {
    
    private String offerId;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private double price;
    private String currency;
    private int stops;
    private String duration;
    
    /**
     * Default constructor.
     */
    public FlightOffer() {
    }
    
    /**
     * Creates a new FlightOffer with the specified ID.
     * 
     * @param offerId The offer ID
     */
    public FlightOffer(String offerId) {
        this.offerId = offerId;
    }
    
    /**
     * Gets the offer ID.
     * 
     * @return The offer ID
     */
    public String getOfferId() {
        return offerId;
    }
    
    /**
     * Sets the offer ID.
     * 
     * @param offerId The offer ID
     */
    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }
    
    /**
     * Gets the airline.
     * 
     * @return The airline
     */
    public String getAirline() {
        return airline;
    }
    
    /**
     * Sets the airline.
     * 
     * @param airline The airline
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }
    
    /**
     * Gets the departure airport.
     * 
     * @return The departure airport
     */
    public String getDepartureAirport() {
        return departureAirport;
    }
    
    /**
     * Sets the departure airport.
     * 
     * @param departureAirport The departure airport
     */
    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }
    
    /**
     * Gets the arrival airport.
     * 
     * @return The arrival airport
     */
    public String getArrivalAirport() {
        return arrivalAirport;
    }
    
    /**
     * Sets the arrival airport.
     * 
     * @param arrivalAirport The arrival airport
     */
    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }
    
    /**
     * Gets the departure time.
     * 
     * @return The departure time
     */
    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    
    /**
     * Sets the departure time.
     * 
     * @param departureTime The departure time
     */
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }
    
    /**
     * Gets the arrival time.
     * 
     * @return The arrival time
     */
    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    
    /**
     * Sets the arrival time.
     * 
     * @param arrivalTime The arrival time
     */
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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
     * Gets the number of stops.
     * 
     * @return The number of stops
     */
    public int getStops() {
        return stops;
    }
    
    /**
     * Sets the number of stops.
     * 
     * @param stops The number of stops
     */
    public void setStops(int stops) {
        this.stops = stops;
    }
    
    /**
     * Gets the duration.
     * 
     * @return The duration
     */
    public String getDuration() {
        return duration;
    }
    
    /**
     * Sets the duration.
     * 
     * @param duration The duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    @Override
    public String toString() {
        return "FlightOffer{" +
                "offerId='" + offerId + '\'' +
                ", airline='" + airline + '\'' +
                ", departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalAirport + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", stops=" + stops +
                '}';
    }
}
