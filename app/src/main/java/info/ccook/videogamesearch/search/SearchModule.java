package info.ccook.videogamesearch.search;

import android.ccook.info.giantbombapi.Endpoints;
import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import info.ccook.videogamesearch.AppConfig;
import info.ccook.videogamesearch.PerActivity;
import info.ccook.videogamesearch.RetainedFragment;

@Module
class SearchModule {

    private static final String SEARCH_PRESENTER_FRAGMENT_TAG = "SearchPresenterFragment";

    private GameSearchView searchView;
    private FragmentManager fragmentManager;

    SearchModule(GameSearchView searchView, FragmentManager fragmentManager) {
        this.searchView = searchView;
        this.fragmentManager = fragmentManager;
    }

    /**
     * Provide a SearchFragmentPresenter dependency. If it exists in the retained fragment, use it.
     * Otherwise create a retained fragment, add the presenter to it, then return the presenter.
     * @param endpoints Interface of API endpoints.
     * @return A SearchFragmentPresenter.
     */
    @Provides
    @PerActivity
    SearchActivityPresenter provideSearchPresenter(Endpoints endpoints, AppConfig config) {
        RetainedFragment existingFragment = (RetainedFragment) fragmentManager
                .findFragmentByTag(SEARCH_PRESENTER_FRAGMENT_TAG);
        if (existingFragment != null) {
            return (SearchActivityPresenter) existingFragment.get();
        } else {
            RetainedFragment<SearchActivityPresenter> newFragment = new RetainedFragment<>();
            newFragment.set(new SearchActivityPresenter(searchView, endpoints, config));
            fragmentManager.beginTransaction().add(newFragment, SEARCH_PRESENTER_FRAGMENT_TAG)
                    .commit();
            return newFragment.get();
        }
    }
}