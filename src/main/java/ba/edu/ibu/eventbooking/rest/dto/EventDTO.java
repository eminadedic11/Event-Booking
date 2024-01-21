package ba.edu.ibu.eventbooking.rest.dto;

import ba.edu.ibu.eventbooking.core.model.Event;
import ba.edu.ibu.eventbooking.core.model.enums.EventType;

import java.util.Date;

public class EventDTO {
    private int eventId;
    private String name;
    private Date date;
    private String location;
    private String description;
    private EventType eventType;

    public EventDTO(Event event) {
        this.name = event.getName();
        this.date = event.getDate();
        this.location = event.getLocation();
        this.description = event.getDescription();
        this.eventType = event.getEventType();
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

}

