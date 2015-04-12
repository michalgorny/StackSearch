package pl.michalgorny.stacksearch.rest;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import pl.michalgorny.stacksearch.StackSearchApplication;
import pl.michalgorny.stacksearch.events.RequestSuccessEvent;
import pl.michalgorny.stacksearch.pojos.StackQuestionResponse;
import pl.michalgorny.stacksearch.events.RequestFailureEvent;
import timber.log.Timber;

/**
 *  Listener class responsible to handle @StackQuestionResponse
 */
public class StackQuestionListener implements RequestListener<StackQuestionResponse>{

    @Inject
    Bus mBus;

    public StackQuestionListener() {
        StackSearchApplication.doDaggerInject(this);
        mBus.register(this);
    }

    @Override
    public void onRequestFailure(SpiceException spiceException) {
        Timber.e("Request failed! " + spiceException.getMessage()) ;
        mBus.post(new RequestFailureEvent(spiceException));
    }

    @Override
    public void onRequestSuccess(StackQuestionResponse stackQuestionResponse) {
        Timber.d("Request success");
        mBus.post(new RequestSuccessEvent(stackQuestionResponse));
    }
}
