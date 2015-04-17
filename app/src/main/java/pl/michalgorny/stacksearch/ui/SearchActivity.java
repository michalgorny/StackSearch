package pl.michalgorny.stacksearch.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.constants.Constants;


public class SearchActivity extends AbstractActivity {

    @InjectView(R.id.SearchActivitySearchButton)
    Button mSearchButton;

    @InjectView(R.id.SearchActivitySearchEditText)
    EditText mSearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.inject(this);
        mSearchEditText.addTextChangedListener(mSearchEditTextWatcher);
    }

    @OnClick(R.id.SearchActivitySearchButton)
    public void launchResultsActivity() {
        Intent intent = new Intent(this, ResultsActivity.class);
        intent.putExtra(Constants.SEARCH_TEXT, mSearchEditText.getText().toString());
        startActivity(intent);
    }

    private TextWatcher mSearchEditTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            mSearchButton.setEnabled(charSequence.length() > 0);
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
    };
}
