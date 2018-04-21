package net.dzale.alexa.concierge.exceptions;

/**
 * A generic exception encapsulator.
 *
 * @author dzale
 */
public class AlexaConciergeException extends Exception {

    public AlexaConciergeException(String message) {
        super(message);
    }

    public AlexaConciergeException(String message, Throwable cause) {
        super(message, cause);
    }

}
