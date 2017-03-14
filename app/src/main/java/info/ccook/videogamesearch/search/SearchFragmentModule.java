package info.ccook.videogamesearch.search;

import android.ccook.info.giantbombapi.Endpoints;

import dagger.Module;
import dagger.Provides;
import info.ccook.videogamesearch.PerFragment;

@Module
class SearchFragmentModule {

    private GameSearchView gameSearchView;

    SearchFragmentModule(GameSearchView gameSearchView) {
        this.gameSearchView = gameSearchView;
    }

    @Provides
    @PerFragment
    SearchFragmentPresenter provideSearchPresenter(Endpoints endpoints) {
        return new SearchFragmentPresenter(gameSearchView, endpoints);
    }
}