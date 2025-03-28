package org.proto.proto.repository;

import org.proto.proto.strategy.ApiStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CacheRepository implements ApiStrategy {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CacheRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String get() { return "TODO"; }
}
