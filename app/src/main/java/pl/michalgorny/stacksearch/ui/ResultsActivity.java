package pl.michalgorny.stacksearch.ui;

import android.os.Bundle;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.constants.Constants;
import pl.michalgorny.stacksearch.events.RetrievePostsRequestEvent;

/**
 *  Activity displaying a results from server with topics list
 *  Required a extras @Constants.SEARCH_TEXT which is tag used to retrieve results list
 */
public class ResultsActivity extends AbstractActivity {

    @InjectExtra(Constants.SEARCH_TEXT)
    String mTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Dart.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mBus.post(new RetrievePostsRequestEvent(mTag));
    }
}
