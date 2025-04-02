package ese.triptop.prototype.adapter.contracts;

import ese.triptop.prototype.domain.Flight;

import java.util.List;

public interface IFlightAdapter {

    List<Flight> getFlights(String origin, String destination, String departureDate, int adults);
    boolean isAvailable();

}
