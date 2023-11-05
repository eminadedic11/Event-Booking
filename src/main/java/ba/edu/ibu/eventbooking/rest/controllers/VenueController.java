package ba.edu.ibu.eventbooking.rest.controllers;

import ba.edu.ibu.eventbooking.model.enums.VenueType;
import ba.edu.ibu.eventbooking.rest.dto.VenueDTO;
import ba.edu.ibu.eventbooking.rest.dto.VenueRequestDTO;
import ba.edu.ibu.eventbooking.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @GetMapping("/")
    public ResponseEntity<List<VenueDTO>> getVenues() {
        return ResponseEntity.ok(venueService.getVenues());
    }

    @PostMapping("/createVenue")
    public ResponseEntity<VenueDTO> createVenue(@RequestBody VenueRequestDTO venue) {
        return ResponseEntity.ok(venueService.createVenue(venue));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VenueDTO> getVenueById(@PathVariable int id) {
        return ResponseEntity.ok(venueService.getVenueById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VenueDTO> updateVenue(@PathVariable int id, @RequestBody VenueRequestDTO venue) {
        return ResponseEntity.ok(venueService.updateVenue(id, venue));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenue(@PathVariable int id) {
        venueService.deleteVenue(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<VenueDTO>> searchVenues(@RequestParam(name = "name", required = false) String name,
                                                       @RequestParam(name = "type", required = false) VenueType type,
                                                       @RequestParam(name = "city", required = false) String city,
                                                       @RequestParam(name = "country", required = false) String country) {
        return ResponseEntity.ok(venueService.searchVenues(name, type, city, country));
    }
}

