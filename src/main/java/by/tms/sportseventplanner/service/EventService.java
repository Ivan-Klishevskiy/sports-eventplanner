package by.tms.sportseventplanner.service;

import by.tms.sportseventplanner.entity.Event;
import by.tms.sportseventplanner.entity.Organization;
import by.tms.sportseventplanner.repository.CommentRepository;
import by.tms.sportseventplanner.repository.EventRepository;
import by.tms.sportseventplanner.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private CommentRepository commentRepository;

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
            throw new RuntimeException(String.format("Event with id %d is not exist!", id));
        }
    }

    public Event findById(long id) {
        return eventRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Event id %d is not exist!", id)));
    }

    public Page<Event> findAllByCreatorName(String creatorName, Pageable page) {
        return eventRepository
                .findAllByCreatorOrganization(creatorName,page)
                .orElseThrow(() -> new RuntimeException(String.format("Event by %s is not exist!", creatorName)));
    }

    public Page<Event> findByDate(String date,Pageable page) {
        return eventRepository
                .findAllByDate(LocalDate.parse(date),page)
                .orElseThrow(() -> new RuntimeException(String.format("Event with date %s is not exist!", date)));
    }

    public Event addParticipant(String participantName, long eventId) {
        Event event = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new RuntimeException(String.format("Event id %d is not exist!", eventId)));

        Organization participant = organizationRepository
                .findByName(participantName)
                .orElseThrow(() -> new RuntimeException(String.format("Organization %s is not exist!", participantName)));

        if (event.getParticipants().contains(participant.getName())) {
            throw new RuntimeException(String.format("Participant %s is already exist!", participant.getName()));
        } else {
            event.getParticipants().add(participant.getName());
            eventRepository.save(event);
        }
        return event;
    }

    public Event removeParticipant(String participantName, long eventId){
        Event event = eventRepository
                .findById(eventId)
                .orElseThrow(() -> new RuntimeException(String.format("Event id %d is not exist!", eventId)));

        Organization participant = organizationRepository
                .findByName(participantName)
                .orElseThrow(() -> new RuntimeException(String.format("Organization %s is not exist!", participantName)));

        if (event.getParticipants().contains(participant.getName())) {
            event.getParticipants().remove(participant.getName());
            eventRepository.save(event);
        } else {
            throw new RuntimeException(String.format("Participant %s is not exist!", participant.getName()));
        }
        return event;
    }

    @Transactional
    public Event delete(long id) {
        Event found = eventRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Event with id %d is not exist!", id)));
        eventRepository.delete(found);
        commentRepository.deleteAllByEventId(found.getId());
        return found;
    }


}
