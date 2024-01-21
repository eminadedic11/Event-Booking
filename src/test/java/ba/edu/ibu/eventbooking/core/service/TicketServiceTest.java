package ba.edu.ibu.eventbooking.core.service;

import ba.edu.ibu.eventbooking.core.exceptions.general.TicketException;
import ba.edu.ibu.eventbooking.core.model.Ticket;
import ba.edu.ibu.eventbooking.core.model.enums.TicketStatus;
import ba.edu.ibu.eventbooking.core.repository.TicketRepository;
import ba.edu.ibu.eventbooking.rest.dto.TicketDTO;
import ba.edu.ibu.eventbooking.rest.dto.TicketRequestDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class TicketServiceTest {
    @MockBean
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @Test
    public void shouldReturnTicketDTOWhenGettingTicketById() {
        int ticketId = 1;
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketId);
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        TicketDTO result = ticketService.getTicketById(ticketId);

        assertNotNull(result);
        assertEquals(ticketId, result.getTicketId());
        verify(ticketRepository, times(1)).findById(ticketId);
    }

    @Test
    public void shouldThrowExceptionWhenGettingNonexistentTicketById() {
        int nonExistentTicketId = 99;
        when(ticketRepository.findById(nonExistentTicketId)).thenReturn(Optional.empty());

        assertThrows(TicketException.class, () -> ticketService.getTicketById(nonExistentTicketId));
        verify(ticketRepository, times(1)).findById(nonExistentTicketId);
    }
    @Test
    public void shouldReturnListOfTicketDTOs() {
        when(ticketRepository.findAll()).thenReturn(List.of(new Ticket()));

        List<TicketDTO> tickets = ticketService.getAllTickets();

        assertEquals(1, tickets.size());
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void shouldThrowExceptionWhenCreatingTicketWithNullStatus() {
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();

        assertThrows(TicketException.class, () -> ticketService.createTicket(ticketRequestDTO));
        verify(ticketRepository, times(0)).save(any(Ticket.class));
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingNonexistentTicket() {
        int nonExistentTicketId = 99;
        TicketRequestDTO ticketRequestDTO = new TicketRequestDTO();
        ticketRequestDTO.setStatus(TicketStatus.SOLD);

        when(ticketRepository.findById(nonExistentTicketId)).thenReturn(Optional.empty());

        assertThrows(TicketException.class, () -> ticketService.updateTicket(nonExistentTicketId, ticketRequestDTO));
        verify(ticketRepository, times(1)).findById(nonExistentTicketId);
        verify(ticketRepository, times(0)).save(any(Ticket.class));
    }

    @Test
    public void shouldDeleteTicket() {
        int ticketId = 1;

        ticketService.deleteTicket(ticketId);

        verify(ticketRepository, times(1)).deleteById(ticketId);
    }
}
