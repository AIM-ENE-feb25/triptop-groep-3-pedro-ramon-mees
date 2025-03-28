package org.proto.proto.service;

import org.proto.proto.context.ApiContext;
import org.proto.proto.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    private ApiContext apiContext;

    public ApiService(ApiContext apiContext) {
        this.apiContext = apiContext;
    }

    public String get() {

        return apiContext.get();
    }
}
