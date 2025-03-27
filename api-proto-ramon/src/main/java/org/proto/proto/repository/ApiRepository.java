package org.proto.proto.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ApiRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ApiRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String get() {
        return jdbcTemplate.queryForObject("SELECT bar FROM foo", String.class);
    }

}
