package info.ccook.gamefuse.search;

import android.app.SearchManager;
import android.ccook.info.giantbombapi.search.models.SearchResult;
import android.ccook.info.giantbombapi.search.models.SearchResults;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import javax.inject.Inject;

import info.ccook.gamefuse.App;
import info.ccook.gamefuse.R;

public class SearchActivity extends AppCompatActivity implements GameSearchView {

    @Inject SearchActivityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSearchComponent.builder()
                .appComponent(((App) getApplication()).getComponent())
                .searchModule(new SearchModule(this, getSupportFragmentManager()))
                .build().inject(this);
        DataBindingUtil.setContentView(this, R.layout.search_activity);
        presenter.continuedSearch("rocket league");
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
    public void showSearchResults(SearchResults searchResults) {
        for (SearchResult result : searchResults.results()) {
            Log.d("stuff", result.name());
        }
    }

    @Override
    public void showSearchError() {
        Toast.makeText(this, "Search error", Toast.LENGTH_LONG).show();
    }
}