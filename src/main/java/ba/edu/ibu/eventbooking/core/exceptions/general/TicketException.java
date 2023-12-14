package ba.edu.ibu.eventbooking.core.exceptions.general;

import ba.edu.ibu.eventbooking.core.exceptions.GeneralException;

public class TicketException extends GeneralException {

    public TicketException(String message) {
        super(message);
    }

    public TicketException(String message, Exception e) {
        super(message, e);
    }
}

