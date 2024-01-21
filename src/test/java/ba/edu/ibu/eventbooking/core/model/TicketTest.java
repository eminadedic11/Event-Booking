package ba.edu.ibu.eventbooking.core.model;

import ba.edu.ibu.eventbooking.core.model.enums.TicketStatus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TicketTest {
    @Test
    public void createTicketAndVerifyValues() {
        Ticket ticket = new Ticket();

        ticket.setTicketId(1);
        ticket.setEventId(100);
        ticket.setUserId(50);
        ticket.setPrice(29.99);
        ticket.setSeatNumber("A101");
        ticket.setBooked(false);
        ticket.setStatus(TicketStatus.AVAILABLE);
        ticket.setDescription("VIP Seat");

        assertEquals(1, ticket.getTicketId());
        assertEquals(100, ticket.getEventId());
        assertEquals(50, ticket.getUserId());
        assertEquals(29.99, ticket.getPrice(), 0.001);
        assertEquals("A101", ticket.getSeatNumber());
        assertFalse(ticket.isBooked());
        assertEquals(TicketStatus.AVAILABLE, ticket.getStatus());
        assertEquals("VIP Seat", ticket.getDescription());
    }
    @Test
    public void setBookedStatusToTrue() {
        Ticket ticket = new Ticket();
        ticket.setBooked(true);
        assertTrue(ticket.isBooked());
    }
    @Test
    public void setTicketStatusAndVerify() {
        Ticket ticket = new Ticket();
        ticket.setStatus(TicketStatus.RESERVED);
        assertEquals(TicketStatus.RESERVED, ticket.getStatus());
    }
    @Test
    public void setDescriptionAndVerify() {
        Ticket ticket = new Ticket();

        ticket.setDescription("Standard Seat");

        assertEquals("Standard Seat", ticket.getDescription());
    }
}
