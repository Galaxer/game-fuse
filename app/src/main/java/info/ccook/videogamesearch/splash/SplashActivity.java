package info.ccook.videogamesearch.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import info.ccook.videogamesearch.App;
import info.ccook.videogamesearch.R;
import info.ccook.videogamesearch.databinding.SplashActivityBinding;
import info.ccook.videogamesearch.search.SearchActivity;

public class SplashActivity extends AppCompatActivity implements SplashView {

    @Inject SplashActivityPresenter presenter;

    private SplashActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerSplashComponent.builder()
                .appComponent(((App) getApplication()).getComponent())
                .splashModule(new SplashModule(this, getSupportFragmentManager()))
                .build().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.splash_activity);
        presenter.fetchConfig();
    }

    @Override
    public void hideLoadingIndicator() {
        binding.loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void showSearch() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Unable to get config", Toast.LENGTH_LONG).show();
    }
}