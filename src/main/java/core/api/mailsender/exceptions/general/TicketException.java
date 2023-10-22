package core.api.mailsender.exceptions.general;

import core.api.mailsender.exceptions.GeneralException;

public class TicketException extends GeneralException {

    public TicketException(String message) {
        super(message);
    }

    public TicketException(String message, Exception e) {
        super(message, e);
    }
}

