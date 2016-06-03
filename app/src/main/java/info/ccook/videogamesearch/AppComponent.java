package info.ccook.videogamesearch;

import javax.inject.Singleton;

import dagger.Component;
import info.ccook.videogamesearch.network.NetworkModule;
import info.ccook.videogamesearch.search.SearchActivity;
import info.ccook.videogamesearch.search.SearchActivityModule;
import info.ccook.videogamesearch.search.SearchActivityPresenterModule;

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