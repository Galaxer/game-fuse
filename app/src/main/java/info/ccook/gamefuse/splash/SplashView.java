package info.ccook.gamefuse.splash;

import com.hannesdorfmann.mosby3.mvp.MvpView;

interface SplashView extends MvpView {
    void hideLoadingIndicator();
    void showError();
    void showSearch();
}