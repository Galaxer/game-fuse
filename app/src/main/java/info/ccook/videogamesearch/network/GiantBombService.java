package info.ccook.videogamesearch.network;

import info.ccook.videogamesearch.search.models.SearchResults;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GiantBombService {

    String BASE_API_URL = "http://www.giantbomb.com/api/";
    long MAX_CACHE_DIR_SIZE = 10 * 1024 * 1024; // 10 MB
    long TIMEOUT_SECONDS = 20;

    // Search parameters
    String SEARCH_FIELD_LIST = "name";
    String SEARCH_RESOURCES = "game";
    int SEARCH_LIMIT = 10;

    @GET("search/" +
            "?format=json" +
            "&limit=" + SEARCH_LIMIT +
            "&resources=" + SEARCH_RESOURCES +
            "&field_list=" + SEARCH_FIELD_LIST)
    Observable<SearchResults> search(@Query("api_key") String apiKey,
                                     @Query("query") String query);
}