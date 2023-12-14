package ba.edu.ibu.eventbooking.core.repository;

import ba.edu.ibu.eventbooking.core.model.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TicketRepositoryTest {
    @Autowired
    private TicketRepository ticketRepository;

    @Test
    void testFindByType() {
        List<Ticket> tickets = ticketRepository.findByType("VIP");
        assertTrue(tickets.isEmpty(), "No tickets should be found for the specified type");
    }

    @Test
    void testFindByPriceLessThanOrEqualTo() {
        List<Ticket> tickets = ticketRepository.findByPriceLessThanOrEqualTo(50.0);
        assertTrue(tickets.isEmpty(), "No tickets should be found for the specified price");
    }

    @Test
    void testFindTicketsByPriceRange() {
        List<Ticket> tickets = ticketRepository.findTicketsByPriceRange(30.0, 50.0);
        assertTrue(tickets.isEmpty(), "No tickets should be found for the specified price range");
    }

    @Test
    void testFindByEventId() {
        List<Ticket> tickets = ticketRepository.findByEventId(123);
        assertTrue(tickets.isEmpty(), "No tickets should be found for the specified event ID");
    }

    @Test
    void testFindByStatus() {
        List<Ticket> tickets = ticketRepository.findByStatus("SOLD");
        assertTrue(tickets.isEmpty(), "No tickets should be found for the specified status");
    }

    @Test
    void testFindByEventIdAndStatus() {
        List<Ticket> tickets = ticketRepository.findByEventIdAndStatus(123, "AVAILABLE");
        assertTrue(tickets.isEmpty(), "No tickets should be found for the specified event ID and status");
    }

    @Test
    void testFindAvailableTickets() {
        List<Ticket> tickets = ticketRepository.findAvailableTickets();
        assertTrue(tickets.isEmpty(), "No available tickets should be found");
    }

    @Test
    void testFindTicketsByBuyerUserId() {
        List<Ticket> tickets = ticketRepository.findTicketsByBuyerUserId("user123");
        assertTrue(tickets.isEmpty(), "No tickets should be found for the specified buyer user ID");
    }
}
