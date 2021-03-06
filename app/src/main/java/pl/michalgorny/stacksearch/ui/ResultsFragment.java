package pl.michalgorny.stacksearch.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.loadindicators.adrianlesniak.library.LoaderView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;
import com.octo.android.robospice.SpiceManager;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.StackSearchApplication;
import pl.michalgorny.stacksearch.adapters.StackItemAdapter;
import pl.michalgorny.stacksearch.events.RequestFailureEvent;
import pl.michalgorny.stacksearch.events.RequestSuccessEvent;
import pl.michalgorny.stacksearch.events.RetrievePostsRequestEvent;
import pl.michalgorny.stacksearch.pojos.StackItem;
import pl.michalgorny.stacksearch.rest.StackQuestionListener;
import pl.michalgorny.stacksearch.rest.StackQuestionRequest;


public class ResultsFragment extends Fragment {

    @Inject
    Bus mBus;

    @Inject
    SpiceManager mSpiceManager;

    @Inject
    StackQuestionListener mStackQuestionListener;

    @InjectView(R.id.results_recycler_view)
    UltimateRecyclerView mUltimateRecyclerView;

    @InjectView(R.id.results_progress_bar)
    LoaderView mLoaderView;

    private List<StackItem> mListItems = new ArrayList<>();
    private UltimateViewAdapter mAdapter;

    private LinearLayoutManager mLayoutManager;
    private String mTag = "";

    private boolean mIsInProgress = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        StackSearchApplication.doDaggerInject(this);
        mSpiceManager.start(getActivity());
        mAdapter = new StackItemAdapter(mListItems);
        mBus.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_results, container, false);
        ButterKnife.inject(this, view);
        showProgress(mIsInProgress);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initiazlizeRecyclerView();
    }

    private void initiazlizeRecyclerView() {
        mUltimateRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mUltimateRecyclerView.setLayoutManager(mLayoutManager);
        mUltimateRecyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrievePosts();
            }
        });
        mUltimateRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSpiceManager.shouldStop();
        mBus.unregister(this);
    }

    @Subscribe
    public void retrievePosts(RetrievePostsRequestEvent event) {
        if (!mTag.equals(event.getTag())){
            mTag = event.getTag();
            retrievePosts();
        }
    }

    private void retrievePosts() {
        StackQuestionRequest request = new StackQuestionRequest(mTag);
        mSpiceManager.execute(request, mStackQuestionListener);
        showProgress(true);
    }

    @Subscribe
    public void handleSuccessEvent(RequestSuccessEvent event) {
        populateList(event.getStackQuestionResponse().getItems());
        showProgress(false);
    }

    @Subscribe
    public void handleFailureEvent(RequestFailureEvent event) {
        Toast.makeText(getActivity(), event.getSpiceException().getMessage().toString(), Toast.LENGTH_SHORT).show();
        mUltimateRecyclerView.getAdapter().notifyDataSetChanged();
        showProgress(false);
    }

    private void populateList(List<StackItem> items){
        mListItems.clear();
        mListItems.addAll(items);
        mUltimateRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void showProgress(boolean isInProgress) {
        mIsInProgress = isInProgress;
        mLoaderView.setVisibility(isInProgress && mListItems.isEmpty() ? View.VISIBLE : View.GONE);
        mUltimateRecyclerView.setVisibility(isInProgress && mListItems.isEmpty() ? View.GONE : View.VISIBLE);
    }

}