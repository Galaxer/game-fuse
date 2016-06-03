package info.ccook.videogamesearch.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import info.ccook.videogamesearch.search.models.SearchResults;

public interface GiantBombService {

    String BASE_API_URL = "http://www.giantbomb.com/api/";
    long MAX_CACHE_DIR_SIZE = 10 * 1024 * 1024; // 10 MB
    long TIMEOUT_SECONDS = 20;

    @GET("search/?format=json")
    Call<SearchResults> search(@Query("api_key") String apiKey,
                               @Query("resources") String resources,
                               @Query("query") String query,
                               @Query("limit") int limit);
}