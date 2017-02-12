package info.ccook.videogamesearch.search;

import android.ccook.info.giantbombapi.GiantBombAPI;

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
    SearchFragmentPresenter provideSearchPresenter(GiantBombAPI.Builder builder) {
        return new SearchFragmentPresenter(gameSearchView, builder);
    }
}