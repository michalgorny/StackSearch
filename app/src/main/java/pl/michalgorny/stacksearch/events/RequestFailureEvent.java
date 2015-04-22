package pl.michalgorny.stacksearch.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 *  Event used to send info about failure during request to server
 */

@Getter
@Accessors(prefix = "m")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC, suppressConstructorProperties = true)
public class RequestFailureEvent {
    private final Exception mSpiceException;
}
