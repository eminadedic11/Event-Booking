package ba.edu.ibu.eventbooking.service;

import ba.edu.ibu.eventbooking.model.Venue;
import ba.edu.ibu.eventbooking.model.enums.VenueType;
import ba.edu.ibu.eventbooking.repository.VenueRepository;
import ba.edu.ibu.eventbooking.rest.dto.VenueDTO;
import ba.edu.ibu.eventbooking.rest.dto.VenueRequestDTO;
import core.api.mailsender.exceptions.general.VenueException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VenueService {
    private final VenueRepository venueRepository;

    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    public List<VenueDTO> getVenues() {
        List<Venue> venues = venueRepository.findAll();
        return venues.stream()
                .map(VenueDTO::new)
                .collect(Collectors.toList());
    }

    public VenueDTO getVenueById(int venueId) {
        Optional<Venue> venue = venueRepository.findById(venueId);
        if (venue.isEmpty()) {
            throw new VenueException("The venue with the given ID does not exist.");
        }
        return new VenueDTO(venue.get());
    }

    public VenueDTO createVenue(VenueRequestDTO payload) {
        Venue venue = venueRepository.save(payload.toEntity());
        return new VenueDTO(venue);
    }

    public VenueDTO updateVenue(int id, VenueRequestDTO payload) {
        Optional<Venue> venue = venueRepository.findById(id);
        if (venue.isEmpty()) {
            throw new VenueException("The venue with the given ID does not exist.");
        }
        Venue updatedVenue = payload.toEntity();
        updatedVenue.setVenueId(venue.get().getVenueId());
        updatedVenue = venueRepository.save(updatedVenue);
        return new VenueDTO(updatedVenue);
    }

    public void deleteVenue(int id) {
        Optional<Venue> venue = venueRepository.findById(id);
        venue.ifPresent(venueRepository::delete);
    }

    public List<VenueDTO> searchVenues(String name, VenueType type, String city, String country) {
        List<Venue> venues = venueRepository.findByCriteria(name, type, city, country);

        return venues.stream()
                .map(VenueDTO::new)
                .collect(Collectors.toList());
    }
}