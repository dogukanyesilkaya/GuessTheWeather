package com.project.guesstheweather;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Country {
    @Id
    private String code;

    private String name;
    private String continent;
    private String temperature;

    public Country() {}

    public Country(String name,String code ,String continent, String temperature) {
        this.name = name;
        this.code = code;
        this.continent = continent;
        this.temperature = temperature;

    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }


    public String getContinent() {
        return continent;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

}
