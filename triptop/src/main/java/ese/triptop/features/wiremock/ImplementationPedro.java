package ese.triptop.features.wiremock;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import kong.unirest.core.json.JSONObject;
import kong.unirest.core.UnirestException;

public class ImplementationPedro {

    private final String BASE_URL = "https://triptop-identity.wiremockapi.cloud";

    private static final String FLIGHT_OFFERS_URL = "https://kk039.wiremockapi.cloud/shopping/flight-offers";

    protected final String username1 = "edevries";
    protected final String username2 = "fvleeuwen";
    protected final String username3 = "mvdlinden";
    protected final String password1 = "3g2Rw9sT1x";
    protected final String password2 = "0qW3rE5t7y";
    protected final String password3 = "1xZ3cV5b7n";


    public void run(){
        System.out.println("--------------------~~**&&( Pedro implementation )&&**~~----------------------");
        String token = logIn(username1, password1);
        if (token != null) {
            checkAccess(token, username1);
        }
        token = logIn(username2, password2);
        if (token != null) {
            checkAccess(token, username2);
        }
        token = logIn(username3, password3);
        if (token != null) {
            checkAccess(token, username3);
        }
    }

    public String logIn(String username, String password) {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post(BASE_URL + "/login")
                    .header("Content-Type", "application/json")
                    .body("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}")
                    .asJson();
        } catch (UnirestException e) {
            System.out.println("Pedro implementation Error------------------------------------------");
            System.out.println(e.getMessage());
        }
        if (response.getStatus() == 201 || response.getStatus() == 200) {
            JSONObject responseBody = response.getBody().getObject();
            String token = responseBody.getJSONObject("token").getString("value");
            System.out.println("Login for " + username + " successful! Token: " + token);
            return token;
        } else {
            System.out.println("Login failed: " + response.getBody());
            return null;
        }
    }
    public void checkAccess(String token, String username) {
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL + "/checkAppAccess?token=" + token)
                .header("Content-Type", "application/json")
                .body("{\"username\":\"" + username + "\",\"application\":\"triptop\"}")
                .asJson();
        if (response.getStatus() == 200 || response.getStatus() == 201) {
            String rol = response.getBody().getObject().getString("role");
            System.out.println("Gebruiker: "+ username +" Rol: " + rol);
        } else {
            System.out.println("Access check failed: " + response.getBody());
        }
    }


    public String getFlightOffers(String origin, String destination, String departureDate, int adults) {
        try {
            HttpResponse<JsonNode> response = Unirest.get(FLIGHT_OFFERS_URL)
                    .queryString("originLocationCode", origin)
                    .queryString("destinationLocationCode", destination)
                    .queryString("departureDate", departureDate)
                    .queryString("adults", adults)
                    .asJson();

            if (response.getStatus() == 200) {
                System.out.println("Flight Offers: " + response.getBody().toString());
                return response.getBody().toString();
            } else {
                System.out.println("Failed to get flight offers: " + response.getBody());
                return null;
            }

        } catch (UnirestException e) {
            System.out.println("Error fetching flight offers: " + e.getMessage());
            return null;
        }
    }
    public String getBooking(){
        HttpResponse<String> response = Unirest.get("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotelsByCoordinates?latitude=19.24232736426361&longitude=72.85841985686734&arrival_date=2025-03-31&departure_date=2025-04-15&adults=1&room_qty=1&units=metric&page_number=1&temperature_unit=c&languagecode=en-us&currency_code=EUR&location=NL")
                .header("x-rapidapi-key", "01fcc8ef7dmsh9371396917f2b4fp187e1ejsn778fecdf4de9")
                .header("x-rapidapi-host", "booking-com15.p.rapidapi.com")
                .asString();
        System.out.println("booking: "+ response.getBody());
        return response.getBody().toString();
    }
    public String getTripAdvisor(){
        HttpResponse<String> response = Unirest.get("https://tripadvisor16.p.rapidapi.com/api/v1/restaurant/searchRestaurants?locationId=304554")
                .header("x-rapidapi-key", "01fcc8ef7dmsh9371396917f2b4fp187e1ejsn778fecdf4de9")
                .header("x-rapidapi-host", "tripadvisor16.p.rapidapi.com")
                .asString();
        System.out.println("TripAdvisor: "+ response.getBody());
        return response.getBody().toString();
    }

}