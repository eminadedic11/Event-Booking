package ba.edu.ibu.eventbooking.core.service;


import ba.edu.ibu.eventbooking.core.exceptions.general.EventException;
import ba.edu.ibu.eventbooking.core.model.Event;
import ba.edu.ibu.eventbooking.core.repository.EventRepository;
import ba.edu.ibu.eventbooking.rest.dto.EventDTO;
import ba.edu.ibu.eventbooking.rest.dto.EventRequestDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class EventServiceTest {
    @MockBean
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Test
    public void shouldReturnListOfEventDTOs() {
        when(eventRepository.findAll()).thenReturn(List.of(new Event()));

        List<EventDTO> events = eventService.getEvents();

        assertEquals(1, events.size());
        verify(eventRepository, times(1)).findAll();
    }

    @Test
    public void shouldSaveEvent() {
        Event eventToSave = new Event();

        eventService.saveEvent(eventToSave);

        verify(eventRepository, times(1)).save(eventToSave);
    }

    @Test
    public void shouldCreateEvent() {
        EventRequestDTO eventRequestDTO = new EventRequestDTO();
        Event eventToSave = new Event();

        when(eventRepository.save(any(Event.class))).thenReturn(eventToSave);

        EventDTO createdEvent = eventService.createEvent(eventRequestDTO);

        assertEquals(eventToSave.getEventId(), createdEvent.getEventId());
        verify(eventRepository, times(1)).save(any(Event.class));
    }
    @Test
    public void testGetEventByIdEventNotFound() {
        int eventId = 1;

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThrows(EventException.class, () -> eventService.getEventById(eventId));

        verify(eventRepository, times(1)).findById(eventId);
    }
    @Test
    void testUpdateEvent() {
        int eventId = 1;
        EventRequestDTO eventRequestDTO = new EventRequestDTO();

        Event existingEvent = new Event();
        existingEvent.setEventId(eventId);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(Mockito.any(Event.class))).thenAnswer(invocation -> invocation.getArgument(0));

        EventDTO updatedEventDTO = eventService.updateEvent(eventId, eventRequestDTO);

        assertNotNull(updatedEventDTO);
    }

    @Test
    void testUpdateEventEventNotFound() {
        int eventId = 1;
        EventRequestDTO eventRequestDTO = new EventRequestDTO();

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertThrows(EventException.class, () -> eventService.updateEvent(eventId, eventRequestDTO));
    }

    @Test
    void testDeleteEvent() {
        int eventId = 1;
        Event existingEvent = new Event();
        existingEvent.setEventId(eventId);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(existingEvent));

        eventService.deleteEvent(eventId);

        verify(eventRepository, times(1)).delete(existingEvent);
    }

    @Test
    void testDeleteEventEventNotFound() {
        int eventId = 1;

        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> eventService.deleteEvent(eventId));
    }
    @Test
    void testFindAll() {
        Event event1 = new Event();
        Event event2 = new Event();
        List<Event> events = Arrays.asList(event1, event2);

        when(eventRepository.findAll()).thenReturn(events);

        List<Event> result = eventService.findAll();

        assertEquals(events.size(), result.size());
    }
}
