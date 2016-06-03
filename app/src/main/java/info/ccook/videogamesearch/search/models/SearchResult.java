package info.ccook.videogamesearch.search.models;

public class SearchResult {

    private String name;

    public String getName() {
        if (name == null) {
            return "";
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}