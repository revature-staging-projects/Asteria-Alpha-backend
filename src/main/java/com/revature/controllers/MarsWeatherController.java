package com.revature.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mars")
public class MarsWeatherController {

    private final MarsWeatherService marsws;

    @Autowired
    public MarsWeatherController(MarsWeatherService marsws) {
        this.marsws = marsws;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    MarsWeather getMarsWeather() {
        return marsws.getWeather();
    }

}
