package com.project.guesstheweather;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryLogRepository extends JpaRepository<CountryLog, Integer> {
}
