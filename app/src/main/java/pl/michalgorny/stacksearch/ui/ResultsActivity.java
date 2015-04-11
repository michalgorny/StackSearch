package pl.michalgorny.stacksearch.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnItemClick;
import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.constants.Constants;
import timber.log.Timber;

public class ResultsActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        if (!getIntent().hasExtra(Constants.SEARCH_TEXT)){
            Timber.e("No search data passed to activity!");
            finish();
        }

        String textToSearch = getIntent().getStringExtra(Constants.SEARCH_TEXT);

        Toast.makeText(this, textToSearch, Toast.LENGTH_SHORT).show();
    }

}
