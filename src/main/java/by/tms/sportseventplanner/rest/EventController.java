package by.tms.sportseventplanner.rest;

import by.tms.sportseventplanner.dto.event.RequestEventDto;
import by.tms.sportseventplanner.dto.event.SentEventDto;
import by.tms.sportseventplanner.entity.Event;
import by.tms.sportseventplanner.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

        @Autowired
        private ModelMapper mapper;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody RequestEventDto eventDto) {
        Event created = eventService.save(mapper.map(eventDto, Event.class));
        return new ResponseEntity<>(mapper.map(created, SentEventDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody RequestEventDto requestEventDto) {
        Event updated = eventService.update(mapper.map(requestEventDto, Event.class), id);
        return new ResponseEntity<>(mapper.map(updated, SentEventDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Event deleted = eventService.delete(id);
        return new ResponseEntity<>(mapper.map(deleted, SentEventDto.class), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getEventsByCreatorName(@PathVariable String name) {
        List<SentEventDto> sentEventDtoList = eventService.findAllByCreatorName(name)
                .stream()
                .map(record -> mapper.map(record, SentEventDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(sentEventDtoList, HttpStatus.OK);
    }

    @GetMapping("/getByDate")
    public ResponseEntity<?> getEventsByDate(@RequestParam String date) {
        List<SentEventDto> sentEventDtoList = eventService
                .findByDate(date)
                .stream()
                .map(record -> mapper.map(record, SentEventDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(sentEventDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable long id){
        Event found = eventService.findById(id);
        return new ResponseEntity<>(mapper.map(found, SentEventDto.class), HttpStatus.OK);
    }

    @PostMapping("/takePart/{eventId}")
    public ResponseEntity<?> addParticipant(@PathVariable long eventId, @RequestParam String participant){
        Event event = eventService.addParticipant(participant, eventId);
        return new ResponseEntity<>(mapper.map(event, SentEventDto.class), HttpStatus.OK);
    }

    @PostMapping("/notPart/{eventId}")
    public ResponseEntity<?> removeParticipant(@PathVariable long eventId, @RequestParam String participant){
        Event event = eventService.removeParticipant(participant, eventId);
        return new ResponseEntity<>(mapper.map(event, SentEventDto.class), HttpStatus.OK);
    }
}
