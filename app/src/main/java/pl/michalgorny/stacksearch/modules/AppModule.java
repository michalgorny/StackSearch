package pl.michalgorny.stacksearch.modules;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.michalgorny.stacksearch.ui.ResultsActivity;
import pl.michalgorny.stacksearch.ui.SearchActivity;

/**
 * Module to satisfying application dependencies using Dagger
 */

@Module(
    injects = {
            SearchActivity.class,
            ResultsActivity.class
    }
)
public class AppModule {
    @Provides
    @Singleton
    public Bus provideBus(){
        return new Bus();
    }

}
