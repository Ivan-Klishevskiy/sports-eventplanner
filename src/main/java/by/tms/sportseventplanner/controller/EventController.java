package by.tms.sportseventplanner.controller;

import by.tms.sportseventplanner.dto.event.RequestEventDto;
import by.tms.sportseventplanner.dto.event.SentEventDto;
import by.tms.sportseventplanner.entity.Event;
import by.tms.sportseventplanner.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import org.hibernate.validator.constraints.Length;
import org.intellij.lang.annotations.RegExp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/create")
    @Operation(summary = "Create new event")
    public ResponseEntity<?> create(@Valid @RequestBody RequestEventDto eventDto) {
        Event created = eventService.save(mapper.map(eventDto, Event.class));
        return new ResponseEntity<>(mapper.map(created, SentEventDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updating an existing event")
    public ResponseEntity<?> update(@PathVariable long id,
                                    @Valid @RequestBody RequestEventDto requestEventDto) {
        Event updated = eventService.update(mapper.map(requestEventDto, Event.class), id);
        return new ResponseEntity<>(mapper.map(updated, SentEventDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleting an event")
    public ResponseEntity<?> delete(@PathVariable long id) {
        Event deleted = eventService.delete(id);
        return new ResponseEntity<>(mapper.map(deleted, SentEventDto.class), HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    @Operation(summary = "Find event by organization name")
    public ResponseEntity<?> getEventsByCreatorName(@PathVariable @Length(min = 1, max = 255) String name,
                                                    @RequestParam Optional<Integer> page,
                                                    @RequestParam Optional<Integer> size,
                                                    @RequestParam Optional<String> sortBy) {
        List<SentEventDto> sentEventDtoList = eventService.findAllByCreatorName(name, (PageRequest.of(page.orElse(0),
                        size.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("date"))))
                .stream()
                .map(record -> mapper.map(record, SentEventDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(sentEventDtoList, HttpStatus.OK);
    }

    @GetMapping("/getByDate")
    @Operation(summary = "Find event by date")
    public ResponseEntity<?> getEventsByDate(@RequestParam String date,
                                             @RequestParam Optional<Integer> page,
                                             @RequestParam Optional<Integer> size,
                                             @RequestParam Optional<String> sortBy) {
        List<SentEventDto> sentEventDtoList = eventService
                .findByDate(date,(PageRequest.of(page.orElse(0),
                        size.orElse(5),
                        Sort.Direction.ASC,
                        sortBy.orElse("id"))))
                .stream()
                .map(record -> mapper.map(record, SentEventDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(sentEventDtoList, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    @Operation(summary = "Find event by eventId")
    public ResponseEntity<?> getEventById(@PathVariable long id) {
        Event found = eventService.findById(id);
        return new ResponseEntity<>(mapper.map(found, SentEventDto.class), HttpStatus.OK);
    }

    @PostMapping("/takePart/{eventId}")
    @Operation(summary = "Adding to the event participants")
    public ResponseEntity<?> addParticipant(@PathVariable long eventId, @RequestParam String participant) {
        Event event = eventService.addParticipant(participant, eventId);
        return new ResponseEntity<>(mapper.map(event, SentEventDto.class), HttpStatus.OK);
    }

    @PostMapping("/notPart/{eventId}")
    @Operation(summary = "Withdraw from the event participants")
    public ResponseEntity<?> removeParticipant(@PathVariable long eventId, @RequestParam String participant) {
        Event event = eventService.removeParticipant(participant, eventId);
        return new ResponseEntity<>(mapper.map(event, SentEventDto.class), HttpStatus.OK);
    }
}
