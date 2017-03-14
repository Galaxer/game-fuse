package android.ccook.info.giantbombapi;

import android.ccook.info.giantbombapi.search.models.SearchResults;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface Endpoints {

        @GET("search/")
        Observable<Response<SearchResults>> search(@Query("api_key") String apiKey,
                                                  @Query("query") String query,
                                                  @Query("format") String format,
                                                  @Query("limit") int limit,
                                                  @Query("resources") String resources,
                                                  @Query("field_list") String fieldList);
}