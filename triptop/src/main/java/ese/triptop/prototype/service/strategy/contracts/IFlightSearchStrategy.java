package ese.triptop.prototype.service.strategy.contracts;
import ese.triptop.prototype.domain.Flight;
import java.util.List;

public interface IFlightSearchStrategy {
    Flight findBestFlight(List<Flight> availableFlights);
}