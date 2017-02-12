package android.ccook.info.giantbombapi;

import android.ccook.info.giantbombapi.models.SearchResults;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

interface Endpoints {

        @GET("search/")
        Observable<SearchResults> search(@Query("api_key") String apiKey,
                                         @Query("query") String query,
                                         @Query("format") String format,
                                         @Query("limit") int limit,
                                         @Query("resources") String resources,
                                         @Query("field_list") String fieldList);
}