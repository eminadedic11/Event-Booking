package ba.edu.ibu.eventbooking.core.repository;

import ba.edu.ibu.eventbooking.core.model.Venue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class VenueRepositoryTest {

    @Autowired
    private VenueRepository venueRepository;
    @Test
    public void testFindByMinimumCapacity() {
        List<Venue> foundVenues = venueRepository.findByMinimumCapacity(100);
        assertTrue(foundVenues.stream().allMatch(v -> v.getCapacity() >= 100),
                "All venues should have a capacity greater than or equal to 100");
    }


    @Test
    public void testFindByCityAndCountry() {
        List<Venue> foundVenues = venueRepository.findByCityAndCountry("Sample City", "Sample Country");
        assertTrue(foundVenues.stream().allMatch(v -> v.getLocation().contains("Sample City")
                && v.getLocation().contains("Sample Country")), "All venues should match the specified city and country");
    }



}