package ba.edu.ibu.eventbooking.rest.controllers;

import ba.edu.ibu.eventbooking.model.Event;
import ba.edu.ibu.eventbooking.rest.dto.EventDTO;
import ba.edu.ibu.eventbooking.rest.dto.EventRequestDTO;
import ba.edu.ibu.eventbooking.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<EventDTO>> getEvents() {
        return ResponseEntity.ok(eventService.getEvents());
    }

    @RequestMapping(method = RequestMethod.POST, path = "/createEvent")
    public ResponseEntity<EventDTO> register(@RequestBody EventRequestDTO event) {
        return ResponseEntity.ok(eventService.createEvent(event));
    }
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable int id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<EventDTO> updateEvent(@PathVariable int id, @RequestBody EventRequestDTO event) {
        return ResponseEntity.ok(eventService.updateEvent(id, event));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public List<Event> findAll() {
        return eventService.findAll();
    }

    @GetMapping("/{eventId}")
    public Event findById(@PathVariable int eventId) {
        return eventService.findById(eventId);
    }
}
