package ese.triptop.prototype.domain;

public class Flight {
    private String departureAirport;
    private Integer arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private double price;

    public Flight(String departureAirport, Integer arrivalAirport, String departureTime, String arrivalTime, double price) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Integer getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Integer arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "departureAirport='" + departureAirport + '\'' +
                ", arrivalAirport='" + arrivalTime + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalAirport + '\'' +
                ", price=" + price +
                '}';
    }
}
