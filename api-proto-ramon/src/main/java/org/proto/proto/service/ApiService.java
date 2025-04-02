package org.proto.proto.service;

import org.proto.proto.context.ApiContext;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    private final ApiContext apiContext;

    public ApiService(final ApiContext apiContext) {
        this.apiContext = apiContext;
    }

    public String getHotels(String city) {
        return apiContext.getHotels(city);
    }
}
