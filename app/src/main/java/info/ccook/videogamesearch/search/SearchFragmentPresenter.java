package info.ccook.videogamesearch.search;

import android.util.Log;

import info.ccook.videogamesearch.config.PrivateConfig;
import info.ccook.videogamesearch.network.GiantBombService;
import info.ccook.videogamesearch.search.models.SearchResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

class SearchFragmentPresenter {

    private GiantBombService giantBombService;
    private GameSearchView view;

    SearchFragmentPresenter(GiantBombService giantBombService, GameSearchView view) {
        this.giantBombService = giantBombService;
        this.view = view;
    }

    public void search(String query) {
        giantBombService.search(PrivateConfig.GIANT_BOMB_API_KEY, query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SearchResults>() {
                    @Override
                    public final void onCompleted() {
                    }

                    @Override
                    public final void onError(Throwable error) {
                        onSearchError(error);
                    }

                    @Override
                    public final void onNext(SearchResults searchResults) {
                        onSearchSuccess(searchResults);
                    }
                });
    }

    private void onSearchSuccess(SearchResults searchResults) {
        view.showSearchResults(searchResults);
    }

    private void onSearchError(Throwable error) {
        Log.d("stuff", error.getMessage());
    }
}