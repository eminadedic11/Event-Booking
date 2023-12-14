package ba.edu.ibu.eventbooking.core.model;

import ba.edu.ibu.eventbooking.core.model.enums.VenueType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class VenueTest {
    @Test
    void testSetVenueId() {
        Venue venue = new Venue();
        venue.setVenueId(1);
        assertEquals(1, venue.getVenueId());
    }

    @Test
    void testSetName() {
        Venue venue = new Venue();
        venue.setName("Sample Venue");
        assertEquals("Sample Venue", venue.getName());
    }

    @Test
    void testSetLocation() {
        Venue venue = new Venue();
        venue.setLocation("Sample Location");
        assertEquals("Sample Location", venue.getLocation());
    }

    @Test
    void testSetCapacity() {
        Venue venue = new Venue();
        venue.setCapacity(1000);
        assertEquals(1000, venue.getCapacity());
    }

    @Test
    void testSetContactEmail() {
        Venue venue = new Venue();
        venue.setContactEmail("sample@email.com");
        assertEquals("sample@email.com", venue.getContactEmail());
    }

    @Test
    void testSetContactPhone() {
        Venue venue = new Venue();
        venue.setContactPhone("123-456-7890");
        assertEquals("123-456-7890", venue.getContactPhone());
    }

    @Test
    void testSetVenueType() {
        Venue venue = new Venue();
        VenueType venueType = VenueType.OUTDOOR_SPACE;
        venue.setVenueType(venueType);
        assertEquals(venueType, venue.getVenueType());
    }

    @Test
    void testInequality() {
        Venue venue1 = new Venue();
        venue1.setVenueId(1);
        venue1.setName("Sample Venue");

        Venue venue2 = new Venue();
        venue2.setVenueId(2);
        venue2.setName("Different Venue");

        assertNotEquals(venue1, venue2);
    }

}
