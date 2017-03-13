package android.ccook.info.giantbombapi.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class SearchResults {

    public abstract List<SearchResult> results();

    public static TypeAdapter<SearchResults> typeAdapter(Gson gson) {
        return new AutoValue_SearchResults.GsonTypeAdapter(gson);
    }
}