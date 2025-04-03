package ese.triptop.prototype.adapter;

import ese.triptop.prototype.adapter.contracts.IFlightAdapter;
import ese.triptop.prototype.domain.Flight;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;
import kong.unirest.core.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkyscannerFlightAdapter implements IFlightAdapter {

    private static final String SKYSCANNER_URL = "https://skyscanner89.p.rapidapi.com/flights/one-way/list";
    private static final String SKYSCANNER_KEY = "c0a58aedd7msh28a8b060e8b4ce9p1df17cjsn339e2aef0ac1";


    @Override
    public List<Flight> getFlights(String origin, String destination, String departureDate, int adults) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(SKYSCANNER_URL)
                    .header("x-rapidapi-host", "skyscanner89.p.rapidapi.com")
                    .header("x-rapidapi-key", SKYSCANNER_KEY)

                    .queryString("date", "2025-05-01")
                    .queryString("origin", "AMS")
                    .queryString("originId", "95565044")
                    .queryString("destination", "LHR")
                    .queryString("destinationId", "27544008")
                    .queryString("adults", 2)
                    .asJson();

            return flightMapper(new JSONObject(response.getBody().toString()));
        } catch (UnirestException e) {
            throw new RuntimeException("[Adapter] Availability check failed: " + e.getMessage());
        }
    }

    @Override
    public boolean isAvailable() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(SKYSCANNER_URL)
                    .header("x-rapidapi-host", "skyscanner89.p.rapidapi.com")
                    .header("x-rapidapi-key", SKYSCANNER_KEY)

                    .queryString("date", "2025-05-01")
                    .queryString("origin", "AMS")
                    .queryString("originId", "95565044")
                    .queryString("destination", "LHR")
                    .queryString("destinationId", "27544008")
                    .queryString("adults", 2)
                    .asJson();

            return response.getStatus() >= 200 && response.getStatus() < 300;
        } catch (UnirestException e) {
            System.err.println("[Adapter] Availability check failed: " + e.getMessage());
            return false;
        }
    }

    private List<Flight> flightMapper(JSONObject responseObj) {
        List<Flight> flights = new ArrayList<>();

        try {
            // Check if response contains the expected structure
            if (!responseObj.has("data") || !responseObj.getJSONObject("data").has("itineraries")) {
                System.err.println("Response doesn't contain expected flight data structure");
                return flights;
            }

            // Access the itineraries section
            JSONObject itineraries = responseObj.getJSONObject("data").getJSONObject("itineraries");

            // Check if itineraries contains buckets
            if (!itineraries.has("buckets")) {
                System.err.println("No flight buckets found in response");
                return flights;
            }

            // Process each bucket (Best, Cheapest, etc.)
            itineraries.getJSONArray("buckets").forEach(bucketObj -> {
                JSONObject bucket = (JSONObject) bucketObj;

                // Process each flight item in the bucket
                if (bucket.has("items")) {
                    bucket.getJSONArray("items").forEach(itemObj -> {
                        JSONObject item = (JSONObject) itemObj;

                        // Extract price
                        double price = item.getJSONObject("price").getDouble("raw");

                        // Extract leg information (first leg only for simplicity)
                        JSONObject leg = item.getJSONArray("legs").getJSONObject(0);

                        // Extract origin and destination
                        JSONObject origin = leg.getJSONObject("origin");

                        String departureAirport = origin.getString("displayCode");
                        Integer arrivalAirport = leg.getInt("durationInMinutes");
                        String departureTime = leg.getString("departure");
                        String arrivalTime = leg.getString("arrival");

                        Flight flight = new Flight(
                                departureAirport,
                                arrivalAirport,
                                departureTime,
                                arrivalTime,
                                price
                        );
                        flights.add(flight);
                    });
                }
            });
        } catch (Exception e) {
            System.err.println("Error parsing Skyscanner flight data: " + e.getMessage());
            e.printStackTrace();
        }

        return flights;
    }
}
