package pl.michalgorny.stacksearch.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.octo.android.robospice.Jackson2SpringAndroidSpiceService;
import com.octo.android.robospice.JacksonSpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import javax.inject.Inject;

import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.constants.Constants;
import pl.michalgorny.stacksearch.pojos.StackQuestionResponse;
import pl.michalgorny.stacksearch.rest.StackQuestionListener;
import pl.michalgorny.stacksearch.rest.StackQuestionRequest;
import timber.log.Timber;
public class ResultsActivity extends AbstractActivity {

    private SpiceManager mSpiceManager = new SpiceManager(Jackson2SpringAndroidSpiceService.class);

    @Inject
    StackQuestionListener mStackQuestionListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        if (!getIntent().hasExtra(Constants.SEARCH_TEXT)){
            Timber.e("No search data passed to activity!");
            finish();
        }

        String tag = getIntent().getStringExtra(Constants.SEARCH_TEXT);
        StackQuestionRequest request = new StackQuestionRequest(tag);
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
