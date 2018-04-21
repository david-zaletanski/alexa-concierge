package net.dzale.alexa.concierge.exceptions;

/**
 * Exceptions related to data errors.
 *
 * @author dzale
 */
public class AlexaConciergeDataException extends AlexaConciergeException {

    public AlexaConciergeDataException(String message) {
        super(message);
    }

    public AlexaConciergeDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
