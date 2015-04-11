package pl.michalgorny.stacksearch.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.squareup.otto.Bus;

import javax.inject.Inject;

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
