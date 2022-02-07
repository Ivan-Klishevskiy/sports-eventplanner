package by.tms.sportseventplanner.controller;

import by.tms.sportseventplanner.dto.weather.WeatherDto;
import by.tms.sportseventplanner.entity.Weather;
import by.tms.sportseventplanner.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import org.hibernate.validator.constraints.Length;
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
    @Operation(summary = "Getting the current weather for today")
    public ResponseEntity<?> getCurrentWeather(@PathVariable @Length(min = 1, max = 255) String city){
        Weather currentWeather = weatherService.getCurrentWeather(city);
        return new ResponseEntity<>(mapper.map(currentWeather, WeatherDto.class), HttpStatus.OK);
    }
}
