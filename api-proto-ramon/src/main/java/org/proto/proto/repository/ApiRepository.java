package org.proto.proto.repository;

import org.proto.proto.strategy.ApiStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApiRepository implements ApiStrategy {

    private final JdbcTemplate jdbcTemplate;

    public ApiRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String get() {
        return jdbcTemplate.queryForObject("SELECT bar FROM foo", String.class);
    }

}
