package info.ccook.gamefuse.splash;

import com.hannesdorfmann.mosby.mvp.MvpView;

interface SplashView extends MvpView {
    void hideLoadingIndicator();
    void showError();
    void showSearch();
}