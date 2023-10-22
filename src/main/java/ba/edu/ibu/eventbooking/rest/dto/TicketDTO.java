package ba.edu.ibu.eventbooking.rest.dto;

import ba.edu.ibu.eventbooking.model.Ticket;

public class TicketDTO {
    private int ticketId;
    private String description;
    private String status;
    private int eventId;
    private int userId;


    public TicketDTO(Ticket ticket) {
        this.ticketId = ticketId;
        this.description = description;
        this.status = status;
        this.eventId = eventId;
        this.userId = userId;
    }


    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}

