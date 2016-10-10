package info.ccook.videogamesearch.search;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import info.ccook.videogamesearch.config.PrivateConfig;
import info.ccook.videogamesearch.network.GiantBombService;
import info.ccook.videogamesearch.search.models.SearchResult;
import info.ccook.videogamesearch.search.models.SearchResults;

public class SearchFragmentPresenter implements Callback<SearchResults> {

    private GiantBombService giantBombService;
    private String query = "";

    public SearchFragmentPresenter(GiantBombService giantBombService) {
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