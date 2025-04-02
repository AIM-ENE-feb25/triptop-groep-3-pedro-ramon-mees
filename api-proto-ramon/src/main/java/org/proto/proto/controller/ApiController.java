package org.proto.proto.controller;

import org.proto.proto.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    private ApiService apiService;

    @Autowired
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("hotels")
    public ResponseEntity<String> hotels(String city){
        return new ResponseEntity<>(apiService.getHotels(city), HttpStatus.OK);
    }
}
