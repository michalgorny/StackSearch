package pl.michalgorny.stacksearch.rest;

import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import pl.michalgorny.stacksearch.constants.Constants;
import pl.michalgorny.stacksearch.pojos.StackQuestionResponse;

/**
 *  Spice request to StackOverflow API. It requests for Stack questions.
 */
public class StackQuestionRequest extends SpringAndroidSpiceRequest<StackQuestionResponse>{
    private static final String URL = Constants.STACK_API_URL + "/2.2/search?order=desc&sort=activity&site=stackoverflow&tagged=";

    private final String mTag;

    public StackQuestionRequest(String tag) {
        super(StackQuestionResponse.class);
        mTag = tag;
    }

    @Override
    public StackQuestionResponse loadDataFromNetwork() throws Exception {
        return getRestTemplate().getForObject(URL + mTag, StackQuestionResponse.class);
    }
}
