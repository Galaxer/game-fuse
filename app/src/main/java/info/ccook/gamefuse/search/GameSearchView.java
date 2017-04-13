package info.ccook.gamefuse.search;

import android.ccook.info.giantbombapi.search.models.SearchResult;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

interface GameSearchView extends MvpView {
    void showSearchResults(List<SearchResult> searchResults);
    void showSearchError();
}