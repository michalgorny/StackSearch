package pl.michalgorny.stacksearch.events;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 *  Event used to send info about failure during request to server
 */
@Getter
@Accessors(prefix = "m")
public class RequestFailureEvent {
    private final Exception mSpiceException;

    public RequestFailureEvent(Exception spiceException) {
        mSpiceException = spiceException;
    }
}
