package com.project.guesstheweather;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CountryLog {
    @Id
    @GeneratedValue
    private Integer id;

    private String countryCode;
    private String howCloseToTemperature;
    private Integer lostOnThisManyTimes;
    private String comment;

    public CountryLog() {}

    public CountryLog(String countryCode, String howCloseToTemperature, Integer lostOnThisManyTimes, String comment) {
        this.countryCode = countryCode;
        this.howCloseToTemperature = howCloseToTemperature;
        this.lostOnThisManyTimes = lostOnThisManyTimes;
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getHowCloseToTemperature() {
        return howCloseToTemperature;
    }

    public void setHowCloseToTemperature(String howCloseToTemperature) {
        this.howCloseToTemperature = howCloseToTemperature;
    }

    public Integer getLostOnThisManyTimes() {
        return lostOnThisManyTimes;
    }

    public void setLostOnThisManyTimes(Integer lostOnThisManyTimes) {
        this.lostOnThisManyTimes = lostOnThisManyTimes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
