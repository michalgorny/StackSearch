package pl.michalgorny.stacksearch.events;

import lombok.Getter;
import lombok.experimental.Accessors;
import pl.michalgorny.stacksearch.pojos.StackQuestionResponse;

/**
 *  Event used to send info about success during request to server.
 */
@Getter
@Accessors(prefix = "m")
public class RequestSuccessEvent {

    private final StackQuestionResponse mStackQuestionResponse;

    public RequestSuccessEvent(StackQuestionResponse stackQuestionResponse) {
        mStackQuestionResponse = stackQuestionResponse;
    }
}
