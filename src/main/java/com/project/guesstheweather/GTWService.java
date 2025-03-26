package com.project.guesstheweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class GTWService {

    public final CountryLogRepository countryLogRepository;
    public final CountryRepository countryRepository;

    @Autowired
    public GTWService(CountryLogRepository countryLogRepository, CountryRepository countryRepository, CountryRepository countryRepository1) {
        this.countryLogRepository = countryLogRepository;
        this.countryRepository = countryRepository1;
    }

    public String GetRandomTemperature() {
        Country randomCountry = countryRepository.getRandomCountry();
        return randomCountry.getTemperature();
    }


}
