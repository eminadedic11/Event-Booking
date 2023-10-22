package core.api.mailsender.exceptions.general;

import core.api.mailsender.exceptions.GeneralException;

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
