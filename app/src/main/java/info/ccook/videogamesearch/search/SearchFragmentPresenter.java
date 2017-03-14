package info.ccook.videogamesearch.search;

import android.ccook.info.giantbombapi.ResponseFormats;
import android.ccook.info.giantbombapi.Endpoints;
import android.ccook.info.giantbombapi.game.GameFields;
import android.ccook.info.giantbombapi.search.SearchResources;
import android.ccook.info.giantbombapi.search.models.SearchResults;
import android.util.Log;

import info.ccook.videogamesearch.config.PrivateConfig;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class SearchFragmentPresenter {

    private GameSearchView view;
    private Endpoints endpoints;

    SearchFragmentPresenter(GameSearchView view, Endpoints endpoints) {
        this.view = view;
        this.endpoints = endpoints;
    }

    public void search(String query) {
        endpoints.search(PrivateConfig.GIANT_BOMB_API_KEY, query, ResponseFormats.JSON, 10,
                SearchResources.GAME, GameFields.NAME)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<SearchResults>>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable error) {
                        onSearchError(error);
                    }

                    @Override
                    public final void onNext(Response<SearchResults> resultsResponse) {
                        onSearchSuccess(resultsResponse.body());
                    }
                });
    }

    private void onSearchSuccess(SearchResults searchResults) {
        view.showSearchResults(searchResults);
    }

    private void onSearchError(Throwable error) {
        Log.d("Search Error", "Error getting search results");
    }
}