package ba.edu.ibu.eventbooking.rest.dto;

import ba.edu.ibu.eventbooking.core.model.Event;
import ba.edu.ibu.eventbooking.core.model.enums.EventType;

import java.util.Date;

public class EventRequestDTO {
    private String name;
    private Date date;
    private String location;
    private String description;
    private EventType eventType;

    public EventRequestDTO() { }

    public EventRequestDTO(Event event) {
        this.name = event.getName();
        this.date = event.getDate();
        this.location = event.getLocation();
        this.description = event.getDescription();
        this.eventType = event.getEventType();
    }

    public Event toEntity() {
        Event event = new Event();
        event.setName(name);
        event.setDate(date);
        event.setLocation(location);
        event.setDescription(description);
        event.setEventType(eventType);
        return event;
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

