package org.proto.proto.repository;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.proto.proto.strategy.ApiStrategy;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ApiRepository implements ApiStrategy {

    private final String Host = "booking-com15.p.rapidapi.com";
    private final String Key = "47aa077e79msh3aa641c74b24ecep1c9291jsned53c1e5db74";

    public ApiRepository() {}

    public String getHotels(String city) {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("x-rapidapi-host", Host);
        headers.put("x-rapidapi-key", Key);

        String response = Unirest.get("https://"+Host+"/api/v1/hotels/searchDestination?query=" + city)
                .headers(headers)
                .asJson()
                .getBody()
                .toString();

        //Do caching here

        return response;
    }
}
