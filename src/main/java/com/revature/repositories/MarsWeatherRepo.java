package com.revature.repositories;

import com.revature.models.MarsWeather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MarsWeatherRepo  extends CrudRepository<MarsWeather,Integer> {

    List<MarsWeather> findAll();

    void delete(final MarsWeather marsw);

    Object save(final MarsWeather marsw);

}
