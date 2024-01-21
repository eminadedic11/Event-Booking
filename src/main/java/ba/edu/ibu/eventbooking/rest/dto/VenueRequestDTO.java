package ba.edu.ibu.eventbooking.rest.dto;

import ba.edu.ibu.eventbooking.core.model.Venue;
import ba.edu.ibu.eventbooking.core.model.enums.VenueType;

public class VenueRequestDTO {
    private String name;
    private String location;
    private int capacity;
    private String contactEmail;
    private String contactPhone;
    private VenueType venueType;

    public VenueRequestDTO() {}

    public VenueRequestDTO(String name, String location, int capacity, String contactEmail, String contactPhone, VenueType venueType) {
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.venueType = venueType;
    }

    public Venue toEntity() {
        Venue venue = new Venue();
        venue.setName(name);
        venue.setLocation(location);
        venue.setCapacity(capacity);
        venue.setContactEmail(contactEmail);
        venue.setContactPhone(contactPhone);
        venue.setVenueType(venueType);
        return venue;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public VenueType getVenueType() {
        return venueType;
    }

    public void setVenueType(VenueType venueType) {
        this.venueType = venueType;
    }
}
