package com.revature.service;

import com.revature.models.MarsWeather;
import com.revature.repositories.MarsWeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;

@Service
public class MarsWeatherService {
    private final MarsWeatherRepo marswr;

    @Autowired
    public MarsWeatherService(MarsWeatherRepo marswr) {
        this.marswr = marswr;
    }



    private MarsWeather getWeatherFromApi() {
        String url = "https://api.nasa.gov/insight_weather/?api_key=qhkaWwIpbcmhbAR6z4MRbd4fHuNkjd0jLog3eHez&feedtype=json&ver=1.0";
        return WebClient.create(url)
               .get()
               .retrieve()
               .bodyToMono(MarsWeather.class)
               .blockOptional().orElseThrow(RuntimeException::new);
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
        return compareSolDates(marsw_db);
    }

    public MarsWeather getWeather() {
        return compareWeatherDates(marswr.getWeather());
    }

}
