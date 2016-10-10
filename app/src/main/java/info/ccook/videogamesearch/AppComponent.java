package info.ccook.videogamesearch;

import javax.inject.Singleton;

import dagger.Component;
import info.ccook.videogamesearch.network.NetworkModule;
import info.ccook.videogamesearch.search.SearchFragment;
import info.ccook.videogamesearch.search.SearchFragmentModule;
import info.ccook.videogamesearch.search.SearchFragmentPresenterModule;

@Singleton
@Component(modules = {
        AppModule.class,
        SearchFragmentPresenterModule.class,
        SearchFragmentModule.class,
        NetworkModule.class
})
public interface AppComponent {
    final class Initializer {
        private Initializer() {}

        public static AppComponent init(App app) {
            return DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
    }

    void inject(SearchFragment searchFragment);
}