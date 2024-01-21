package ba.edu.ibu.eventbooking.core.model;

import ba.edu.ibu.eventbooking.core.model.enums.EventType;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.Date;


public class EventTest {
    @Test
    void shouldCreateNewEvent() {
        Event event = new Event();
        event.setEventId(1);
        event.setName("Test Event");
        event.setDate(new Date());
        event.setLocation("Test Location");
        event.setDescription("Test Description");
        event.setEventType(EventType.CONCERT);

        assertThat(event.getEventId()).isEqualTo(1);
        assertThat(event.getName()).isEqualTo("Test Event");
        assertThat(event.getDate()).isInstanceOf(Date.class);
        assertThat(event.getLocation()).isEqualTo("Test Location");
        assertThat(event.getDescription()).isEqualTo("Test Description");
        assertThat(event.getEventType()).isEqualTo(EventType.CONCERT);

    }
    @Test
    void shouldCompareTwoEvents() {
        Event event1 = new Event();
        event1.setEventId(1);
        event1.setName("Test Event");
        event1.setDate(new Date());
        event1.setLocation("Test Location");
        event1.setDescription("Test Description");
        event1.setEventType(EventType.CONCERT);

        Event event2 = new Event();
        event2.setEventId(1);
        event2.setName("Test Event");
        event2.setDate(new Date());
        event2.setLocation("Test Location");
        event2.setDescription("Test Description");
        event2.setEventType(EventType.CONCERT);

        assertThat(event1)
                .usingRecursiveComparison()
                .ignoringFields("date")
                .isEqualTo(event2);
    }
}
