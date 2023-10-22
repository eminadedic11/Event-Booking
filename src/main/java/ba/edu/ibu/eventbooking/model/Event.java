package ba.edu.ibu.eventbooking.model;

import ba.edu.ibu.eventbooking.model.enums.EventType;

import java.util.Date;

public class Event {
    private int eventId;
    private String name;
    private Date date;
    private String location;
    private String description;
    private EventType eventType;

    public int getEventId() {
        return eventId;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
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
