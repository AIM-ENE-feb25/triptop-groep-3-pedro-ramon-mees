package org.proto.proto.context;

import org.proto.proto.repository.ApiRepository;
import org.proto.proto.repository.CacheRepository;
import org.proto.proto.strategy.ApiStrategy;
import org.springframework.stereotype.Component;

@Component
public class ApiContext {
    private ApiStrategy apiStrategy;

    private final CacheRepository cacheRepository;
    private final ApiRepository apiRepository;

    public ApiContext(final CacheRepository cacheRepository, final ApiRepository apiRepository) {
        this.cacheRepository = cacheRepository;
        this.apiRepository = apiRepository;
    }

    public String getHotels(String city) {
        this.setStrategy(apiRepository);
        return apiStrategy.getHotels(city);
    }

    private void setStrategy(ApiStrategy apiStrategy) {
        this.apiStrategy = apiStrategy;
    }
}
