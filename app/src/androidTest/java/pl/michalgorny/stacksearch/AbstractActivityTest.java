package pl.michalgorny.stacksearch;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;

import com.robotium.solo.Solo;

/**
 * Abstract class for encapsulate common operates to derived test class
 */
public abstract class AbstractActivityTest extends ActivityInstrumentationTestCase2 {

    protected Solo mSolo;

    public AbstractActivityTest(Class activityClass) {
        super(activityClass);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mSolo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        mSolo.finishOpenedActivities();
        super.tearDown();
    }
}
