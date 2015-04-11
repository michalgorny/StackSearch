package pl.michalgorny.stacksearch.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.michalgorny.stacksearch.R;


public class SearchActivity extends AbstractActivity{

    @InjectView(R.id.SearchActivitySearchButton)
    Button mSearchButton;
    @InjectView(R.id.SearchActivitySearchEditText)
    EditText mSearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.SearchActivitySearchButton)
    public void searchPosts(){

    }

}
