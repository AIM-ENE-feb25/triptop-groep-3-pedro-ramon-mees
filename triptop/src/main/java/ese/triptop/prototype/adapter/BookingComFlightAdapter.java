package ese.triptop.prototype.adapter;

import ese.triptop.prototype.adapter.contracts.IFlightAdapter;
import ese.triptop.prototype.domain.Flight;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.util.List;

@Component
public class BookingComFlightAdapter implements IFlightAdapter {

    private HttpClient httpClient;

    private static final String BOOKING_URL = "https://flight-search-offer.wiremockapi.cloud/shopping/flight-offers";

    public BookingComFlightAdapter() {
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public List<Flight> getFlights(String origin, String destination, String departureDate, int adults) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(BOOKING_URL)
                    .queryString("originLocationCode", origin)
                    .queryString("destinationLocationCode", destination)
                    .queryString("departureDate", departureDate)
                    .queryString("adults", adults)
                    .asJson();

            if (response.getStatus() == 200) {
                System.out.println("[Facade] Flight search successful.");
                System.out.println(response.getBody());


                return null;
            } else {
                System.err.println("[Facade] Flight search failed: " + response.getStatus() + " - " + response.getBody());
                return null;
            }
        } catch (UnirestException e) {
            System.err.println("[Facade] Error during flight search: " + e.getMessage());
            return null;
        }

//        return List.of();
    }

    @Override
    public boolean isAvailable() {

        return false;
    }

//    private List<Flight> flightMapper(JsonNode jsonNode) {
//        List<Flight> flights = new ArrayList<>();
//        jsonNode.getArray().forEach(item -> {
//            Flight flight = new Flight(
//                    ((JSONObject) item).getString("origin"),
//                    item.getString("destination"),
//                    item.getString("departureDate"),
//                    item.getString("arrivalDate"),
//                    item.getDouble("price"),
//                    item.getString("currency")
//            );
//            flights.add(flight);
//        });
//        return flights;
//    }
}
