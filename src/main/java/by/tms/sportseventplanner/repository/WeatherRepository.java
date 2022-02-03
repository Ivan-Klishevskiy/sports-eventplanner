package by.tms.sportseventplanner.repository;

import by.tms.sportseventplanner.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather,Long> {

    Optional<Weather> findByDateAndCity(LocalDate date, String city);
}
