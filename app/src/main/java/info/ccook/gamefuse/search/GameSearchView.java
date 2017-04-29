package info.ccook.gamefuse.search;

import android.ccook.info.giantbombapi.search.models.SearchResult;

import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

interface GameSearchView extends MvpView {
    void showSearchResults(List<SearchResult> searchResults);
    void showSearchError();
    void showProgressBar();
    void hideProgressBar();
    void focusOnSearchAndShowKeyboard();
    void clearSearchFocus();
    void setSearchQuery(String query);
}