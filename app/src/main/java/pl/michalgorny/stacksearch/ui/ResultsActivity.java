package pl.michalgorny.stacksearch.ui;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.octo.android.robospice.SpiceManager;
import com.skocken.efficientadapter.lib.adapter.SimpleAdapter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.adapters.StackItemHolder;
import pl.michalgorny.stacksearch.constants.Constants;
import pl.michalgorny.stacksearch.events.RequestFailureEvent;
import pl.michalgorny.stacksearch.events.RequestSuccessEvent;
import pl.michalgorny.stacksearch.pojos.StackItem;
import pl.michalgorny.stacksearch.rest.StackQuestionListener;
import pl.michalgorny.stacksearch.rest.StackQuestionRequest;

public class ResultsActivity extends AbstractActivity {

    @Inject
    SpiceManager mSpiceManager;

    @Inject
    StackQuestionListener mStackQuestionListener;

    @InjectView(R.id.results_recycler_view)
    RecyclerView mResultsRecycleView;

    @InjectExtra(Constants.SEARCH_TEXT)
    String mTag;

    List<StackItem> mListItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Dart.inject(this);
        ButterKnife.inject(this);
        retrievePosts();
        initiazlizeRecycleView();
    }

    private void retrievePosts() {
        setProgressBarIndeterminateVisibility(true);
        StackQuestionRequest request = new StackQuestionRequest(mTag);
        mSpiceManager.execute(request, mStackQuestionListener);
    }

    private void initiazlizeRecycleView() {

        mResultsRecycleView.setLayoutManager(new LinearLayoutManager(this));

        SimpleAdapter adpater = new SimpleAdapter<StackItem>(
                R.layout.result_list_item, StackItemHolder.class, mListItems);
        mResultsRecycleView.setAdapter(adpater);
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

    @Subscribe
    public void handleSuccessEvent(RequestSuccessEvent event){
        Toast.makeText(this, String.valueOf(event.getStackQuestionResponse().getItems().size()), Toast.LENGTH_SHORT).show();
        populateList(event.getStackQuestionResponse().getItems());
    }

    @Subscribe
    public void handleFailureEvent(RequestFailureEvent event){
        Toast.makeText(this, event.getSpiceException().getMessage().toString(), Toast.LENGTH_SHORT).show();
        finish();
    }

    private void populateList(List<StackItem> items){
        mListItems.addAll(items);
        mResultsRecycleView.getAdapter().notifyDataSetChanged();
    }

}
