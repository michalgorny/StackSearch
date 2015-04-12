package pl.michalgorny.stacksearch.modules;

import com.octo.android.robospice.Jackson2SpringAndroidSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.michalgorny.stacksearch.rest.StackQuestionListener;
import pl.michalgorny.stacksearch.ui.ResultsActivity;
import pl.michalgorny.stacksearch.ui.SearchActivity;

/**
 * Module to satisfying application dependencies using Dagger
 */

@Module(
    injects = {
            SearchActivity.class,
            ResultsActivity.class,
            StackQuestionListener.class
    }
)
public class AppModule {
    @Provides
    @Singleton
    public Bus provideBus(){
        return new Bus();
    }

    @Provides
    public SpiceManager provideSpiceManager(){
        return new SpiceManager(Jackson2SpringAndroidSpiceService.class);
    }

}
