package by.tms.sportseventplanner.rest;

import by.tms.sportseventplanner.dto.organization.OrganizationDto;
import by.tms.sportseventplanner.dto.weather.WeatherDto;
import by.tms.sportseventplanner.entity.Weather;
import by.tms.sportseventplanner.repository.EventRepository;
import by.tms.sportseventplanner.service.WeatherService;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper mapper;


    @GetMapping("/current/{city}")
    public ResponseEntity<?> getCurrentWeather(@PathVariable String city){
        Weather currentWeather = weatherService.getCurrentWeather(city);
        return new ResponseEntity<>(mapper.map(currentWeather, WeatherDto.class), HttpStatus.OK);
    }

    @GetMapping("/eventWeather/{eventId}")
    public ResponseEntity<?> getEventWeather(@PathVariable long eventId){
        Weather eventWeather = weatherService.getEventWeather(eventId);
        return new ResponseEntity<>(mapper.map(eventWeather, WeatherDto.class), HttpStatus.OK);
    }
}
