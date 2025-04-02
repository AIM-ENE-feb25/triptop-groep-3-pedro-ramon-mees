package ese.triptop.prototype.adapter;

import ese.triptop.prototype.adapter.contracts.IFlightAdapter;
import ese.triptop.prototype.domain.Flight;

import java.util.List;

public class SkyscannerFlightAdapter implements IFlightAdapter {

    @Override
    public List<Flight> getFlights(String origin, String destination, String departureDate, int adults) {
        return List.of();
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
