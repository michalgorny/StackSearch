package pl.michalgorny.stacksearch;

import android.app.Activity;
import android.content.Intent;

import com.robotium.solo.Solo;

import pl.michalgorny.stacksearch.constants.Constants;
import pl.michalgorny.stacksearch.ui.DetailsActivity;
import pl.michalgorny.stacksearch.ui.ResultsActivity;

/**
 * Test class testing @ResultsActivity behaviour
 */
public class ResultsActivityTest extends AbstractActivityTest {

    public ResultsActivityTest() {
        super(ResultsActivity.class);
    }

    public void testShouldDisplayTopicList() throws Exception {
        checkInternetStatus();
        assertTrue(mSolo.waitForView(R.id.result_list_item_name, 1, 5000));
    }

    public void testShouldDisplayPostAfterClick() throws Exception {
        checkInternetStatus();
        mSolo.clickOnImage(1);
        mSolo.assertCurrentActivity(TestErrorTexts.ACTIVITY_NOT_CHANGED, DetailsActivity.class);
    }

    public void testShouldListInTheSamePositionAfterRotateScreen() throws Exception {
        mSolo.setActivityOrientation(Solo.PORTRAIT);
        mSolo.scrollToBottom();
        final String beforeRotate = mSolo.getText(0).getText().toString();
        mSolo.setActivityOrientation(Solo.LANDSCAPE);
        Thread.sleep(300);
        final String afterRotate = mSolo.getText(0).getText().toString();
        assertEquals(beforeRotate, afterRotate);
    }

    @Override
    public Activity getActivity() {
        setActivityIntentWithExtra();
        return super.getActivity();
    }

    private void setActivityIntentWithExtra() {
        Intent i = new Intent();
        i.putExtra(Constants.SEARCH_TEXT, SEARCH_TEXT);
        setActivityIntent(i);
    }
}
