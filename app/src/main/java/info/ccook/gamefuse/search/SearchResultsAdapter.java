package info.ccook.gamefuse.search;

import android.ccook.info.giantbombapi.search.models.SearchResult;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import info.ccook.gamefuse.databinding.SearchResultBinding;

class SearchResultsAdapter extends RecyclerView.Adapter {

    private List<SearchResult> searchResults = new ArrayList<>();

    void setSearchResults(List<SearchResult> searchResults) {
        this.searchResults = searchResults;
        notifyDataSetChanged();
    }

    List<SearchResult> getSearchResults() {
        return searchResults;
    }

    @Override
    public int getItemCount() {
        return searchResults.size();
    }

    private SearchResult getItemForPosition(int position) {
        return searchResults.get(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SearchResultBinding itemBinding = SearchResultBinding
                .inflate(layoutInflater, parent, false);
        return new SearchResultViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchResultViewHolder viewHolder = (SearchResultViewHolder) holder;
        viewHolder.bind(getItemForPosition(position));
    }

    private static class SearchResultViewHolder extends RecyclerView.ViewHolder {

        private SearchResultBinding binding;

        SearchResultViewHolder(SearchResultBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(SearchResult searchResult) {
            binding.setSearchResult(searchResult);
            binding.executePendingBindings();
        }
    }
}