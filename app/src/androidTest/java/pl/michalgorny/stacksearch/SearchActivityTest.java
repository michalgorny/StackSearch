package pl.michalgorny.stacksearch;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

import pl.michalgorny.stacksearch.ui.ResultsActivity;
import pl.michalgorny.stacksearch.ui.SearchActivity;

import static pl.michalgorny.stacksearch.TestUtils.isOnline;

/**
 *  Test class testing @SearchActivity behaviour
 */
public class SearchActivityTest extends ActivityInstrumentationTestCase2<SearchActivity>{
    private static final String SAMPLE_TEXT = "android";
    private Solo mSolo;

    public SearchActivityTest() {
        super(SearchActivity.class);
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

    public void testShouldSwitchToResultsActivity() throws Exception {
        checkInternetStatus();
        EditText search = mSolo.getEditText(0);
        mSolo.clearEditText(search);
        mSolo.enterText(search, SAMPLE_TEXT);
        mSolo.clickOnButton(0);
        mSolo.assertCurrentActivity("Activity was not changed", ResultsActivity.class);
    }

    public void testButtonShouldBeDisabledWhenSearchFieldIsEmpty() throws Exception {
        checkInternetStatus();
        EditText search = mSolo.getEditText(0);
        mSolo.clearEditText(search);
        assertTrue(!mSolo.getButton(0).isClickable());
    }

    private void checkInternetStatus() {
        assertTrue("No internet connection", isOnline(getActivity()));
    }
}
