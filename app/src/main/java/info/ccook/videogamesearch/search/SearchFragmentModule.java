package info.ccook.videogamesearch.search;

import dagger.Module;
import dagger.Provides;
import info.ccook.videogamesearch.network.GiantBombService;

@Module
public class SearchFragmentModule {

    @Provides
    SearchFragmentPresenter provideSearchPresenter(GiantBombService giantBombService) {
        return new SearchFragmentPresenter(giantBombService);
    }
}