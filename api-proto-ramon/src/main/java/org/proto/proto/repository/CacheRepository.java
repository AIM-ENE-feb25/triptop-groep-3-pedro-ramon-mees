package org.proto.proto.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.proto.proto.strategy.ApiStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CacheRepository implements ApiStrategy {

    private final JdbcTemplate jdbcTemplate;

    public CacheRepository(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getHotels(String city) {
        String sql = "SELECT cc1, city_name, city_ufi, country, dest_id, dest_type, hotels, image_url, label, latitude, lc, longitude, name, nr_hotels, region, roundtrip, search_type, type FROM hotel";

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(rows);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return "[]";
        }
    }
}
