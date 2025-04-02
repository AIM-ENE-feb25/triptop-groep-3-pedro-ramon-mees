package ese.triptop.prototype.adapter.contracts;

import ese.triptop.prototype.domain.Flight;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.util.List;

@Component
public class BookingFlightAdapter implements IFlightAdapter{

    private HttpClient httpClient;

    private final String API_URL = "https://booking-com15.p.rapidapi.com/api/v1/flights/";

    public BookingFlightAdapter() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public List<Flight> getFlights(String origin, String destination, String departureDate, int adults) {
        return List.of();
    }

    @Override
    public boolean isAvailable() {
        
        return false;
    }
}
