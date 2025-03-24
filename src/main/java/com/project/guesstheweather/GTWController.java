package com.project.guesstheweather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("")
public class GTWController {
    private final GTWService gtwService;

    @Autowired
    public GTWController(GTWService gtwService) {
        this.gtwService = gtwService;
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

    @GetMapping("country")
    private List<Country> GetAllCountries() {
        return gtwService.countryRepository.findAll();
    }

    @GetMapping("country/{code}")
    private Country GetCountryByCountryCode(@PathVariable String code) {
        return gtwService.countryRepository.getCountryWithCode(code);
    }

    @GetMapping("countrylog")
    private List<CountryLog> GetAllCountryLogs() {
        return gtwService.countryLogRepository.findAll();
    }

    @GetMapping("countrylog/{id}")
    private CountryLog GetCountryLogByID(@PathVariable int id) {
        return gtwService.countryLogRepository.findById(id).get();
    }
}
