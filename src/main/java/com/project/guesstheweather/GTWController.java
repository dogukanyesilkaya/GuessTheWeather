package com.project.guesstheweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;


@RestController
@RequestMapping("")
public class GTWController {
    private final GTWService gtwService;
    private final CountryRepository countryRepository;

    @Autowired
    public GTWController(GTWService gtwService, CountryRepository countryRepository) {
        this.gtwService = gtwService;
        this.countryRepository = countryRepository;
    }

    @PostMapping("countrylog")
    private @ResponseBody void CreateCountryLog(@RequestParam("countryCode") String countryCode,@RequestParam("howCloseToTemperature") String howCloseToTemperature,
                                                @RequestParam("lostOnThisManyTimes") Integer lostOnThisManyTimes,@RequestParam("comment") String comment) {

        CountryLog countryLog = new CountryLog();
        countryLog.setCountryCode(countryCode);
        countryLog.setHowCloseToTemperature(howCloseToTemperature);
        countryLog.setLostOnThisManyTimes(lostOnThisManyTimes);
        countryLog.setComment(comment);
        gtwService.countryLogRepository.save(countryLog);
    }


//
//    @PostMapping("country/{id}")
//    private @ResponseBody void AddCountry(@PathVariable String id) {
//
//        Country country = new Country();
//        gtwService.countryRepository.save(country);
//    }

//    @GetMapping("/country")
//    private List<Country> GetAllCountries() {
//        return gtwService.countryRepository.findAll();
//    }

    @GetMapping("/country")
    private String GetCountryTemperatureByCountryCode(@RequestParam String code) {
        return gtwService.countryRepository.getCountryWithCode(code).getTemperature();
    }

    @GetMapping("/country-randomtemp")
    private String GetRandomTemperature() {
        return gtwService.GetRandomTemperature();
    }

    @GetMapping("countrylog")
    private List<CountryLog> GetAllCountryLogs() {
        return gtwService.countryLogRepository.findAll();
    }

    @GetMapping("countrylog/{id}")
    private CountryLog GetCountryLogByID(@PathVariable int id) {
        return gtwService.countryLogRepository.findById(id).get();
    }

    public int HowCloseWasGuess(int guess,int exactTemperature){
        return exactTemperature-guess;
    }
}
