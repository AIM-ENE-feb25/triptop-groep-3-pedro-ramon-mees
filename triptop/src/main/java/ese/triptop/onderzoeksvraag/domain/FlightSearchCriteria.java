package ese.triptop.onderzoeksvraag.domain;

import java.time.LocalDate;

/**
 * Represents criteria for searching flights.
 * Used to specify parameters for flight searches.
 */
public class FlightSearchCriteria {
    
    private String origin;
    private String destination;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private int passengers;
    
    /**
     * Default constructor.
     */
    public FlightSearchCriteria() {
    }
    
    /**
     * Creates a new FlightSearchCriteria with the specified origin and destination.
     * 
     * @param origin The origin airport code
     * @param destination The destination airport code
     */
    public FlightSearchCriteria(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }
    
    /**
     * Creates a new FlightSearchCriteria with the specified origin, destination, and departure date.
     * 
     * @param origin The origin airport code
     * @param destination The destination airport code
     * @param departureDate The departure date
     */
    public FlightSearchCriteria(String origin, String destination, LocalDate departureDate) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
    }
    
    /**
     * Gets the origin airport code.
     * 
     * @return The origin airport code
     */
    public String getOrigin() {
        return origin;
    }
    
    /**
     * Sets the origin airport code.
     * 
     * @param origin The origin airport code
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }
    
    /**
     * Gets the destination airport code.
     * 
     * @return The destination airport code
     */
    public String getDestination() {
        return destination;
    }
    
    /**
     * Sets the destination airport code.
     * 
     * @param destination The destination airport code
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    /**
     * Gets the departure date.
     * 
     * @return The departure date
     */
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    
    /**
     * Sets the departure date.
     * 
     * @param departureDate The departure date
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }
    
    /**
     * Gets the return date.
     * 
     * @return The return date
     */
    public LocalDate getReturnDate() {
        return returnDate;
    }
    
    /**
     * Sets the return date.
     * 
     * @param returnDate The return date
     */
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
    
    /**
     * Gets the number of passengers.
     * 
     * @return The number of passengers
     */
    public int getPassengers() {
        return passengers;
    }
    
    /**
     * Sets the number of passengers.
     * 
     * @param passengers The number of passengers
     */
    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
    
    /**
     * Checks if this is a round trip.
     * 
     * @return true if this is a round trip, false otherwise
     */
    public boolean isRoundTrip() {
        return returnDate != null;
    }
    
    @Override
    public String toString() {
        return "FlightSearchCriteria{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departureDate=" + departureDate +
                ", returnDate=" + returnDate +
                ", passengers=" + passengers +
                '}';
    }
}
