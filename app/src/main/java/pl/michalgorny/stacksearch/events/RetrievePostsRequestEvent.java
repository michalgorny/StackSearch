package pl.michalgorny.stacksearch.events;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 *  Event used to trigger a downloading posts from server
 *  It contains a tag which is used to search posts indicated with this tag
 */

@Getter
@Accessors(prefix = "m")
public class RetrievePostsRequestEvent {
    private final String mTag;

    public RetrievePostsRequestEvent(String tag) {
        mTag = tag;
    }
}
