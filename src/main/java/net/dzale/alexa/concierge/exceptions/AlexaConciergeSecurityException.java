package net.dzale.alexa.concierge.exceptions;

/**
 * A wrapper for security related exceptions.
 *
 * @author dzale
 */
public class AlexaConciergeSecurityException extends AlexaConciergeException {

    public AlexaConciergeSecurityException(String message) {
        super(message);
    }

    public AlexaConciergeSecurityException(String message, Throwable cause) {
        super(message, cause);
    }

}
