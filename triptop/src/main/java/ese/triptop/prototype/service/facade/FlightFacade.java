package ese.triptop.prototype.service.facade;

import ese.triptop.prototype.adapter.contracts.IFlightAdapter;
import ese.triptop.prototype.factory.FlightAdapterFactory;
import ese.triptop.prototype.service.strategy.contracts.IFlightSearchStrategy;
import ese.triptop.prototype.domain.Flight;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class FlightFacade {
    private final FlightAdapterFactory adapterFactory;
    private IFlightSearchStrategy currentSearchStrategy;

    @Autowired
    public FlightFacade(FlightAdapterFactory adapterFactory,
                        @Qualifier("cheapestFlightStrategy") IFlightSearchStrategy defaultStrategy) { 
        this.adapterFactory = adapterFactory;
        this.currentSearchStrategy = defaultStrategy;
        System.out.println("FlightFacade initialized with strategy: " + defaultStrategy.getClass().getSimpleName());
    }
    public void setSearchStrategy(IFlightSearchStrategy searchStrategy) {
        this.currentSearchStrategy = searchStrategy;
        System.out.println("FlightFacade search strategy changed to: " + searchStrategy.getClass().getSimpleName());
    }
    public Flight findBestFlight(String origin, String destination, String departureDate, int adults) {
        System.out.println("FlightFacade: Finding best flight from " + origin + " to " + destination);

        List<IFlightAdapter> availableAdapters = adapterFactory.getFlightAdapters();
        System.out.println("FlightFacade: Using " + availableAdapters.size() + " available adapters.");


        List<Flight> allFlights = availableAdapters.stream()
                .flatMap(adapter -> {
                    System.out.println("FlightFacade: Querying adapter: " + adapter.getClass().getSimpleName());
                    List<Flight> flights = adapter.getFlights(origin, destination, departureDate, adults);
                    return flights != null ? flights.stream() : Stream.empty();
                 })
                .collect(Collectors.toList());

        System.out.println("FlightFacade: Aggregated " + allFlights.size() + " flight options.");

        if (allFlights.isEmpty()) {
            return null; 
        }

        Flight bestFlight = currentSearchStrategy.findBestFlight(allFlights);

        if (bestFlight != null) {
             System.out.println("FlightFacade found the best flight! from adapter: " + bestFlight.getAdapterName());
        } else {
             System.out.println("FlightFacade Strategy could not determine a best flight.");
        }

        return bestFlight;
    }
}
