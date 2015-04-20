package pl.michalgorny.stacksearch.events;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 *  Event used to trigger a downloading posts from server
 *  It contains a tag which is used to search posts indicated with this tag
 */

@Getter
@Accessors(prefix = "m")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class RetrievePostsRequestEvent {
    private final String mTag;
}
