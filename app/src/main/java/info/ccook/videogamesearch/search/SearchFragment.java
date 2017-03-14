package info.ccook.videogamesearch.search;

import android.app.Application;
import android.app.SearchManager;
import android.ccook.info.giantbombapi.search.models.SearchResult;
import android.ccook.info.giantbombapi.search.models.SearchResults;
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

import javax.inject.Inject;

import info.ccook.videogamesearch.AppComponent;
import info.ccook.videogamesearch.AppModule;
import info.ccook.videogamesearch.DaggerAppComponent;
import info.ccook.videogamesearch.R;
import info.ccook.videogamesearch.databinding.SearchFragmentBinding;

public class SearchFragment extends Fragment implements GameSearchView {

    @Inject
    SearchFragmentPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(getActivity().getApplication());
    }

    private void injectDependencies(Application application) {
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(application)).build();

        DaggerSearchComponent.builder()
                .appComponent(appComponent)
                .searchFragmentModule(new SearchFragmentModule(this))
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
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
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.search("rocket league");
    }

    @Override
    public void showSearchResults(SearchResults searchResults) {
        for (SearchResult result : searchResults.results()) {
            Log.d("stuff", result.name());
        }
    }
}