package ese.triptop.onderzoeksvraag.domain;

/**
 * Represents a geographical location.
 * Used for hotel searches, restaurant recommendations, etc.
 */
public class Location {
    
    private double latitude;
    private double longitude;
    private String city;
    private String country;
    
    /**
     * Default constructor.
     */
    public Location() {
    }
    
    /**
     * Creates a new Location with the specified coordinates.
     * 
     * @param latitude The latitude
     * @param longitude The longitude
     */
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    /**
     * Creates a new Location with the specified coordinates, city, and country.
     * 
     * @param latitude The latitude
     * @param longitude The longitude
     * @param city The city
     * @param country The country
     */
    public Location(double latitude, double longitude, String city, String country) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.city = city;
        this.country = country;
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
    
    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
