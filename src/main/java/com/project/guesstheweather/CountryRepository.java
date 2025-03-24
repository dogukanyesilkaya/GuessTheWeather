package com.project.guesstheweather;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value = "SELECT * FROM country WHERE code = :code", nativeQuery = true)
    Country getCountryWithCode(@Param("code") String code);
}
