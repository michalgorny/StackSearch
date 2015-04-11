package pl.michalgorny.stacksearch.modules;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Module to satisfying application dependencies using Dagger
 */

@Module(
    injects = {

    }
)
public class AppModule {
    @Provides
    @Singleton
    public Bus provideBus(){
        return new Bus();
    }

}
