package info.ccook.videogamesearch.search;

import android.ccook.info.giantbombapi.Endpoints;
import android.ccook.info.giantbombapi.ResponseFormats;
import android.ccook.info.giantbombapi.game.GameFields;
import android.ccook.info.giantbombapi.search.SearchResources;
import android.ccook.info.giantbombapi.search.models.SearchResults;

import info.ccook.videogamesearch.config.PrivateConfig;
import retrofit2.Response;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class SearchFragmentPresenter {

    private GameSearchView view;
    private Endpoints endpoints;
    private Subscription searchRequest;

    SearchFragmentPresenter(GameSearchView view, Endpoints endpoints) {
        this.view = view;
        this.endpoints = endpoints;
    }

    /**
     * Search the API and update the view.
     * @param query Text to be searched for.
     */
    private void search(String query) {
        searchRequest = endpoints.search(PrivateConfig.GIANT_BOMB_API_KEY, query,
                ResponseFormats.JSON, 10,
                SearchResources.GAME, GameFields.NAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<SearchResults>>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable error) {
                        view.showSearchError();

                    }

                    @Override
                    public final void onNext(Response<SearchResults> resultsResponse) {
                        view.showSearchResults(resultsResponse.body());
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
     * If an existing search is in progress, let it continue and don't perform a new search.
     * @param query Text to be searched for.
     */
    void continuedSearch(String query) {
        if (!isSearching()) {
            search(query);
        }
    }

    /**
     * Checks is there is a search in progress.
     * @return true if there's a search in progress, false if there isn't.
     */
    private boolean isSearching() {
        return searchRequest != null && !searchRequest.isUnsubscribed();
    }

    /**
     * Stop a search that is currently in progress.
     */
    private void cancelSearch() {
        if (searchRequest != null) {
            searchRequest.unsubscribe();
        }
    }
}