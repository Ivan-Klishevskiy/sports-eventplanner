package by.tms.sportseventplanner.service;

import by.tms.sportseventplanner.entity.Event;
import by.tms.sportseventplanner.entity.Weather;
import by.tms.sportseventplanner.repository.EventRepository;
import by.tms.sportseventplanner.repository.WeatherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private EventRepository eventRepository;

    @Value("${application.weatherUrl}")
    private String weatherUrl;

    public Weather getCurrentWeather(String city) {
        Optional<Weather> byDateAndCity = weatherRepository.findByDateAndCity(LocalDate.now(), city);
        Weather currentWeather = byDateAndCity.orElseGet(() -> getCurrentWeatherFromApi(city));
        return weatherRepository.save(currentWeather);
    }

    @Scheduled(initialDelay = 1000L, fixedDelayString = "PT10M")
    private void setWeatherForEvent() {
        Optional<List<Event>> optionalEvents = eventRepository.findAllByDateAndWeather(LocalDate.now(), null);
        if (optionalEvents.isPresent()) {
            optionalEvents.get().forEach(event -> event.setWeather(getCurrentWeather(event.getVenue())));
            eventRepository.saveAll(optionalEvents.get());
        }
    }

    private Weather getCurrentWeatherFromApi(String city) {
        ResponseEntity<String> response = restTemplate.getForEntity(weatherUrl, String.class, Map.of("city", city));
        ObjectMapper mapper = new ObjectMapper();
        Weather weatherByApi = new Weather();
        try {
            JsonNode root = mapper.readTree(response.getBody());
            weatherByApi.setCity(root.findPath("name").asText());
            weatherByApi.setTemp(root.findPath("temp").floatValue());
            weatherByApi.setPressure(root.findPath("pressure").asInt());
            weatherByApi.setHumidity(root.findPath("humidity").asInt());
            weatherByApi.setWindSpeed(root.findParent("wind").findPath("speed").floatValue());
            weatherByApi.setVisibility(root.findPath("visibility").asInt());
            weatherByApi.setDescription(root.findParent("weather").findPath("description").asText());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage()); //TODO записывать в log
        }
        return weatherByApi;
    }
}
