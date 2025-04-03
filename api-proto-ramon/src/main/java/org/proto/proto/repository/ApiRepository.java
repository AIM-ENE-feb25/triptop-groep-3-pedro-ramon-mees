package org.proto.proto.repository;

import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import kong.unirest.core.json.JSONArray;
import kong.unirest.core.json.JSONObject;
import org.proto.proto.strategy.ApiStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ApiRepository implements ApiStrategy {

    private final JdbcTemplate jdbcTemplate;

    private final String Host = "booking-com15.p.rapidapi.com";
    private final String Key = "47aa077e79msh3aa641c74b24ecep1c9291jsned53c1e5db74";

    public ApiRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isAvailable() {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("x-rapidapi-host", Host);
        headers.put("x-rapidapi-key", Key);

        return Unirest.get("https://" + Host + "/api/v1/test")
                .headers(headers)
                .asJson()
                .getBody()
                .getObject().
                getBoolean("status");
    }

    private void writeToCache(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            try {
                String cc1 = jsonObject.getString("cc1");
                String cityName = jsonObject.getString("city_name");
                String cityUfi = jsonObject.getString("city_ufi");
                String country = jsonObject.getString("country");
                String destId = jsonObject.getString("dest_id");
                String destType = jsonObject.getString("dest_type");
                int hotels = jsonObject.getInt("hotels");
                String imageUrl = jsonObject.getString("image_url");
                String label = jsonObject.getString("label");
                double latitude = jsonObject.getDouble("latitude");
                String lc = jsonObject.getString("lc");
                double longitude = jsonObject.getDouble("longitude");
                String name = jsonObject.getString("name");
                int nrHotels = jsonObject.getInt("nr_hotels");
                String region = jsonObject.getString("region");
                String roundtrip = jsonObject.getString("roundtrip");
                String searchType = jsonObject.getString("search_type");
                String type = jsonObject.getString("type");

                jdbcTemplate.update("INSERT INTO hotel(cc1, city_name, city_ufi, country, dest_id, dest_type, hotels, image_url, label, latitude, lc, longitude, name, nr_hotels, region, roundtrip, search_type, type) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                        cc1, cityName, cityUfi, country, destId, destType, hotels, imageUrl, label, latitude, lc, longitude, name, nrHotels, region, roundtrip, searchType, type);
            } catch (Exception _) {
            }
        }
    }

    public String getHotels(String city) {
        Map<String, String> headers = new HashMap<>();
        headers.put("accept", "application/json");
        headers.put("x-rapidapi-host", Host);
        headers.put("x-rapidapi-key", Key);

        JsonNode json = Unirest.get("https://" + Host + "/api/v1/hotels/searchDestination?query=" + city)
                .headers(headers)
                .asJson()
                .getBody();

        writeToCache(json.getObject().getJSONArray("data"));

        return json.toString();
    }
}
