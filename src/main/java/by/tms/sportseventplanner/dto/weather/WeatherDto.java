package by.tms.sportseventplanner.dto.weather;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class WeatherDto {

    private long id;

    private LocalDate date;

    private String city;

    private float temp;

    private int pressure;

    private int humidity;

    private float windSpeed;

    private int visibility;

    private String description;
}
