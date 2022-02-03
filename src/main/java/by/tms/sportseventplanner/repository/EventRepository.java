package by.tms.sportseventplanner.repository;

import by.tms.sportseventplanner.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<List<Event>> findAllByCreatorOrganization(String creatorOrganization);

    Optional<List<Event>> findAllByDate(LocalDate date);

    Optional<List<Event>> findAllByDateAndWeatherId(LocalDate localDate, Long weatherId);

    void deleteAllByCreatorOrganization(String CreatorOrganizationName);
}
