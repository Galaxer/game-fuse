package info.ccook.gamefuse.search;

import android.ccook.info.giantbombapi.Endpoints;

import dagger.Module;
import dagger.Provides;
import info.ccook.gamefuse.AppConfig;
import info.ccook.gamefuse.PerActivity;

@Module
class SearchModule {

    /**
     * Provide a SearchFragmentPresenter dependency. If it exists in the retained fragment, use it.
     * Otherwise create a retained fragment, add the presenter to it, then return the presenter.
     * @param endpoints Interface of API endpoints.
     * @return A SearchFragmentPresenter.
     */
    @Provides
    @PerActivity
    SearchActivityPresenter provideSearchPresenter(Endpoints endpoints, AppConfig config) {
       return new SearchActivityPresenter(endpoints, config);
    }

    @Provides
    @PerActivity
    SearchResultsAdapter provideSearchResultsAdapter() {
        return new SearchResultsAdapter();
    }
}