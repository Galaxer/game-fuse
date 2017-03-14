package android.ccook.info.giantbombapi.search.models;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class SearchResult {

    public abstract String name();

    public static TypeAdapter<SearchResult> typeAdapter(Gson gson) {
        return new AutoValue_SearchResult.GsonTypeAdapter(gson).setDefaultName("");
    }
}