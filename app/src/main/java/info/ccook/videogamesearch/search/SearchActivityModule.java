package info.ccook.videogamesearch.search;

import dagger.Module;
import dagger.Provides;
import info.ccook.videogamesearch.network.GiantBombService;

@Module
public class SearchActivityModule {

    @Provides
    SearchActivityPresenter provideSearchPresenter(GiantBombService giantBombService) {
        return new SearchActivityPresenter(giantBombService);
    }
}