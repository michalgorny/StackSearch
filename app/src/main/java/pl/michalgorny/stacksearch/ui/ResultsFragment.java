package pl.michalgorny.stacksearch.ui;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.octo.android.robospice.SpiceManager;
import com.skocken.efficientadapter.lib.adapter.AbsViewHolderAdapter;
import com.skocken.efficientadapter.lib.adapter.SimpleAdapter;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.StackSearchApplication;
import pl.michalgorny.stacksearch.adapters.StackItemHolder;
import pl.michalgorny.stacksearch.events.RequestFailureEvent;
import pl.michalgorny.stacksearch.events.RequestSuccessEvent;
import pl.michalgorny.stacksearch.events.RetrievePostsRequestEvent;
import pl.michalgorny.stacksearch.pojos.StackItem;
import pl.michalgorny.stacksearch.rest.StackQuestionListener;
import pl.michalgorny.stacksearch.rest.StackQuestionRequest;

import static pl.michalgorny.stacksearch.constants.Constants.WEB_URL_LINK;

public class ResultsFragment extends Fragment {

    @Inject
    Bus mBus;

    @Inject
    SpiceManager mSpiceManager;

    @Inject
    StackQuestionListener mStackQuestionListener;

    @InjectView(R.id.results_recycler_view)
    UltimateRecyclerView mUltimateRecycleView;

    private List<StackItem> mListItems = new ArrayList<>();
    private SimpleAdapter mAdapter;

    private LinearLayoutManager mLayoutManager;
    private String mTag;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        StackSearchApplication.doDaggerInject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        ButterKnife.inject(this, view);
        initiazlizeRecycleView();
        return view;
    }

    private void initiazlizeRecycleView() {
        mUltimateRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mUltimateRecycleView.setLayoutManager(mLayoutManager);

        mUltimateRecycleView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrievePosts();
            }
        });

        mAdapter = new SimpleAdapter<StackItem>(R.layout.result_list_item, StackItemHolder.class, mListItems);
        mAdapter.setOnItemClickListener(new AbsViewHolderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AbsViewHolderAdapter absViewHolderAdapter, View view, Object o, int i) {
                showDetailsView(mListItems.get(i).getLink());
            }
        });
        mUltimateRecycleView.setAdapter(mAdapter);
    }

    private void showDetailsView(String link) {
        Intent i = new Intent(getActivity(), DetailsActivity.class);
        i.putExtra(WEB_URL_LINK, link);
        startActivity(i);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBus.register(this);
        mSpiceManager.start(getActivity());
    }

    @Override
    public void onStop() {
        mSpiceManager.shouldStop();
        mBus.unregister(this);
        super.onStop();
    }

    @Subscribe
    public void retrievePosts(RetrievePostsRequestEvent event) {
        mTag = getTag();
        retrievePosts();
    }

    private void retrievePosts() {
        StackQuestionRequest request = new StackQuestionRequest(mTag);
        mSpiceManager.execute(request, mStackQuestionListener);
    }

    @Subscribe
    public void handleSuccessEvent(RequestSuccessEvent event){
        populateList(event.getStackQuestionResponse().getItems());
    }

    @Subscribe
    public void handleFailureEvent(RequestFailureEvent event){
        Toast.makeText(getActivity(), event.getSpiceException().getMessage().toString(), Toast.LENGTH_SHORT).show();
    }

    private void populateList(List<StackItem> items){
        mListItems.clear();
        mListItems.addAll(items);
        mUltimateRecycleView.getAdapter().notifyDataSetChanged();
    }
}
