package ese.triptop.prototype.service.strategy;
import ese.triptop.prototype.domain.Flight;
import ese.triptop.prototype.service.strategy.contracts.IFlightSearchStrategy;

import java.util.List;
import java.util.Comparator;

import org.springframework.stereotype.Component;

@Component("fastestFlightStrategy")
public class FastestFlightStrategy implements IFlightSearchStrategy {
    @Override
    public Flight findBestFlight(List<Flight> availableFlights) {
         System.out.println("Strategy: Applying Fastest Flight Strategy.");
         return availableFlights.stream()
                .min(Comparator.comparingInt(Flight::getFlightDuration))
                .orElse(null);
    }
}
