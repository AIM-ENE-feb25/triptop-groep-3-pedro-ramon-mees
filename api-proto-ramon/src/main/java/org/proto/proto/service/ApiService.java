package org.proto.proto.service;

import org.proto.proto.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {
    private ApiRepository apiRepository;

    @Autowired
    public ApiService(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public String get() {
        return apiRepository.get();
    }
}
