package pl.michalgorny.stacksearch.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import pl.michalgorny.stacksearch.pojos.StackQuestionResponse;

/**
 *  Event used to send info about success during request to server.
 */

@Getter
@Accessors(prefix = "m")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class RequestSuccessEvent {
    private final StackQuestionResponse mStackQuestionResponse;
}
