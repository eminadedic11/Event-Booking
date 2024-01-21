package ba.edu.ibu.eventbooking.core.exceptions.general;

import ba.edu.ibu.eventbooking.core.exceptions.GeneralException;

public class VenueException extends GeneralException {
    public VenueException(int httpCode) {
        super(httpCode);
    }

    public VenueException(int httpCode, String message) {
        super(httpCode, message);
    }

    public VenueException(String message) {
        super(message);
    }

    public VenueException(String message, Exception e) {
        super(message, e);
    }

    public VenueException(Exception e) {
        super(e);
    }
}
