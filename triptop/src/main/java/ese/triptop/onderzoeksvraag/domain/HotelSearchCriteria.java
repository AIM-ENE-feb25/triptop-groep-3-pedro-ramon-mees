package ese.triptop.onderzoeksvraag.domain;

import java.time.LocalDate;

/**
 * Represents criteria for searching hotels.
 * Used to specify parameters for hotel searches.
 */
public class HotelSearchCriteria {
    
    private Location location;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int adults;
    private int rooms;
    
    /**
     * Default constructor.
     */
    public HotelSearchCriteria() {
    }
    
    /**
     * Creates a new HotelSearchCriteria with the specified location.
     * 
     * @param location The location to search for hotels
     */
    public HotelSearchCriteria(Location location) {
        this.location = location;
    }
    
    /**
     * Creates a new HotelSearchCriteria with the specified location, check-in date, and check-out date.
     * 
     * @param location The location to search for hotels
     * @param checkInDate The check-in date
     * @param checkOutDate The check-out date
     */
    public HotelSearchCriteria(Location location, LocalDate checkInDate, LocalDate checkOutDate) {
        this.location = location;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    
    /**
     * Gets the location.
     * 
     * @return The location
     */
    public Location getLocation() {
        return location;
    }
    
    /**
     * Sets the location.
     * 
     * @param location The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }
    
    /**
     * Gets the check-in date.
     * 
     * @return The check-in date
     */
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    
    /**
     * Sets the check-in date.
     * 
     * @param checkInDate The check-in date
     */
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    
    /**
     * Gets the check-out date.
     * 
     * @return The check-out date
     */
    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    
    /**
     * Sets the check-out date.
     * 
     * @param checkOutDate The check-out date
     */
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
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
    
    @Override
    public String toString() {
        return "HotelSearchCriteria{" +
                "location=" + location +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", adults=" + adults +
                ", rooms=" + rooms +
                '}';
    }
}
