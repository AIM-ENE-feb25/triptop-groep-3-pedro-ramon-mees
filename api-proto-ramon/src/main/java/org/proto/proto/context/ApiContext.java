package org.proto.proto.context;

import org.proto.proto.repository.ApiRepository;
import org.proto.proto.repository.CacheRepository;
import org.proto.proto.strategy.ApiStrategy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ApiContext {
    private ApiStrategy apiStrategy;

    public ApiContext() {}

    public String getHotels(String city) {
        this.setStrategy(new ApiRepository());
        return apiStrategy.getHotels(city);
    }

    private void setStrategy(ApiStrategy apiStrategy) {
        this.apiStrategy = apiStrategy;
    }
}
