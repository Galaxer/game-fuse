package info.ccook.videogamesearch;

import android.app.Application;
import android.ccook.info.giantbombapi.GiantBombAPI;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application.getApplicationContext();
    }

    @Provides
    @Singleton
    GiantBombAPI.Builder provideGiantBombAPIBuilder(Context context) {
        return new GiantBombAPI.Builder(context);
    }
}