package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MarsWeatherRepo  extends JpaRepository<MarsWeather,Integer> {

    @Query(value = "SELECT * FROM Mars_weather",nativeQuery = true)
    MarsWeather getWeather();

}
