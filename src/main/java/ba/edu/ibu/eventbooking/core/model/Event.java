package ba.edu.ibu.eventbooking.core.model;

import ba.edu.ibu.eventbooking.core.model.enums.EventType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Document(collection = "events")
public class Event {

    @Id
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
