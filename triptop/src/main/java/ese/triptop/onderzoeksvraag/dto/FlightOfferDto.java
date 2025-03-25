package ese.triptop.onderzoeksvraag.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for flight offer information.
 * Contains details about a flight offer from an external API.
 */
public class FlightOfferDto {
    
    private String id;
    private String airline;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private double price;
    private String currency;
    private int stops;
    private String duration;
    
    /**
     * Default constructor.
     */
    public FlightOfferDto() {
    }
    
    /**
     * Creates a new FlightOfferDto with the specified ID.
     * 
     * @param id The flight offer ID
     */
    public FlightOfferDto(String id) {
        this.id = id;
    }
    
    /**
     * Gets the flight offer ID.
     * 
     * @return The flight offer ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * Sets the flight offer ID.
     * 
     * @param id The flight offer ID
     */
    public void setId(String id) {
        this.id = id;
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
    public String getDepartureTime() {
        return departureTime;
    }
    
    /**
     * Sets the departure time.
     * 
     * @param departureTime The departure time
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }
    
    /**
     * Gets the arrival time.
     * 
     * @return The arrival time
     */
    public String getArrivalTime() {
        return arrivalTime;
    }
    
    /**
     * Sets the arrival time.
     * 
     * @param arrivalTime The arrival time
     */
    public void setArrivalTime(String arrivalTime) {
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
}
