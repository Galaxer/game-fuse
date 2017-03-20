package info.ccook.videogamesearch.search;

import android.app.SearchManager;
import android.ccook.info.giantbombapi.search.models.SearchResult;
import android.ccook.info.giantbombapi.search.models.SearchResults;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import javax.inject.Inject;

import info.ccook.videogamesearch.App;
import info.ccook.videogamesearch.R;
import info.ccook.videogamesearch.databinding.SearchFragmentBinding;

public class SearchFragment extends Fragment implements GameSearchView {

    @Inject SearchFragmentPresenter presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        DaggerSearchComponent.builder()
                .appComponent(((App) getActivity().getApplication()).getComponent())
                .searchFragmentModule(new SearchFragmentModule(this, getFragmentManager()))
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SearchFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment,
                container, false);
        binding.toolbar.setTitle(R.string.app_name);
        binding.toolbar.inflateMenu(R.menu.search_menu);
        final SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(binding.toolbar.getMenu().findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getActivity()
                .getSystemService(AppCompatActivity.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity()
                .getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.newSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.continuedSearch("rocket league");
    }

    @Override
    public void showSearchResults(SearchResults searchResults) {
        for (SearchResult result : searchResults.results()) {
            Log.d("stuff", result.name());
        }
    }

    @Override
    public void showSearchError() {
        Toast.makeText(getContext(), "Search error", Toast.LENGTH_LONG).show();
    }
}