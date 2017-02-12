package info.ccook.videogamesearch.search;

import android.ccook.info.giantbombapi.Callback;
import android.ccook.info.giantbombapi.GiantBombAPI;
import android.ccook.info.giantbombapi.SearchRequest;
import android.ccook.info.giantbombapi.models.SearchResults;
import android.util.Log;

import info.ccook.videogamesearch.config.PrivateConfig;

class SearchFragmentPresenter {

    private GameSearchView view;
    private GiantBombAPI.Builder giantBombApiBuilder;

    SearchFragmentPresenter(GameSearchView view, GiantBombAPI.Builder giantBombApiBuilder) {
        this.view = view;
        this.giantBombApiBuilder = giantBombApiBuilder;
    }

    public void search(String query) {
        SearchRequest searchRequest = new SearchRequest.Builder()
                .setResource(SearchRequest.RESOURCE_GAME)
                .setQuery(query)
                .setField(SearchRequest.FIELD_NAME)
                .setCallback(new Callback<SearchResults>() {
                    @Override
                    public void onError(Throwable error) {
                        onSearchError(error);
                    }

                    @Override
                    public void onSuccess(SearchResults searchResults) {
                        onSearchSuccess(searchResults);
                    }
                }).build();

        giantBombApiBuilder
                .setApiKey(PrivateConfig.GIANT_BOMB_API_KEY)
                .build().search(searchRequest);
    }

    private void onSearchSuccess(SearchResults searchResults) {
        view.showSearchResults(searchResults);
    }

    private void onSearchError(Throwable error) {
        Log.d("Search Error", "Error getting search results");
    }
}