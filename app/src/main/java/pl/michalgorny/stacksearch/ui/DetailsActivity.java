package pl.michalgorny.stacksearch.ui;

import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.michalgorny.stacksearch.R;
import pl.michalgorny.stacksearch.constants.Constants;

/**
 * Activity displaying details about one topic. It uses a WebView and displays StackOverflow web page
 * It required to pass url to page in extras
 */
public class DetailsActivity extends AbstractActivity {

    @InjectView(R.id.details_web_view)
    WebView mWebView;

    @InjectExtra(Constants.WEB_URL_LINK)
    String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.inject(this);
        Dart.inject(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        if (savedInstanceState == null){
            mWebView.loadUrl(mUrl);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mWebView.restoreState(savedInstanceState);
    }
}
