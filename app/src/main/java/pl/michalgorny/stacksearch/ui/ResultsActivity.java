package pl.michalgorny.stacksearch.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.octo.android.robospice.Jackson2SpringAndroidSpiceService;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.constants.Constants;
import pl.michalgorny.stacksearch.events.RequestFailureEvent;
import pl.michalgorny.stacksearch.events.RequestSuccessEvent;
import pl.michalgorny.stacksearch.pojos.StackQuestionResponse;
import pl.michalgorny.stacksearch.rest.StackQuestionListener;
import pl.michalgorny.stacksearch.rest.StackQuestionRequest;
import timber.log.Timber;
public class ResultsActivity extends AbstractActivity {

    @Inject
    SpiceManager mSpiceManager;

    @Inject
    StackQuestionListener mStackQuestionListener;

    @InjectExtra(Constants.SEARCH_TEXT)
    String mTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Dart.inject(this);
        retrievePosts();

    }

    private void retrievePosts() {
        StackQuestionRequest request = new StackQuestionRequest(mTag);
        mSpiceManager.execute(request, mStackQuestionListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSpiceManager.start(this);
    }

    @Override
    protected void onStop() {
        mSpiceManager.shouldStop();
        super.onStop();
    }

}
