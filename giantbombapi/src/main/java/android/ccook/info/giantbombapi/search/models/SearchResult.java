package android.ccook.info.giantbombapi.search.models;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class SearchResult implements Parcelable {

    public abstract String name();

    public static TypeAdapter<SearchResult> typeAdapter(Gson gson) {
        return new AutoValue_SearchResult.GsonTypeAdapter(gson).setDefaultName("");
    }
}