package pl.michalgorny.stacksearch;

import android.widget.EditText;

import pl.michalgorny.stacksearch.ui.ResultsActivity;
import pl.michalgorny.stacksearch.ui.SearchActivity;

import static pl.michalgorny.stacksearch.TestUtils.isOnline;

/**
 *  Test class testing @SearchActivity behaviour
 */
public class SearchActivityTest extends AbstractActivityTest {
    private static final String SAMPLE_TEXT = "android";

    public SearchActivityTest() {
        super(SearchActivity.class);
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
        EditText search = mSolo.getEditText(0);
        mSolo.clearEditText(search);
        assertTrue(!mSolo.getButton(0).isEnabled());
    }

    private void checkInternetStatus() {
        assertTrue("No internet connection", isOnline(getActivity()));
    }
}
