package videogamessearch.android.ccook.info.videogamessearch.search;

import dagger.Module;
import dagger.Provides;
import videogamessearch.android.ccook.info.videogamessearch.network.GiantBombService;

@Module
public class SearchActivityModule {

    @Provides
    SearchActivityPresenter provideSearchPresenter(GiantBombService giantBombService) {
        return new SearchActivityPresenter(giantBombService);
    }
}