package info.ccook.videogamesearch.search;

import android.ccook.info.giantbombapi.SearchRequest;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchFragmentPresenterModule {

    @Provides
    SearchRequest.Builder provideSearchRequestBuilder() {
        return new SearchRequest.Builder();
    }
}