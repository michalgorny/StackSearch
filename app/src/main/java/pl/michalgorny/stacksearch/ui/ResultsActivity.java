package pl.michalgorny.stacksearch.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
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
    UltimateRecyclerView mUltimateRecycleView;

    @InjectExtra(Constants.SEARCH_TEXT)
    String mTag;

    List<StackItem> mListItems = new ArrayList<>();
    private SimpleAdapter mAdapter;
    private LinearLayoutManager mLayourManager;

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
        StackQuestionRequest request = new StackQuestionRequest(mTag);
        mSpiceManager.execute(request, mStackQuestionListener);
    }

    private void initiazlizeRecycleView() {
        mLayourManager = new LinearLayoutManager(this);
        mUltimateRecycleView.setLayoutManager(mLayourManager);

        mUltimateRecycleView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrievePosts();
            }
        });

        mAdapter = new SimpleAdapter<StackItem>(R.layout.result_list_item, StackItemHolder.class, mListItems);
        mUltimateRecycleView.setAdapter(mAdapter);
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
        populateList(event.getStackQuestionResponse().getItems());
    }

    @Subscribe
    public void handleFailureEvent(RequestFailureEvent event){
        Toast.makeText(this, event.getSpiceException().getMessage().toString(), Toast.LENGTH_SHORT).show();
        finish();
    }

    private void populateList(List<StackItem> items){
        mListItems.clear();
        mListItems.addAll(items);
        mUltimateRecycleView.getAdapter().notifyDataSetChanged();
    }

}
