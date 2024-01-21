package ba.edu.ibu.eventbooking.rest.dto;

import ba.edu.ibu.eventbooking.core.model.Ticket;
import ba.edu.ibu.eventbooking.core.model.enums.TicketStatus;

public class TicketRequestDTO {
    private String description;
    private TicketStatus status;
    private int eventId;
    private int userId;
    private int ticketId;

    public TicketRequestDTO() {}

    public TicketRequestDTO(String description, TicketStatus status, int eventId, int userId) {
        this.description = description;
        this.status = status;
        this.eventId = eventId;
        this.userId = userId;
    }
    public Ticket toEntity() {
        Ticket ticket = new Ticket();
        ticket.setDescription(description);
        ticket.setStatus(status);
        ticket.setEventId(eventId);
        ticket.setUserId(userId);

        return ticket;
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

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }
}

