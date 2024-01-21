package ba.edu.ibu.eventbooking.core.service;

import ba.edu.ibu.eventbooking.core.model.Ticket;
import ba.edu.ibu.eventbooking.core.repository.TicketRepository;
import ba.edu.ibu.eventbooking.rest.dto.TicketDTO;
import ba.edu.ibu.eventbooking.rest.dto.TicketRequestDTO;
import ba.edu.ibu.eventbooking.core.exceptions.general.TicketException;
import ba.edu.ibu.eventbooking.core.model.enums.TicketStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketDTO> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream()
                .map(TicketDTO::new)
                .collect(Collectors.toList());
    }

    public TicketDTO getTicketById(int ticketId) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (ticket.isEmpty()) {
            throw new TicketException("The ticket with the given ID does not exist.");
        }
        return new TicketDTO(ticket.get());
    }

    public TicketDTO createTicket(TicketRequestDTO payload) {
        validateTicketRequest(payload); // Validate payload before creating the ticket

        Ticket ticket = payload.toEntity();
        ticket.setStatus(TicketStatus.AVAILABLE); // Set the status explicitly
        ticket = ticketRepository.save(ticket);
        return new TicketDTO(ticket);
    }


    public TicketDTO updateTicket(int ticketId, TicketRequestDTO payload) {
        validateTicketRequest(payload); // Validate payload before updating the ticket

        Optional<Ticket> existingTicket = ticketRepository.findById(ticketId);
        if (existingTicket.isEmpty()) {
            throw new TicketException("The ticket with the given ID does not exist.");
        }

        Ticket ticket = existingTicket.get();
        ticket.setStatus(payload.getStatus());
        ticket = ticketRepository.save(ticket);

        return new TicketDTO(ticket);
    }

    private void validateTicketRequest(TicketRequestDTO payload) {
        if (payload.getStatus() == null || payload.getStatus().name().isEmpty()) {
            throw new TicketException("Ticket status is required.");
        }
    }

    public void deleteTicket(int ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}


