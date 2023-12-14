package ba.edu.ibu.eventbooking.core.service;

import ba.edu.ibu.eventbooking.core.exceptions.general.VenueException;
import ba.edu.ibu.eventbooking.core.model.Venue;
import ba.edu.ibu.eventbooking.core.repository.VenueRepository;
import ba.edu.ibu.eventbooking.rest.dto.VenueDTO;
import ba.edu.ibu.eventbooking.rest.dto.VenueRequestDTO;
import ba.edu.ibu.eventbooking.core.model.enums.VenueType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
@SpringBootTest
public class VenueServiceTest {
    @MockBean
    private VenueRepository venueRepository;

    @Autowired
    private VenueService venueService;

    @Test
    public void shouldReturnListOfVenueDTOs() {
        when(venueRepository.findAll()).thenReturn(List.of(new Venue()));

        List<VenueDTO> venues = venueService.getVenues();

        assertEquals(1, venues.size());
        verify(venueRepository, times(1)).findAll();
    }

    @Test
    public void shouldReturnVenueDTOById() {
        int venueId = 1;
        Venue venue = new Venue();
        venue.setVenueId(venueId);

        when(venueRepository.findById(venueId)).thenReturn(Optional.of(venue));

        VenueDTO foundVenue = venueService.getVenueById(venueId);

        assertEquals(venueId, foundVenue.getVenueId());
        verify(venueRepository, times(1)).findById(venueId);
    }

    @Test
    public void shouldCreateVenue() {

        VenueRequestDTO venueRequestDTO = new VenueRequestDTO();
        Venue venueToSave = new Venue();

        when(venueRepository.save(any(Venue.class))).thenReturn(venueToSave);

        VenueDTO createdVenue = venueService.createVenue(venueRequestDTO);

        assertEquals(venueToSave.getVenueId(), createdVenue.getVenueId());
        verify(venueRepository, times(1)).save(any(Venue.class));
    }

    @Test
    public void shouldUpdateVenue() {
        int venueId = 1;
        VenueRequestDTO venueRequestDTO = new VenueRequestDTO();
        Venue existingVenue = new Venue();
        existingVenue.setVenueId(venueId);

        when(venueRepository.findById(venueId)).thenReturn(Optional.of(existingVenue));
        when(venueRepository.save(any(Venue.class))).thenAnswer(invocation -> invocation.getArgument(0));

        VenueDTO updatedVenue = venueService.updateVenue(venueId, venueRequestDTO);

        assertEquals(venueId, updatedVenue.getVenueId());
        verify(venueRepository, times(1)).findById(venueId);
        verify(venueRepository, times(1)).save(any(Venue.class));
    }

    @Test
    public void shouldThrowExceptionWhenUpdatingNonexistentVenue() {
        int nonExistentVenueId = 99;
        VenueRequestDTO venueRequestDTO = new VenueRequestDTO();

        when(venueRepository.findById(nonExistentVenueId)).thenReturn(Optional.empty());

        assertThrows(VenueException.class, () -> venueService.updateVenue(nonExistentVenueId, venueRequestDTO));
        verify(venueRepository, times(1)).findById(nonExistentVenueId);
        verify(venueRepository, times(0)).save(any(Venue.class));
    }

    @Test
    public void shouldDeleteVenue() {
        int venueId = 1;
        Venue existingVenue = new Venue();
        existingVenue.setVenueId(venueId);

        when(venueRepository.findById(venueId)).thenReturn(Optional.of(existingVenue));

        venueService.deleteVenue(venueId);

        verify(venueRepository, times(1)).findById(venueId);
        verify(venueRepository, times(1)).delete(existingVenue);
    }

    @Test
    public void shouldReturnListOfVenueDTOsByCriteria() {
        String name = "Sample Venue";
        VenueType type = VenueType.OUTDOOR_SPACE;
        String city = "Sample City";
        String country = "Sample Country";

        when(venueRepository.findByCriteria(name, type, city, country)).thenReturn(List.of(new Venue()));

        List<VenueDTO> venues = venueService.searchVenues(name, type, city, country);

        assertEquals(1, venues.size());
        verify(venueRepository, times(1)).findByCriteria(name, type, city, country);
    }
}
