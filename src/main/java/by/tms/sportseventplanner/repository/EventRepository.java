package by.tms.sportseventplanner.repository;

import by.tms.sportseventplanner.entity.Event;
import by.tms.sportseventplanner.entity.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Page<Event>> findAllByCreatorOrganization(String creatorOrganization, Pageable page);

    Optional<Page<Event>> findAllByDate(LocalDate date,Pageable page);

    Optional<List<Event>> findAllByDateAndWeather(LocalDate localDate, Weather weather);

    void deleteAllByCreatorOrganization(String CreatorOrganizationName);
}
