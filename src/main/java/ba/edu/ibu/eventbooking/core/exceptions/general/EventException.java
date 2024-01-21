package ba.edu.ibu.eventbooking.core.exceptions.general;

import ba.edu.ibu.eventbooking.core.exceptions.GeneralException;
import org.springframework.http.HttpStatus;

public class EventException extends GeneralException {
    public EventException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}

