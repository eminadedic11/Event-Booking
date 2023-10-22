package ba.edu.ibu.eventbooking.service;

import ba.edu.ibu.eventbooking.model.Ticket;
import ba.edu.ibu.eventbooking.model.enums.TicketStatus;
import ba.edu.ibu.eventbooking.repository.TicketRepository;
import ba.edu.ibu.eventbooking.rest.dto.TicketDTO;
import ba.edu.ibu.eventbooking.rest.dto.TicketRequestDTO;
import core.api.mailsender.exceptions.general.TicketException;
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
        if (payload.getStatus() == null || payload.getStatus().name().isEmpty()) {
            throw new TicketException("Ticket status is required.");
        }
        Ticket ticket = payload.toEntity();
        ticket = ticketRepository.save(ticket);
        return new TicketDTO(ticket);
    }

    public TicketDTO updateTicket(int ticketId, TicketRequestDTO payload) {
        Optional<Ticket> existingTicket = ticketRepository.findById(ticketId);
        if (existingTicket.isEmpty()) {
            throw new TicketException("The ticket with the given ID does not exist.");
        }

        Ticket ticket = existingTicket.get();
        ticket.setStatus(payload.getStatus());
        ticket = ticketRepository.save(ticket);

        return new TicketDTO(ticket);
    }

    public void deleteTicket(int ticketId) {
        ticketRepository.deleteById(ticketId);
    }
}


