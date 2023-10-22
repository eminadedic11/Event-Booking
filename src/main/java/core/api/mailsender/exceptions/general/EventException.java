package core.api.mailsender.exceptions.general;

import core.api.mailsender.exceptions.GeneralException;
import org.springframework.http.HttpStatus;

public class EventException extends GeneralException {
    public EventException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
    }
}

