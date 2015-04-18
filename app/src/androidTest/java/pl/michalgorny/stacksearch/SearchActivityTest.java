package pl.michalgorny.stacksearch;

import android.widget.EditText;

import pl.michalgorny.stacksearch.ui.ResultsActivity;
import pl.michalgorny.stacksearch.ui.SearchActivity;

import static pl.michalgorny.stacksearch.TestErrorTexts.ACTIVITY_NOT_CHANGED;

/**
 *  Test class testing @SearchActivity behaviour
 */
public class SearchActivityTest extends AbstractActivityTest {

    public SearchActivityTest() {
        super(SearchActivity.class);
    }

    public void testShouldSwitchToResultsActivity() throws Exception {
        checkInternetStatus();
        EditText search = mSolo.getEditText(0);
        mSolo.clearEditText(search);
        mSolo.enterText(search, SEARCH_TEXT);
        mSolo.clickOnButton(0);
        mSolo.assertCurrentActivity(ACTIVITY_NOT_CHANGED, ResultsActivity.class);
    }

    public void testButtonShouldBeDisabledWhenSearchFieldIsEmpty() throws Exception {
        EditText search = mSolo.getEditText(0);
        mSolo.clearEditText(search);
        assertFalse(mSolo.getButton(0).isEnabled());
    }

}
