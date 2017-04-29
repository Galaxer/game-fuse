package info.ccook.gamefuse.search;

import android.ccook.info.giantbombapi.Endpoints;
import android.ccook.info.giantbombapi.ResponseFormats;
import android.ccook.info.giantbombapi.game.GameFields;
import android.ccook.info.giantbombapi.search.SearchResources;
import android.ccook.info.giantbombapi.search.models.SearchResults;

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter;

import info.ccook.gamefuse.AppConfig;
import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class SearchActivityPresenter extends MvpNullObjectBasePresenter<GameSearchView> {

    private static final int SEARCH_RESPONSE_LIMIT = 100;

    private Endpoints endpoints;
    private Subscription searchRequest;
    private AppConfig config;

    SearchActivityPresenter(Endpoints endpoints, AppConfig config) {
        this.endpoints = endpoints;
        this.config = config;
    }

    /**
     * Search the API and update the view.
     * @param query Text to be searched for.
     */
    private void search(String query) {
        searchRequest = endpoints.search(config.getGiantBombApiKey(), query,
                ResponseFormats.JSON, SEARCH_RESPONSE_LIMIT,
                SearchResources.GAME, GameFields.NAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<SearchResults>>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable error) {
                        getView().showSearchError();
                    }

                    @Override
                    public final void onNext(Response<SearchResults> resultsResponse) {
                        getView().showSearchResults(resultsResponse.body().results());
                        getView().hideProgressBar();
                    }
                });
    }

    /**
     * Cancel a previous search and start a new one.
     * @param query Text to be searched for.
     */
    void newSearch(String query) {
        cancelSearch();
        search(query);
    }

    /**
     * Stop a search that is currently in progress.
     */
    private void cancelSearch() {
        if (searchRequest != null) {
            searchRequest.unsubscribe();
        }
    }

    /**
     * Update the search view state including query, keyboard visibility, and focus.
     * @param query Query to set in the search view
     * @param searchViewHasFocus Whether the search view should have focus
     */
    void updateSearchViewState(String query, boolean searchViewHasFocus) {
        if (query != null && query.length() > 0) {
            getView().focusOnSearchAndShowKeyboard();
            getView().setSearchQuery(query);
        }

        if (!searchViewHasFocus) {
            getView().clearSearchFocus();
        }
    }
}