package info.ccook.gamefuse.search;

import android.animation.Animator;
import android.app.SearchManager;
import android.ccook.info.giantbombapi.search.models.SearchResult;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.daimajia.androidanimations.library.YoYo;
import com.evernote.android.state.State;
import com.evernote.android.state.StateSaver;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import info.ccook.gamefuse.App;
import info.ccook.gamefuse.BaseMvpActivity;
import info.ccook.gamefuse.R;
import info.ccook.gamefuse.SlideInDownAnimatorNoFade;
import info.ccook.gamefuse.SlideOutUpAnimatorNoFade;
import info.ccook.gamefuse.databinding.SearchActivityBinding;

public class SearchActivity extends BaseMvpActivity<GameSearchView, SearchActivityPresenter>
        implements GameSearchView {

    @Inject SearchActivityPresenter presenter;
    @Inject SearchResultsAdapter searchResultsAdapter;

    @State ArrayList<SearchResult> searchResults;
    @State int progressBarVisibility;

    private SearchActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.search_activity);
        binding.searchResults.setLayoutManager(new LinearLayoutManager(this));
        binding.searchResults.setAdapter(searchResultsAdapter);
        getPresenter().attachView(this);
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
                searchView.clearFocus();
                getPresenter().newSearch(query);
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
        getPresenter().detachView(true);
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        StateSaver.saveInstanceState(this, outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        StateSaver.restoreInstanceState(this, savedInstanceState);
        binding.progressBar.setVisibility(progressBarVisibility);
        showSearchResults(searchResults);
    }

    @Override
    public void showSearchResults(List<SearchResult> searchResults) {
        this.searchResults = new ArrayList<>(searchResults);
        searchResultsAdapter.setSearchResults(searchResults);
    }

    @Override
    public void showSearchError() {
        Toast.makeText(this, "Search error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressBar() {
        progressBarVisibility = View.VISIBLE;
        YoYo.with(new SlideInDownAnimatorNoFade())
                .interpolate(new AccelerateDecelerateInterpolator())
                .duration(300)
                .onStart(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        binding.progressBar.setVisibility(View.VISIBLE);
                    }
                })
                .playOn(binding.progressBar);
    }

    @Override
    public void hideProgressBar() {
        progressBarVisibility = View.GONE;
        YoYo.with(new SlideOutUpAnimatorNoFade())
                .interpolate(new AccelerateDecelerateInterpolator())
                .duration(300)
                .onEnd(new YoYo.AnimatorCallback() {
                    @Override
                    public void call(Animator animator) {
                        binding.progressBar.setVisibility(View.INVISIBLE);
                    }
                })
                .playOn(binding.progressBar);
    }
}