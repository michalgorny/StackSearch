package pl.michalgorny.stacksearch.ui;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.StackSearchApplication;

/**
 * Abstract class for Activity. Contains common operations for application activities.
 */

public abstract class AbstractActivity extends ActionBarActivity {

    @Inject
    Bus mBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StackSearchApplication.doDaggerInject(this);
        initActionBar();
    }

    private void initActionBar() {
        ActionBar bar = getSupportActionBar();
        if (bar != null){
            bar.setBackgroundDrawable(new ColorDrawable(R.color.title_background));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBus.register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mBus.unregister(this);
    }
}
