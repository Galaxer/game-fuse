package info.ccook.videogamesearch.search;

import android.ccook.info.giantbombapi.search.models.SearchResults;

interface GameSearchView {
    void showSearchResults(SearchResults searchResults);
}