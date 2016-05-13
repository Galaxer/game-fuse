package videogamessearch.android.ccook.info.videogamessearch.search;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import videogamessearch.android.ccook.info.videogamessearch.config.PrivateConfig;
import videogamessearch.android.ccook.info.videogamessearch.network.GiantBombService;
import videogamessearch.android.ccook.info.videogamessearch.search.models.SearchResult;
import videogamessearch.android.ccook.info.videogamessearch.search.models.SearchResults;

public class SearchActivityPresenter implements Callback<SearchResults> {

    private GiantBombService giantBombService;
    private String query = "";

    public SearchActivityPresenter(GiantBombService giantBombService) {
        this.giantBombService = giantBombService;
    }

    public void search(String query) {
        Call<SearchResults> call = giantBombService.search(PrivateConfig.GIANT_BOMB_API_KEY, "game",
                query, 10);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<SearchResults> call, Response<SearchResults> response) {
        Log.d("stuff", call.request().url().toString());
        Log.d("stuff", response.code() + "");
        if (response.body() != null) {
            List<SearchResult> results = response.body().getResults();
            for (SearchResult result : results) {
                Log.d("stuff", result.getName());
            }
        }
    }

    @Override
    public void onFailure(Call<SearchResults> call, Throwable t) {
        Log.d("stuff", t.getMessage());
    }
}