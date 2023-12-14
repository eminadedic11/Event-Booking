package ba.edu.ibu.eventbooking.core.repository;

import ba.edu.ibu.eventbooking.core.model.Event;
import ba.edu.ibu.eventbooking.core.service.EventService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EventRepositoryTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    public void testFindByEventId() {
        int eventId = 1;
        Event expectedEvent = new Event();
        expectedEvent.setEventId(eventId);

        when(eventRepository.findByEventId(eventId)).thenReturn(expectedEvent);

        Event actualEvent = eventService.findById(eventId);

        assertEquals(expectedEvent, actualEvent);
    }

    @Test
    public void testFindAllCustom() {
        List<Event> expectedEvents = Collections.singletonList(new Event());

        when(eventRepository.findAllCustom()).thenReturn(expectedEvents);

        List<Event> actualEvents = eventService.findAllCustom();

        assertEquals(expectedEvents, actualEvents);
    }

    @Test
    public void testFindByEventNameCustom() {
        String eventName = "Test Event";
        Event expectedEvent = new Event();
        expectedEvent.setName(eventName);

        when(eventRepository.findByEventNameCustom(eventName)).thenReturn(Optional.of(expectedEvent));

        Optional<Event> actualEvent = eventService.findByEventNameCustom(eventName);

        assertEquals(expectedEvent, actualEvent.orElse(null));
    }
}
