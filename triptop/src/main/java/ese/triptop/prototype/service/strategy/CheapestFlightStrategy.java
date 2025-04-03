package ese.triptop.prototype.service.strategy;

import ese.triptop.prototype.domain.Flight;
import ese.triptop.prototype.service.strategy.contracts.IFlightSearchStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Comparator;

@Component("cheapestFlightStrategy")
public class CheapestFlightStrategy implements IFlightSearchStrategy {
    @Override
    public Flight findBestFlight(List<Flight> availableFlights) {
        System.out.println("Strategy: Applying Cheapest Flight Strategy.");
        return availableFlights.stream()
                .min(Comparator.comparingDouble(Flight::getPrice))
                .orElse(null);
    }
}
