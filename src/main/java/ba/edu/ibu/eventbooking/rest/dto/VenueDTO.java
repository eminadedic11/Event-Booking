package ba.edu.ibu.eventbooking.rest.dto;

import ba.edu.ibu.eventbooking.model.Venue;
import ba.edu.ibu.eventbooking.model.enums.VenueType;

public class VenueDTO {
    private int venueId;
    private String name;
    private String location;
    private int capacity;
    private String contactEmail;
    private String contactPhone;
    private VenueType venueType;

    public VenueDTO(Venue venue) {
        this.venueId = venue.getVenueId();
        this.name = venue.getName();
        this.location = venue.getLocation();
        this.capacity = venue.getCapacity();
        this.contactEmail = venue.getContactEmail();
        this.contactPhone = venue.getContactPhone();
        this.venueType = venue.getVenueType();
    }


    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
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
