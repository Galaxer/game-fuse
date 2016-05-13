package videogamessearch.android.ccook.info.videogamessearch;

import javax.inject.Singleton;

import dagger.Component;
import videogamessearch.android.ccook.info.videogamessearch.network.NetworkModule;
import videogamessearch.android.ccook.info.videogamessearch.search.SearchActivity;
import videogamessearch.android.ccook.info.videogamessearch.search.SearchActivityModule;
import videogamessearch.android.ccook.info.videogamessearch.search.SearchActivityPresenterModule;

@Singleton
@Component(modules = {
        AppModule.class,
        SearchActivityPresenterModule.class,
        SearchActivityModule.class,
        NetworkModule.class
})
public interface AppComponent {
    final class Initializer {
        private Initializer() {}

        public static AppComponent init(App app) {
            return DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
    }

    void inject(SearchActivity searchActivity);
}