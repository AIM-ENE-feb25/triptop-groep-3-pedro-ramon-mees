package ese.triptop.prototype.domain;

public class Flight {
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private Integer flightDuration;
    private double price;
    private String adapterName;

    public Flight(String departureAirport,
                        String arrivalAirport,
                        String departureTime, 
                        String arrivalTime, 
                        double price,
                        Integer flightDuration,
                        String adapterName) {
        this.adapterName = adapterName;
        this.flightDuration = flightDuration;
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

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
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

    public Integer getFlightDuration() {
        return flightDuration;
    }
    public void setFlightDuration(Integer flightDuration) {
        this.flightDuration = flightDuration;
    }
    public String getAdapterName() {
        return adapterName;
    }
    public void setAdapterName(String adapterName) {
        this.adapterName = adapterName;
    }


    @Override
    public String toString() {
        return "Flight {\n" +
               "\tdepartureAirport='" + departureAirport + "',\n" +
               "\tarrivalAirport='" + arrivalAirport + "',\n" +
               "\tdepartureTime='" + departureTime + "',\n" +
               "\tarrivalTime='" + arrivalTime + "',\n" +
               "\tprice=" + price + ",\n" +
               "\tflightDuration=" + flightDuration + "\n" +
               "}";
    }
}
