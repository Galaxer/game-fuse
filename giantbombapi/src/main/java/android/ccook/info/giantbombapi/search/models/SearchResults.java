package android.ccook.info.giantbombapi.search.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.util.List;

@AutoValue
public abstract class SearchResults implements Parcelable {

    public abstract List<SearchResult> results();

    public static TypeAdapter<SearchResults> typeAdapter(Gson gson) {
        return new AutoValue_SearchResults.GsonTypeAdapter(gson);
    }
}