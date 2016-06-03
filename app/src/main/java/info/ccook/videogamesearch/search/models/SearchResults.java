package info.ccook.videogamesearch.search.models;

import java.util.ArrayList;
import java.util.List;

public class SearchResults {

    private List<SearchResult> results = new ArrayList<>();

    public List<SearchResult> getResults() {
        return results;
    }

    public void setResults(List<SearchResult> results) {
        this.results = results;
    }
}