package ese.triptop.prototype.adapter;

import ese.triptop.prototype.adapter.contracts.IFlightAdapter;
import ese.triptop.prototype.domain.Flight;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import kong.unirest.core.json.JSONObject;
import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingComFlightAdapter implements IFlightAdapter {


    private static final String BOOKING_URL = "https://flight-search-offer.wiremockapi.cloud/shopping/flight-offers";

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

                return flightMapper(new JSONObject(response.getBody().toString()));
            } else {
                throw new RuntimeException("[Facade] Flight search failed: " + response.getStatus() + " - " + response.getBody());
            }
        } catch (UnirestException e) {
            throw new RuntimeException("[Facade] Error during flight search: " + e.getMessage());
        }
    }

    @Override
    public boolean isAvailable() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(BOOKING_URL)
                    .queryString("originLocationCode", "AMS")
                    .queryString("destinationLocationCode", "LHR")
                    .queryString("departureDate", "2024-02-01Z")
                    .queryString("adults", 2)
                    .asJson();

            return response.getStatus() >= 200 && response.getStatus() < 300;
        } catch (UnirestException e) {
            System.err.println("[Adapter] Availability check failed: " + e.getMessage());
            return false;
        }
    }

    private List<Flight> flightMapper(JSONObject jsonObj) {
        List<Flight> flights = new ArrayList<>();
        jsonObj.getJSONArray("data").forEach(item -> {
            JSONObject flightOffer = (JSONObject) item;

            // Extract price information
            JSONObject priceObj = flightOffer.getJSONObject("price");
            double price = Double.parseDouble(priceObj.getString("grandTotal"));

            // Extract itinerary information - using first itinerary and first segment
            JSONObject itinerary = flightOffer.getJSONArray("itineraries").getJSONObject(0);
            JSONObject segment = itinerary.getJSONArray("segments").getJSONObject(0);

            // Extract departure and arrival information
            JSONObject departure = segment.getJSONObject("departure");
            JSONObject arrival = segment.getJSONObject("arrival");

            String originCode = departure.getString("iataCode");
            String destinationCode = arrival.getString("iataCode");
            String departureTime = departure.getString("at");
            String arrivalTime = arrival.getString("at");

            Flight flight = new Flight(
                    originCode,
                    destinationCode,
                    departureTime,
                    arrivalTime,
                    price
            );
            flights.add(flight);
        });
        return flights;
    }
}
