package by.tms.sportseventplanner.service;

import by.tms.sportseventplanner.entity.Event;
import by.tms.sportseventplanner.repository.EventRepository;
import by.tms.sportseventplanner.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    public Event save(Event event) {
        if (organizationRepository.existsByName(event.getCreatorOrganization())) {
            return eventRepository.save(event);
        } else {
            throw new RuntimeException(String.format("Organization %s is not exist!", event.getCreatorOrganization()));
        }
    }

    public Event update(Event event, long id) {
        if (eventRepository.existsById(id)) {
            return eventRepository.save(event);
        } else {
            throw new RuntimeException(String.format("Event with id %s is not exist!", id));
        }
    }

    public Event findById(long id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Event id %s is not exist!", id)));
    }

    public List<Event> findAllByCreatorName(String creatorName) {
        return eventRepository
                .findAllByCreatorOrganization(creatorName)
                .orElseThrow(() -> new RuntimeException(String.format("Event by %s is not exist!", creatorName)));
    }

    public List<Event> findByDate(String date) {
        return eventRepository
                .findAllByDate(LocalDate.parse(date))
                .orElseThrow(() -> new RuntimeException(String.format("Event with date %s is not exist!", date)));
    }

    public Event delete(long id) {
        Event found = eventRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Event with id %s is not exist!", id)));
        eventRepository.delete(found);
        return found;
    }
}
