package ba.edu.ibu.eventbooking.rest.dto;

import ba.edu.ibu.eventbooking.core.model.Ticket;
import ba.edu.ibu.eventbooking.core.model.enums.TicketStatus;

public class TicketDTO {
    private int ticketId;
    private String description;
    private TicketStatus status;
    private int eventId;
    private int userId;

    public TicketDTO() {
    }
    public TicketDTO(Ticket ticket) {
        this.ticketId = ticket.getTicketId();
        this.description = ticket.getDescription();
        this.status = ticket.getStatus();
        this.eventId = ticket.getEventId();
        this.userId = ticket.getUserId();
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

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
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

