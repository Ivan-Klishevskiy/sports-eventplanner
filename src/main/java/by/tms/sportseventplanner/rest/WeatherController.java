package by.tms.sportseventplanner.rest;

import by.tms.sportseventplanner.dto.organization.OrganizationDto;
import by.tms.sportseventplanner.entity.Weather;
import by.tms.sportseventplanner.repository.EventRepository;
import by.tms.sportseventplanner.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;


    @GetMapping("/current/{city}")
    public ResponseEntity<?> getCurrentWeather(@PathVariable String city){
        Weather currentWeather = weatherService.getCurrentWeather(city);
        return new ResponseEntity<>(currentWeather, HttpStatus.OK);
    }

//    @GetMapping("/test")
//    public ResponseEntity<?> setWeather(){
//        weatherService.setWeatherForEvent();
//        return new ResponseEntity<>("setWeather", HttpStatus.OK);
//    }
}
