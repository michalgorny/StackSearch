package pl.michalgorny.stacksearch;

import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

import static pl.michalgorny.stacksearch.TestUtils.isOnline;

/**
 * Abstract class for encapsulate common operates to derived test class
 */
public abstract class AbstractActivityTest extends ActivityInstrumentationTestCase2 {

    protected Solo mSolo;
    protected static final String SEARCH_TEXT = "android";

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

    protected void checkInternetStatus() {
        assertTrue("No internet connection", isOnline(getActivity()));
    }
}
