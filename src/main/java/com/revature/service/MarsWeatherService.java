package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MarsWeatherService {
    private final MarsWeatherRepo marswr;

    @Autowired
    public MarsWeatherService(MarsWeatherRepo marswr) {
        this.marswr = marswr;
    }


    private MarsWeather compareSolDates(final MarsWeather marsw_db) {
        final MarsWeather new_weather = getWeatherFromApi();
        if(new_weather.getSol() > marsw_db.getSol()) {
            marswr.update(new_weather);
        }
        return new_weather;
    }

    private MarsWeather compareWeatherDates(final MarsWeather marsw_db) {
        final LocalDate today = LocalDate.now();
        if(today.isEqual(marsw_db.getStamp())) {
            return marsw_db;
        }
        return compareSolDates(marsw_db)
    }

    public MArsWeather getWeather() {
        return compareWeatherDates(marswr.getWeather())
    }

}
