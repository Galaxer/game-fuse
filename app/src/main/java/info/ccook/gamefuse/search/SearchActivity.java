package info.ccook.gamefuse.search;

import android.app.SearchManager;
import android.ccook.info.giantbombapi.search.models.SearchResult;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import info.ccook.gamefuse.App;
import info.ccook.gamefuse.BaseMvpActivity;
import info.ccook.gamefuse.R;
import info.ccook.gamefuse.databinding.SearchActivityBinding;

public class SearchActivity extends BaseMvpActivity<GameSearchView, SearchActivityPresenter>
        implements GameSearchView {

    private static final String SEARCH_RESULTS_STATE_KEY = "searchResults";

    @Inject SearchActivityPresenter presenter;
    @Inject LinearLayoutManager linearLayoutManager;
    @Inject SearchResultsAdapter searchResultsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SearchActivityBinding binding = DataBindingUtil
                .setContentView(this, R.layout.search_activity);
        binding.searchResults.setLayoutManager(linearLayoutManager);
        binding.searchResults.setAdapter(searchResultsAdapter);
        presenter.attachView(this);
    }

    @Override
    public void injectDependencies() {
        DaggerSearchComponent.builder()
                .appComponent(((App) getApplication()).getComponent())
                .searchModule(new SearchModule())
                .build().inject(this);
    }

    @NonNull
    @Override
    public SearchActivityPresenter createPresenter() {
        return presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        final SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager)
                getSystemService(AppCompatActivity.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
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
        return true;
    }

    @Override
    protected void onDestroy() {
        presenter.detachView(true);
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(SEARCH_RESULTS_STATE_KEY,
                new ArrayList<Parcelable>(searchResultsAdapter.getSearchResults()));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<SearchResult> searchResults = savedInstanceState
                .getParcelableArrayList(SEARCH_RESULTS_STATE_KEY);
        showSearchResults(searchResults);
    }

    @Override
    public void showSearchResults(List<SearchResult> searchResults) {
        searchResultsAdapter.setSearchResults(searchResults);
    }

    @Override
    public void showSearchError() {
        Toast.makeText(this, "Search error", Toast.LENGTH_LONG).show();
    }
}