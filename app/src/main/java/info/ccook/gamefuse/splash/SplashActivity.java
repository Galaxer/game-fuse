package info.ccook.gamefuse.splash;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import info.ccook.gamefuse.App;
import info.ccook.gamefuse.BaseMvpActivity;
import info.ccook.gamefuse.R;
import info.ccook.gamefuse.databinding.SplashActivityBinding;
import info.ccook.gamefuse.search.SearchActivity;

public class SplashActivity extends BaseMvpActivity<SplashView, SplashActivityPresenter> implements
        SplashView {

    @Inject SplashActivityPresenter presenter;

    private SplashActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        binding = DataBindingUtil.setContentView(this, R.layout.splash_activity);
        presenter.fetchConfig();
    }

    @Override
    public void injectDependencies() {
        DaggerSplashComponent.builder()
                .appComponent(((App) getApplication()).getComponent())
                .splashModule(new SplashModule())
                .build().inject(this);
    }

    @NonNull
    @Override
    public SplashActivityPresenter createPresenter() {
        return presenter;
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